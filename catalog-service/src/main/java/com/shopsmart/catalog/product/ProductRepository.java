package com.shopsmart.catalog.product;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {
	Optional<Product> findBySku(String sku);
	Page<Product> findByNameContainingIgnoreCase(String query, Pageable pageable);
}
