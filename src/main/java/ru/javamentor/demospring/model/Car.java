package ru.javamentor.demospring.model;

public class Car {

    private String model;
    private int price;
    private int series;

    public Car(String model, int price, int series) {
        this.model = model;
        this.price = price;
        this.series = series;
    }

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
