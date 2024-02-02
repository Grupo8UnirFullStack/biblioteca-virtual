package com.unir.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.library.data.AuthRepository;
import com.unir.library.data.BookRepository;
import com.unir.library.model.pojo.Auth;
import com.unir.library.model.pojo.Book;
import com.unir.library.model.pojo.BookDto;
import com.unir.library.model.request.CreateBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Book> getBooks(String title, String isbn, String description, Integer year, Integer stock, String name, String lastname) {
        if (StringUtils.hasLength(title) || StringUtils.hasLength(isbn) || StringUtils.hasLength(description)
                || year != null || stock != null || name != null  || lastname != null) {
            log.info(name);
            List<Book> bookList  = new ArrayList<>();

                for(Auth auths: authRepository.search(name, lastname)){
                    bookList.addAll(repository.search(title, isbn, description, year, stock, auths.getId()));

            }

            return bookList;
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
        Book book = repository.getById(Long.valueOf(bookId));

        if (book != null) {
            repository.delete(book);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Book createBook(CreateBookRequest request) {
        //Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
        if (request != null && StringUtils.hasLength(request.getTitle().trim())
                && StringUtils.hasLength(request.getIsbn().trim())
                && StringUtils.hasLength(request.getDescription().trim())
                && request.getYear() != null && request.getStock() != null
                && request.getAuthid() != null && request.getImageid() != null
                && request.getGenderid() != null) {

            Book book = Book.builder()
                    .title(request.getTitle())
                    .isbn(request.getIsbn())
                    .description(request.getDescription())
                    .year(request.getYear())
                    .stock(request.getStock())
                    .authid(request.getAuthid())
                    .imageid(request.getImageid())
                    .genderid(request.getGenderid())
                    .build();

            return repository.save(book);
        } else {
            return null;
        }
    }

    @Override
    public Book updateBook(String bookId, String request) {
        //PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
        Book book = repository.getById(Long.valueOf(bookId));
        if (book != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(book)));
                Book patched = objectMapper.treeToValue(target, Book.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating product {}", bookId, e);
                return null;
            }
        } else {
            return null;
        }

    }

    @Override
    public Book updateBook(String bookId, BookDto updateRequest) {
        Book book = repository.getById(Long.valueOf(bookId));
        if (book != null) {
            book.update(updateRequest);
            repository.save(book);
            return book;
        } else {
            return null;
        }
    }
}
