package com.pierdominici.inventory.models;

import jakarta.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "Products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    @NotNull(message = "The name cant be null")
    private String name;

    @Column(name="description")
    @NotNull(message = "The name cant be null")
    private String description;

    @Column(name="price")
    @Min(value = 0,message = "the price can't be less than 0")
    private float price;

    @Column(name="amount")
    @Min(value = 0,message = "the amount can't be less than 0")
    private Integer amount;
}
