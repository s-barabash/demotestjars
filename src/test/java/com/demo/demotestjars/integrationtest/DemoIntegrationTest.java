package com.demo.demotestjars.integrationtest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.demo.demotestjars.config.DemoAppConfig;
import com.demo.demotestjars.model.Book;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import({DemoAppConfig.class})
public class DemoIntegrationTest {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void testGetBooks_extractAndAssert() {

    Book newBook = new Book();
    newBook.setTitle("Test Driven Development");
    newBook.setAuthor("Kent Beck");
    newBook.setYear(2003);

    given()
        .contentType("application/json")
        .body(newBook)
        .when()
        .post("/demo/books")
        .then()
        .statusCode(200);

    // Act: Call GET /demo/books and extract list
    List<Book> books = given()
        .when()
        .get("/demo/books")
        .then()
        .statusCode(200)
        .extract()
        .as(new TypeRef<>() {
        });

    // Assert: Check that the new book is in the list
    assertThat(books)
        .isNotEmpty()
        .anySatisfy(book -> {
          assertThat(book.getTitle()).isEqualTo(newBook.getTitle());
          assertThat(book.getAuthor()).isEqualTo(newBook.getAuthor());
          assertThat(book.getYear()).isEqualTo(newBook.getYear());
        });
  }
}
