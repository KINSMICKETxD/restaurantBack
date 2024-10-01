package com.restaurantApp.restaurantBack.service.Order;


import com.restaurantApp.restaurantBack.dao.CartDAO;
import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerDAO;
import com.restaurantApp.restaurantBack.dao.OrderDAO;
import com.restaurantApp.restaurantBack.dto.CartDTO;
import com.restaurantApp.restaurantBack.dto.OrderDTO;
import com.restaurantApp.restaurantBack.dto.OrderItemDTO;
import com.restaurantApp.restaurantBack.entity.*;
import com.restaurantApp.restaurantBack.enums.OrderStatus;
import com.restaurantApp.restaurantBack.exception.CartNotFoundException;
import com.restaurantApp.restaurantBack.exception.CustomerNotFoundException;
import com.restaurantApp.restaurantBack.service.OrderItem.OrderItemServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    private OrderDAO orderDAO;

    private CustomerDAO customerDAO;

    private CartDAO cartDAO;



    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO,CartDAO cartDAO,CustomerDAO customerDAO){
        this.cartDAO = cartDAO;
        this.orderDAO = orderDAO;
        this.customerDAO = customerDAO;
    }



    @Override
    public OrderDTO placeOrder(String userName){

        Integer customerId = this.customerDAO.findCustomerIdByUserName(userName).orElseThrow(
                ()->new CustomerNotFoundException("No customer found for user with userName = "+userName)
        );
        Cart cart = this.cartDAO.findByCustomerId(customerId).orElseThrow(
                ()->new CartNotFoundException("You don't have a Cart.")
        );
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalPrice(cart.getTotalAmount());
        order.setCustomer(cart.getCustomer());
        order.setOrderNumber();

        for(OrderItem o : convertCartItemListToOrderItemsList(cart.getCartItems())){
            order.addOrderItem(o);
        }

        Order savedOrder = this.orderDAO.save(order);
        OrderDTO orderDTO = toOrderDTO(savedOrder);
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        for(OrderItem o : order.getOrderItems()){
            orderItemDTOS.add(OrderItemServiceImpl.toOrderItemDTO(o));
        }
        orderDTO.setOrderItemDTOS(orderItemDTOS);

        return orderDTO;

    }

    private List<OrderItem> convertCartItemListToOrderItemsList(List<CartItem> cartItemList){
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem c : cartItemList){

            orderItems.add(convertCartItemToOrderItem(c));
        }

        return orderItems;

    }
    private OrderItem convertCartItemToOrderItem(CartItem cartItem){
        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(cartItem.getMenuItem());
        orderItem.setPrice(cartItem.getTotalPrice());
        orderItem.setQuantity(cartItem.getQuantity());

        return orderItem;
    }

    private OrderDTO toOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setStatus(order.getOrderStatus());
        orderDTO.setCustomerId(order.getCustomer().getId());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setCustomerFullName(order.getCustomer().getUser().getFirstName()+" "+order.getCustomer().getUser().getLastName());
        return orderDTO;
    }

}
