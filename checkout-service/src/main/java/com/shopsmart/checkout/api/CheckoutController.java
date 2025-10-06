package com.shopsmart.checkout.api;

import jakarta.validation.constraints.NotBlank;
import java.time.Duration;
import java.util.UUID;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
	private final StringRedisTemplate redis;
	private final KafkaTemplate<String, String> kafka;

	public CheckoutController(StringRedisTemplate redis, KafkaTemplate<String, String> kafka) {
		this.redis = redis;
		this.kafka = kafka;
	}

	@PostMapping("/{userId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Result checkout(
			@PathVariable("userId") @NotBlank String userId,
			@RequestHeader(value = "Idempotency-Key", required = false) String idemKey) {
		String key = "checkout-idem:" + (idemKey == null ? UUID.randomUUID() : idemKey);
		Boolean ok = redis.opsForValue().setIfAbsent(key, "1", Duration.ofMinutes(10));
		if (ok == null || !ok) {
			return new Result("duplicate");
		}
		String event = "{\"userId\":\"" + userId + "\",\"status\":\"PaymentAuthorized\"}";
		kafka.send("checkout.events.v1", userId, event);
		return new Result("accepted");
	}

	public record Result(String status) {}
}
