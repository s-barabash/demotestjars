package com.demo.demotestjars.model;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private int year;
}