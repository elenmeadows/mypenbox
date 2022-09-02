package com.mypenbox.mpb.repo;

import com.mypenbox.mpb.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(CONCAT(p.brand, ' ', p.type, ' ', p.colorname, ' ')) LIKE %?1%" +
            "OR CONCAT(p.brand, ' ', p.type, ' ', p.colorname, ' ') LIKE %?1%" +
            "OR UPPER(CONCAT(p.brand, ' ', p.type, ' ', p.colorname, ' ')) LIKE %?1%")
    public Page<Product> findAll(String keyword, Pageable pageable);


    @Query("SELECT p FROM Product p WHERE LOWER(CONCAT(p.brand, ' ', p.type, ' ', p.colorname, ' ')) LIKE %?1%" +
            "OR CONCAT(p.brand, ' ', p.type, ' ', p.colorname, ' ') LIKE %?1%" +
            "OR UPPER(CONCAT(p.brand, ' ', p.type, ' ', p.colorname, ' ')) LIKE %?1%")
    public List<Product> findAll(String keyword, Sort sort);

    public List<Product> findAll(Sort sort);

}