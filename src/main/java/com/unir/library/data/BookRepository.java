package com.unir.library.data;

import com.unir.library.data.utils.SearchCriteria;
import com.unir.library.data.utils.SearchOperation;
import com.unir.library.data.utils.SearchStatement;
import com.unir.library.model.pojo.Auth;
import com.unir.library.model.pojo.Book;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final BookJpaRepository repository;

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Book getById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public List<Book> search(String title, String isbn, String description, Integer year, Integer stock, Integer authid) {
        SearchCriteria<Book> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(title)) {
            spec.add(new SearchStatement("title", title, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(isbn)) {
            spec.add(new SearchStatement("isbn", isbn, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(description)) {
            spec.add(new SearchStatement("description", description, SearchOperation.MATCH));
        }

        if (year != null) {
            spec.add(new SearchStatement("year", year, SearchOperation.MATCH));
        }

        if (stock != null) {
            spec.add(new SearchStatement("stock", stock, SearchOperation.MATCH));
        }

        if (authid != null) {
            spec.add(new SearchStatement("authid", authid, SearchOperation.EQUAL));
        }
        return repository.findAll(spec);
    }
}
