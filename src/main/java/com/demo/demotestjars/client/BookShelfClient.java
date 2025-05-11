package com.demo.demotestjars.client;

import com.demo.demotestjars.model.Book;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bookShelfClient", url = "${book.shelf.url}")
public interface BookShelfClient {

    @GetMapping("/api/books")
    List<Book> getAllBooks();

    @PostMapping("/api/books")
    Book addBook(@RequestBody Book book);

    @GetMapping("/api/books/{id}")
    Book getBook(@PathVariable Long id);
}