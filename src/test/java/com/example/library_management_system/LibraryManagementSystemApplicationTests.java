package com.example.library_management_system;

import com.example.library_management_system.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryManagementSystemApplicationTests {

	@Test
	void testCreateBook() {
		//given
		Book book = new Book();
		//when
		book.setTitle("Test Book");
		book.setAuthor("Test Author");
		book.setIsbn("1234567890");
		book.setAvailableCopies(5);
		//then
		assert(book.getTitle().equals("Test Book"));
		assert(book.getAuthor().equals("Test Author"));
		assert(book.getIsbn().equals("1234567890"));
		assert(book.getAvailableCopies() == 5);

	}


}
