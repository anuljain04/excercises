package com.example.KhoaLeU1Capstone.viewmodel;

import java.util.Objects;

public class TshirtViewModel {

    private int tshirtId;
    private String size;
    private String color;
    private String description;
    private double tshirtPrice;
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
        TshirtViewModel that = (TshirtViewModel) o;
        return getTshirtId() == that.getTshirtId() &&
                Objects.equals(getSize(), that.getSize()) &&
                Objects.equals(getColor(), that.getColor()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getTshirtPrice(), that.getTshirtPrice()) &&
                Objects.equals(getTshirtQuantity(), that.getTshirtQuantity());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getTshirtId(), getSize(), getColor(), getDescription(), getTshirtPrice(), getTshirtQuantity()
        );
    }
}
