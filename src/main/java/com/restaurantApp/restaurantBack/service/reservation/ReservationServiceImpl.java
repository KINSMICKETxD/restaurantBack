package com.restaurantApp.restaurantBack.service.reservation;

import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerDAO;
import com.restaurantApp.restaurantBack.dao.ReservationDAO;
import com.restaurantApp.restaurantBack.entity.Customer;
import com.restaurantApp.restaurantBack.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReservationServiceImpl implements ReservationService{

    private ReservationDAO reservationDAO;

    private CustomerDAO customerDAO;


    @Autowired
    public ReservationServiceImpl(ReservationDAO reservationDAO,CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
        this.reservationDAO = reservationDAO;
    }



    @Override
    public Reservation findById(int reservationId) {
        return reservationDAO.findById(reservationId).get();
    }

    @Override
    public Reservation findByIdAndCustomerId(int reservationId, int customerId) {
        return this.reservationDAO.findByIdAndCustomerId(reservationId,customerId).get();
    }

    @Override
    public List<Reservation> findAllByCustomerId(int CustomerId) {
        return this.reservationDAO.findByCustomerId(CustomerId);
    }

    @Override
    public void save(int CustomerId,Reservation reservation) {

        Customer theCustomer = this.customerDAO.findById(CustomerId).get();
        reservation.setCustomer(theCustomer);
        this.reservationDAO.save(reservation);
    }

    @Override
    public void deleteById(int reservationId) {

        Reservation theReservation = this.reservationDAO.findById(reservationId).get();
        theReservation.setCustomer(null);
        this.reservationDAO.delete(theReservation);
    }
}
