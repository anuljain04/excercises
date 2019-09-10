package com.example.KhoaLeU1Capstone.viewmodel;

import java.util.Objects;

public class ConsoleViewModel {

    private int consoleId;
    private String model;
    private String manufacturer;
    private String memoryAmount;
    private String processor;
    private double consolePrice;
    private int consoleQuantity;

    public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public double getConsolePrice() {
        return consolePrice;
    }

    public void setConsolePrice(double consolePrice) {
        this.consolePrice = consolePrice;
    }

    public int getConsoleQuantity() {
        return consoleQuantity;
    }

    public void setConsoleQuantity(int consoleQuantity) {
        this.consoleQuantity = consoleQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleViewModel that = (ConsoleViewModel) o;
        return getConsoleId() == that.getConsoleId() &&
                Objects.equals(getModel(), that.getModel()) &&
                Objects.equals(getManufacturer(), that.getManufacturer()) &&
                Objects.equals(getMemoryAmount(), that.getMemoryAmount()) &&
                Objects.equals(getProcessor(), that.getProcessor()) &&
                Objects.equals(getConsolePrice(), that.getConsolePrice()) &&
                Objects.equals(getConsoleQuantity(), that.getConsoleQuantity());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsoleId(), getModel(), getManufacturer(), getMemoryAmount(), getProcessor(), getConsolePrice(), getConsoleQuantity()
        );
    }
}
