package com.unir.library.data;

import com.unir.library.model.pojo.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

import com.unir.library.model.pojo.Book;

interface BookJpaRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findByTitle(String title);

    List<Book> findByIsbn(String isbn);

    List<Book> findByDescription(String description);

    List<Book> findByYear(Integer year);

    List<Book> findByAuthid(Integer auth);

    List<Book> findByGenderid(Integer auth);
}