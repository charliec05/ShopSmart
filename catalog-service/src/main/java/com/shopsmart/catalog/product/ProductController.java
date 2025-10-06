package com.shopsmart.catalog.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
	private final ProductRepository repository;

	public ProductController(ProductRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Page<Product> list(
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
			@RequestParam(value = "size", defaultValue = "20") @Min(1) int size) {
		Pageable pageable = PageRequest.of(page, size);
		if (query == null || query.isBlank()) {
			return repository.findAll(pageable);
		}
		return repository.findByNameContainingIgnoreCase(query, pageable);
	}

	@GetMapping("/{id}")
	public Product get(@PathVariable("id") UUID id) {
		return repository.findById(id).orElseThrow();
	}

	@PostMapping
	public Product create(@Valid @RequestBody Product body) {
		if (body.getId() == null) {
			body.setId(UUID.randomUUID());
		}
		return repository.save(body);
	}

	@PatchMapping("/{id}")
	public Product update(
			@PathVariable("id") UUID id,
			@Valid @RequestBody Product body) {
		Product existing = repository.findById(id).orElseThrow();
		if (body.getName() != null && !body.getName().isBlank()) existing.setName(body.getName());
		existing.setDescription(body.getDescription());
		if (body.getPriceCents() > 0) existing.setPriceCents(body.getPriceCents());
		if (body.getStockQty() >= 0) existing.setStockQty(body.getStockQty());
		return repository.save(existing);
	}
}
