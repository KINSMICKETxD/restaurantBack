package com.restaurantApp.restaurantBack.service.resaurantTable;

import com.restaurantApp.restaurantBack.dao.RestaurantTableDAO;
import com.restaurantApp.restaurantBack.entity.RestaurantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<RestaurantTable> findAll() {
        return tableDAO.findAll();
    }

    @Override
    public void save(RestaurantTable table) {

        tableDAO.save(table);

    }

    @Override
    public void deleteTableById(int tableId) {

        tableDAO.deleteById(tableId);

    }
}
