package com.graduation.voting.model;

import java.time.LocalDate;

public class Meal extends AbstractNamedEntity {

    private Integer price;

    private LocalDate date;

    public Meal(Integer id, String name, Integer price, LocalDate date) {
        super(id, name);
        this.price = price;
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name +
                ", price=" + price +
                '}';
    }
}
