package com.restaurantApp.restaurantBack.service.reservation;

import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerDAO;
import com.restaurantApp.restaurantBack.dao.ReservationDAO;
import com.restaurantApp.restaurantBack.dao.RestaurantTableDAO;
import com.restaurantApp.restaurantBack.dao.UserDAO;
import com.restaurantApp.restaurantBack.dto.ReservationDTO;
import com.restaurantApp.restaurantBack.dto.ReservationDurationDTO;
import com.restaurantApp.restaurantBack.entity.Customer;
import com.restaurantApp.restaurantBack.entity.Reservation;
import com.restaurantApp.restaurantBack.entity.RestaurantTable;
import com.restaurantApp.restaurantBack.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService{

    private ReservationDAO reservationDAO;

    private RestaurantTableDAO restaurantTableDAO;

    private CustomerDAO customerDAO;


    @Autowired
    public ReservationServiceImpl(ReservationDAO reservationDAO,CustomerDAO customerDAO,
                                  RestaurantTableDAO restaurantTableDAO){
        this.customerDAO = customerDAO;
        this.reservationDAO = reservationDAO;
        this.restaurantTableDAO = restaurantTableDAO;

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
    public ReservationDTO save(ReservationDTO reservationDTO) {

        if(this.reservationDAO.isTableAvailable(reservationDTO.getTableId(),
                reservationDTO.getReservationDateBegin(),
                reservationDTO.getReservationDateEnd()) == 0) {

            Reservation reservation = toEntity(reservationDTO);
            reservation = this.reservationDAO.save(reservation);
            if (reservation != null) {
                return toDTO(reservation);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int reservationId) {

        Reservation theReservation = this.reservationDAO.findById(reservationId).get();
        theReservation.setCustomer(null);
        this.reservationDAO.delete(theReservation);
    }

    public Reservation toEntity(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        Customer customer =  customerDAO.findById(reservationDTO.getCustomerId()).get();
        RestaurantTable restaurantTable = this.restaurantTableDAO.findById(reservationDTO.getTableId()).get();
        LocalDateTime DateBegin = reservationDTO.getReservationDateBegin();
        LocalDateTime DateEnd = reservationDTO.getReservationDateEnd();
        reservation.setCustomer(customer);
        reservation.setTable(restaurantTable);
        reservation.setReservationDateTimeBegin(DateBegin);
        reservation.setReservationDateTimeEnd(DateEnd);
        reservation.setGuestNumber(reservationDTO.getNumberOfGuests());

        return this.reservationDAO.save(reservation);
    }

    public ReservationDTO toDTO(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(reservation.getId());
        reservationDTO.setUserName(reservation.getCustomer().getUser().getUserName());
        reservationDTO.setCustomerId(reservation.getCustomer().getId());
        reservationDTO.setTableId(reservation.getTable().getId());
        reservationDTO.setTableNumber(reservation.getTable().getTableNumber());
        reservationDTO.setReservationDateBegin(reservation.getReservationDateTimeBegin());
        reservationDTO.setReservationDateEnd(reservation.getReservationDateTimeEnd());
        reservationDTO.setNumberOfGuests(reservation.getGuestNumber());
        reservationDTO.setSpecialInstruction(reservation.getSpecialInstructions());

        return reservationDTO;

    }

    @Override
    public List<ReservationDurationDTO> getAllReservationByTableId(int tableId) {

        List<Reservation> reservations = this.reservationDAO.getAllReservationsForATable(tableId);

        List<ReservationDurationDTO> reservationDurationDTOS = new ArrayList<>();
        for(Reservation r : reservations){
            reservationDurationDTOS.add(convertReservationToDuration(r));
        }

        return reservationDurationDTOS;
    }

   /* public boolean isTableAvailable(int table_id,LocalDateTime reservationTime){
        return !reservationDAO.isTableReserved(table_id,reservationTime);
    }*/



    private ReservationDurationDTO convertReservationToDuration(Reservation reservation){
        ReservationDurationDTO reservationDurationDTO = new ReservationDurationDTO();
        reservationDurationDTO.setBeginTime(reservation.getReservationDateTimeBegin());
        reservationDurationDTO.setEndTime(reservation.getReservationDateTimeEnd());

        return reservationDurationDTO;
    }
}
