package com.demo.demotestjars.controller;

import com.demo.demotestjars.client.BookShelfClient;
import com.demo.demotestjars.model.Book;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final BookShelfClient bookShelfClient;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookShelfClient.getAllBooks();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookShelfClient.addBook(book);
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookShelfClient.getBook(id);
    }
}