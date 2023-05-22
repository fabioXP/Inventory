package com.pierdominici.inventory.service;


import com.pierdominici.inventory.models.Products;
import com.pierdominici.inventory.models.ProductsRequest;
import com.pierdominici.inventory.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private ProductsRepository productsRepository;

    public ResponseEntity<Object> getProductByName(String name){
        Products product;
        try{
            product=productsRepository.findByName(name);
            if (product!=null) {
                return ResponseEntity.ok(product);
            }else{
                return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("unknown error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getProductById(int id){
        Products product;
        try{
            product=productsRepository.findById(id);
            if (product!=null) {
                return ResponseEntity.ok(product);
            }else{
                return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("unknown error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> postProduct(Products productRequest){
        try {
            Products product=new Products();
                    product.setName(productRequest.getName());
                    product.setDescription(productRequest.getDescription());
                    product.setAmount(productRequest.getAmount());
                    product.setPrice(productRequest.getPrice());
            productsRepository.save(productRequest);
            return ResponseEntity.ok("Product created");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("unknown error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateProduct(ProductsRequest productRequest,int id){
        try {
            Products product =productsRepository.findById(id);
            if (product!=null) {
                if(productRequest.getName()!=null){
                    product.setName(productRequest.getName());
                }
                if(productRequest.getPrice() !=0){
                    product.setPrice(productRequest.getPrice());
                    System.out.println("price: "+productRequest.getPrice());
                }
                if(productRequest.getDescription()!=null){
                    product.setDescription(productRequest.getDescription());
                }
                if (productRequest.getAmount()!=null){
                    product.setAmount(productRequest.getAmount());
                }
                productsRepository.updateAllById(product.getName(), product.getDescription(), product.getPrice(), product.getAmount(), product.getId());
                return ResponseEntity.ok("Product updated");
            }else{
                return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("unknown error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> getProductsByPrice(){
        List<Products> products;
        try{
            products=productsRepository.findAAllOrderByPriceAsc();
            return ResponseEntity.ok(products);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("unknown error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
