package com.restaurantApp.restaurantBack.service.resaurantTable;

import com.restaurantApp.restaurantBack.dao.RestaurantTableDAO;
import com.restaurantApp.restaurantBack.dto.ReservationDTO;
import com.restaurantApp.restaurantBack.dto.RestaurantTableDTO;
import com.restaurantApp.restaurantBack.entity.RestaurantTable;
import com.restaurantApp.restaurantBack.exception.NoTableAvailableException;
import com.restaurantApp.restaurantBack.exception.TableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RestaurantTableServiceImpl implements RestaurantTableService{

    private RestaurantTableDAO tableDAO;


    @Autowired
    public RestaurantTableServiceImpl(RestaurantTableDAO tableDAO){
        this.tableDAO = tableDAO;
    }


    @Override
    public RestaurantTable findTableById(int tableId) {
        return tableDAO.findById(tableId).get();
    }

    @Override
    public List<RestaurantTableDTO> findAll() {
        List<RestaurantTable> restaurantTables= tableDAO.findAllByOrderByTableNumberAsc().get();
        List<RestaurantTableDTO> restaurantTableDTOS = new ArrayList<>();
        for(RestaurantTable table : restaurantTables){
            restaurantTableDTOS.add(convertToDTO(table));
        }

        return restaurantTableDTOS;
    }

    @Override
    public void save(RestaurantTable table) {

        tableDAO.save(table);

    }

    @Override
    public void deleteTableById(int tableId) {

        tableDAO.deleteById(tableId);

    }

    @Override
    public List<RestaurantTableDTO> getAllAvailableTable(ReservationDTO reservationDTO) {
        List<RestaurantTable> restaurantTables = this.tableDAO.findTableForCustomer(reservationDTO.getNumberOfGuests(),
                reservationDTO.getReservationDateEnd(),reservationDTO.getReservationDateBegin()).orElseThrow(
                ()-> new TableNotFoundException("There is no table available.")
        );
        if(restaurantTables.isEmpty()){
            throw new NoTableAvailableException("Sorry no table available now.");
        }
        List<RestaurantTableDTO> restaurantTableDTOS = new ArrayList<>();
        for(RestaurantTable table : restaurantTables){
            restaurantTableDTOS.add(convertToDTO(table));
        }
        return restaurantTableDTOS;
    }

    RestaurantTableDTO convertToDTO(RestaurantTable restaurantTable){
        RestaurantTableDTO restaurantTableDTO = new RestaurantTableDTO(
                restaurantTable.getId(), restaurantTable.getTableNumber(),
                restaurantTable.getDesc(),restaurantTable.getSeatingCapacity(),
                restaurantTable.isAvailable()
        );


        return restaurantTableDTO;
    }
}
