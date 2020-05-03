package com.graduation.voting.model;

import java.time.LocalDate;

public class Meal extends AbstractNamedEntity {

    private Integer price;

    private LocalDate localDate;

    public Meal(Integer id, String name, Integer price, LocalDate localDate) {
        super(id, name);
        this.price = price;
        this.localDate = localDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
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
