package com.testContainers.testC;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


@Testcontainers
@SpringBootTest
//@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create")
class TestCApplicationTests {


	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
			.withUsername("duke")
			.withPassword("password")
			.withDatabaseName("test");

	@Autowired
	private BookRepository bookRepository;

	// requires Spring Boot >= 2.2.6
	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@BeforeEach
	void contextLoads() {
		Book book = new Book();
		book.setName("Testcontainers");

		bookRepository.save(book);
	}

	@Test
	void listBooks(){
		List<Book> booksCollection = bookRepository.findAll();
		assertFalse(booksCollection.isEmpty());
	}

}
