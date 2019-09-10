package com.example.KhoaLeU1Capstone.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Tshirt {

    @NotNull
    private int tshirtId;
    private String size;
    private String color;
    private String description;
    @NotNull
    private double tshirtPrice;
    @NotNull
    private int tshirtQuantity;

    public int getTshirtId() {
        return tshirtId;
    }

    public void setTshirtId(int tshirtId) {
        this.tshirtId = tshirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTshirtPrice() {
        return tshirtPrice;
    }

    public void setTshirtPrice(double tshirtPrice) {
        this.tshirtPrice = tshirtPrice;
    }

    public int getTshirtQuantity() {
        return tshirtQuantity;
    }

    public void setTshirtQuantity(int tshirtQuantity) {
        this.tshirtQuantity = tshirtQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tshirt tshirt = (Tshirt) o;
        return getTshirtId() == tshirt.getTshirtId() &&
                Objects.equals(getSize(), tshirt.getSize()) &&
                Objects.equals(getColor(), tshirt.getColor()) &&
                Objects.equals(getDescription(), tshirt.getDescription()) &&
                Objects.equals(getTshirtPrice(), tshirt.getTshirtPrice()) &&
                Objects.equals(getTshirtQuantity(), tshirt.getTshirtQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTshirtId(), getSize(), getColor(), getDescription(), getTshirtPrice(), getTshirtQuantity());
    }
}
