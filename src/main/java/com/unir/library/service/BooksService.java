package com.unir.library.service;

import com.unir.library.model.pojo.Book;
import com.unir.library.model.pojo.BookDto;
import com.unir.library.model.request.CreateBookRequest;


import java.util.List;

public interface BooksService {
    List<Book> getBooks(String title, String isbn, String description, int year, int stock);

    Book getBook(String bookId);

    Boolean removeBook(String bookId);

    Book createBook(CreateBookRequest request);

    Book updateBook(String bookId, String updateRequest);

    Book updateBook(String bookId, BookDto updateRequest);
}
