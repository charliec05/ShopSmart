package com.shopsmart.cart.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.time.Duration;
import java.util.Map;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/carts")
public class CartController {
	private final StringRedisTemplate redisTemplate;

	public CartController(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@GetMapping("/{userId}")
	public Map<Object, Object> getCart(@PathVariable("userId") @NotBlank String userId) {
		HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
		return ops.entries(key(userId));
	}

	@PostMapping("/{userId}/items")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable("userId") String userId, @Valid @RequestBody Item body) {
		HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
		ops.increment(key(userId), body.productId(), body.qty());
		redisTemplate.expire(key(userId), Duration.ofHours(12));
	}

	@DeleteMapping("/{userId}/items/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable("userId") String userId, @PathVariable("productId") String productId) {
		HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
		ops.delete(key(userId), productId);
		redisTemplate.expire(key(userId), Duration.ofHours(12));
	}

	private String key(String userId) {
		return "cart:" + userId;
	}

	public record Item(@NotBlank String productId, int qty) {}
}
