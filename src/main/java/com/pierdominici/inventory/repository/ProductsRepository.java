package com.pierdominici.inventory.repository;

import com.pierdominici.inventory.models.Products;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<Products,String> {
    Products findByName(String name);
    Products findById(int id);
    @Transactional
    @Modifying
    @Query("update Products p set p.name = ?1, p.description = ?2, p.price = ?3, p.amount = ?4 where p.id = ?5")
    int updateAllById(String name, String description, float price, int amount, int id);

    @Query("select p from Products p order by p.price")
    List<Products> findAAllOrderByPriceAsc();
}