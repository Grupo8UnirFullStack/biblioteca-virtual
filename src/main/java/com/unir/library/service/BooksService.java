package com.unir.library.service;

import com.unir.library.model.pojo.Auth;
import com.unir.library.model.pojo.Book;
import com.unir.library.model.pojo.BookDto;
import com.unir.library.model.request.CreateBookRequest;


import java.util.List;

public interface BooksService {
    List<Book> getBooks(String title, String isbn, String description, Integer year, Integer stock, String name, String lastname);

    Book getBook(String bookId);

    Boolean removeBook(String bookId);

    Book createBook(CreateBookRequest request);

    Book updateBook(String bookId, String updateRequest);

    Book updateBook(String bookId, BookDto updateRequest);
}
