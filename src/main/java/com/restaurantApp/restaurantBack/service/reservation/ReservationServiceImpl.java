package com.restaurantApp.restaurantBack.service.reservation;

import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerDAO;
import com.restaurantApp.restaurantBack.dao.ReservationDAO;
import com.restaurantApp.restaurantBack.dao.RestaurantTableDAO;
import com.restaurantApp.restaurantBack.dto.CreateReservationDTO;
import com.restaurantApp.restaurantBack.dto.ReservationDTO;
import com.restaurantApp.restaurantBack.dto.ReservationDurationDTO;
import com.restaurantApp.restaurantBack.entity.Customer;
import com.restaurantApp.restaurantBack.entity.Reservation;
import com.restaurantApp.restaurantBack.entity.RestaurantTable;
import com.restaurantApp.restaurantBack.exception.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<ReservationDTO> findAllByCustomerId(int customerId) {
        List<Reservation> reservations = this.reservationDAO.findByCustomerId(customerId).get();
        if(reservations.isEmpty()){
            throw new NoReservationsFoundException("No Reservation found for customer with id = "+customerId);
        }
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for(Reservation reservation : reservations){
            reservationDTOS.add(toDTO(reservation));
        }
        return reservationDTOS;
    }

    @Override
    public ReservationDTO updateReservation(ReservationDTO reservationDTO,int reservationId) {

        Reservation reservation = this.reservationDAO.findById(reservationId).orElseThrow(
                ()-> new ReservationNotFoundException("Reservation with ID = "+reservationId+" is not found.")
        );

        RestaurantTable table = this.restaurantTableDAO.findById(reservationDTO.getTableId()).orElseThrow(
                ()->new TableNotFoundException("Table with id = "+reservationDTO.getTableId()+" is not found.")
        );
        int isTableAvailable = this.reservationDAO.isTableAvailable(reservationDTO.getTableId(),
                reservationDTO.getReservationDateBegin(),
                reservationDTO.getReservationDateEnd(),reservationDTO.getCustomerId());
        if( isTableAvailable== 0 && reservationDTO.getNumberOfGuests() <= table.getSeatingCapacity() ) {

            this.reservationDAO.save(toEntity(reservationDTO,reservation));
            return toDTO(reservation);

        }else if (isTableAvailable != 0){
            throw  new TableIsReservedException("Table is already booked.");
        }else{
            throw new SeatingCapacityExceededException("Number of Guess ("+reservationDTO.getNumberOfGuests()+")" +
                    " bypass Table seating Capacity ("+table.getSeatingCapacity()+").");
        }

    }

    @Override
    public void deleteById(int reservationId) {

        Reservation theReservation = this.reservationDAO.findById(reservationId).get();
        theReservation.setCustomer(null);
        this.reservationDAO.delete(theReservation);
    }

    public Reservation toEntity(ReservationDTO reservationDTO,Reservation reservation){
        Customer customer =  customerDAO.findById(reservationDTO.getCustomerId()).get();
        RestaurantTable restaurantTable = this.restaurantTableDAO.findById(reservationDTO.getTableId()).get();
        LocalDateTime DateBegin = reservationDTO.getReservationDateBegin();
        LocalDateTime DateEnd = reservationDTO.getReservationDateEnd();
        reservation.setCustomer(customer);
        reservation.setTable(restaurantTable);
        reservation.setReservationDateTimeBegin(DateBegin);
        reservation.setReservationDateTimeEnd(DateEnd);
        reservation.setGuestNumber(reservationDTO.getNumberOfGuests());
        reservation.setStatus(reservation.getStatus());
        return reservation;
    }

    public ReservationDTO toDTO(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(reservation.getId());
        reservationDTO.setCustomerId(reservation.getCustomer().getId());
        reservationDTO.setTableId(reservation.getTable().getId());
        reservationDTO.setTableNumber(reservation.getTable().getTableNumber());
        reservationDTO.setReservationDateBegin(reservation.getReservationDateTimeBegin());
        reservationDTO.setReservationDateEnd(reservation.getReservationDateTimeEnd());
        reservationDTO.setNumberOfGuests(reservation.getGuestNumber());
        reservationDTO.setSpecialInstruction(reservation.getSpecialInstructions());
        reservationDTO.setStatus(reservation.getStatus());
        return reservationDTO;

    }

    @Override
    public List<ReservationDurationDTO> getAllReservationByTableId(int tableId) {

        List<Reservation> reservations = this.reservationDAO.getAllActiveReservationsForATable(tableId).get();

        List<ReservationDurationDTO> reservationDurationDTOS = new ArrayList<>();
        for(Reservation r : reservations){
            reservationDurationDTOS.add(convertReservationToDuration(r));
        }

        return reservationDurationDTOS;
    }



    @Override
    public ReservationDTO createNewReservation(@Valid CreateReservationDTO createReservationDTO, int userId) {
        Integer customerId = this.customerDAO.findCustomerIdByUserId(userId).orElseThrow();
        int customerActiveReservationsCount = this.reservationDAO.getAllActiveReservationsForCustomer(customerId,LocalDateTime.now()).get();
        if(customerActiveReservationsCount > 2){
            throw new MaxActiveReservationException("You  reach the Limit of Active Reservations");
        }
        List<RestaurantTable> restaurantTables = this.restaurantTableDAO.findTableForCustomer(
                createReservationDTO.getNumberOfPeople(),
                createReservationDTO.getReservationDateBegin(),
                createReservationDTO.getReservationDateEnd()).get();

        RestaurantTable table = restaurantTables.get(0);


        Customer customer = this.customerDAO.findById(customerId).orElseThrow(
                ()-> new CustomerNotFoundException("customer with id = "+customerId+" is not found.")
        );

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setTable(table);
        reservation.setGuestNumber(createReservationDTO.getNumberOfPeople());
        reservation.setReservationDateTimeBegin(createReservationDTO.getReservationDateBegin());
        reservation.setReservationDateTimeEnd(createReservationDTO.getReservationDateEnd());
        reservation.setSpecialInstructions(createReservationDTO.getSpecialInstructions());
        reservation.setStatus("Active");
        return toDTO(this.reservationDAO.save(reservation));

    }

    @Override
    public ReservationDTO cancelReservation(int reservationId) {
        Reservation reservation = this.reservationDAO.findById(reservationId).orElseThrow(
                ()-> new NoReservationsFoundException("reservation with ID = "+reservationId+" is not found.")
        );

        reservation.setStatus("Canceled");
        return toDTO(reservationDAO.save(reservation));
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
