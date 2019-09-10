package com.example.KhoaLeU1Capstone.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class ProcessingFee {

    @NotNull
    private String productType;
    @NotNull
    private double fee;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

}
