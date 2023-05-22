package com.pierdominici.inventory.controller;

import com.pierdominici.inventory.models.Products;
import com.pierdominici.inventory.models.ProductsRequest;
import com.pierdominici.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/inventory")
public class ServiceController {

    @Autowired
    private InventoryService inventoryService;
    @RequestMapping("/product")
    public ResponseEntity<Object> getProductbyName(@RequestParam("name") String name){
        return inventoryService.getProductByName(name);
    }

    @RequestMapping("/product/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id){
        return inventoryService.getProductById(id);
    }

    @PostMapping("/post-product")
    public ResponseEntity<String> postProduct(@Valid @RequestBody Products product){
        return inventoryService.postProduct(product);
    }

    @PutMapping("/update-Product/{id}")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody ProductsRequest products, @PathVariable int id){
        return inventoryService.updateProduct(products,id);
    }
    @RequestMapping("/products")
    public ResponseEntity<Object> findAllProductsByPrice(){
        return inventoryService.getProductsByPrice();
    }
}
