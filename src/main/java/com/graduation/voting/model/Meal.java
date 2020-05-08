package com.graduation.voting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date"}, name = "meals_unique_restaurant_date_idx")})
public class Meal extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @Range(min = 0, max = 99999)
    private Double price;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Meal() {
            }

    public Meal(Integer id, String name, Double price, LocalDate date) {
        super(id, name);
        this.price = price;
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
