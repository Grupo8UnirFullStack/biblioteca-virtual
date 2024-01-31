package com.unir.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unir.library.data.BookRepository;
import com.unir.library.model.pojo.Book;
import com.unir.library.model.pojo.BookDto;
import com.unir.library.model.request.CreateBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Book> getBooks(String title, String isbn, String description, Integer year, Integer stock) {
        if (StringUtils.hasLength(title) || StringUtils.hasLength(isbn) || StringUtils.hasLength(description)
                || year != null || stock != null) {
            return repository.search( title,  isbn,  description,  year,  stock);
        }

        List<Book> products = repository.getBooks();
        return products.isEmpty() ? null : products;
    }

    @Override
    public Book getBook(String bookId) {
        return repository.getById(Long.valueOf(bookId));
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
