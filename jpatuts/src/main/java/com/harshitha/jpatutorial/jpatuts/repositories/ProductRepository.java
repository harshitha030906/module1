package com.harshitha.jpatutorial.jpatuts.repositories;

import com.harshitha.jpatutorial.jpatuts.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.Bidi;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findByTitle(String productTitle);

    List<Product> findByCreatedAtAfter(LocalDateTime createdAt);

    List<Product> findByTitleAndPrice(String title, BigDecimal price);

    @Query("select e from Product e where e.title=?1 and e.sku=?2")
    Optional<Product> findByTitleAndSku(String title, String sku);
}
