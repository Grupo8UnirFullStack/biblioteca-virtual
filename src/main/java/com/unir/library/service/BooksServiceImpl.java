package com.unir.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unir.library.data.BookRepository;
import com.unir.library.model.pojo.Book;
import com.unir.library.model.pojo.BookDto;
import com.unir.library.model.request.CreateBookRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BooksServiceImpl implements BooksService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Book> getBooks(String title, String isbn, String description, int year, int stock) {
        return null;
    }

    @Override
    public Book getBook(String bookId) {
        return null;
    }

    @Override
    public Boolean removeBook(String bookId) {
        return null;
    }

    @Override
    public Book createBook(CreateBookRequest request) {
        return null;
    }

    @Override
    public Book updateBook(String bookId, String updateRequest) {
        return null;
    }

    @Override
    public Book updateBook(String bookId, BookDto updateRequest) {
        return null;
    }
}
