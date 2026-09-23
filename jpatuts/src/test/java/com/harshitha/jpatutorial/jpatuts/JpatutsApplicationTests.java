package com.harshitha.jpatutorial.jpatuts;


import com.harshitha.jpatutorial.jpatuts.entites.Product;
import com.harshitha.jpatutorial.jpatuts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpatutsApplicationTests {

	@Autowired
    ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository(){
		Product product = Product.builder()
				.price(BigDecimal.valueOf(200.00))
				.quantity(30)
				.sku("cadbury")
				.title("chocolate")
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.build();

		Product saved = productRepository.save(product);
		System.out.println(saved);
	}

	@Test
	void getRepository(){
		List<Product> products = productRepository.findByTitle("coca cola");
		System.out.println(products);
	}

	@Test
	void testTwoRepository(){
		List<Product> products = productRepository.findByTitleAndPrice("coca cola", BigDecimal.valueOf(30));
		System.out.println(products);
	}

	@Test
	void testCreatedBy(){
		List<Product> products = productRepository.findByCreatedAtAfter(LocalDateTime.of(2024,1,1,0,0,0));
		System.out.println(products);
	}

	@Test
	void testUpdatedBy(){
		Optional<Product> product = productRepository.findByTitleAndSku("coca cola", "coke");
		System.out.println(product);
	}
}
