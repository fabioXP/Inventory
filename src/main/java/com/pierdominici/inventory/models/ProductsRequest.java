package com.pierdominici.inventory.models;

import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductsRequest {
    private String name;
    private String description;
    @Min(value = 0,message = "the price can't be less than 0")
    private float price;
    @Min(value = 0,message = "the amount can't be less than 0")
    private Integer amount;
}
