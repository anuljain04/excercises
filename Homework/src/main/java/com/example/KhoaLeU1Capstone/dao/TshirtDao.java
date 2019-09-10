package com.example.KhoaLeU1Capstone.dao;

import com.example.KhoaLeU1Capstone.model.Tshirt;

import java.util.List;

public interface TshirtDao {

    Tshirt addTshirt(Tshirt tshirt);

    Tshirt getTshirt(int tshirtId);

    List<Tshirt> getAllTshirts();

    List<Tshirt> getTshirtsByColor(String color);

    List<Tshirt> getTshirtsBySize(String size);

    void updateTshirt(Tshirt tshirt);

    void deleteTshirt(int tshirtId);
}
