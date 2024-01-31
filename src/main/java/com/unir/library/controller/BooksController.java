package com.unir.library.controller;

import com.unir.library.model.pojo.Book;
import com.unir.library.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Products Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre productos alojados en una base de datos en memoria.")
public class BooksController {

    private final BooksService service;

    @GetMapping("/books")
    @Operation(
            operationId = "Obtener productos",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los productos almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    public ResponseEntity<List<Book>> getBooks(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "title", description = "Titulo del libro")
            String title,
            @Parameter(name = "isbn", description = "Codigo unico del libro")
            String isbn,
            @Parameter(name = "description", description = "Descripcion del libro")
            String description,
            @Parameter(name = "year", description = "Anio de publicacion del libro")
            Integer year,
            @Parameter(name = "stock", description = "Existencias del libro")
            Integer stock)
    {

        log.info("headers: {}", headers);
        List<Book> products = service.getBooks( title,  isbn,  description,  year,  stock);

        return ResponseEntity.ok(Objects.requireNonNullElse(products, Collections.emptyList()));
    }


    @GetMapping("/book/{bookId}")
    @Operation(
            operationId = "Obtener un producto",
            description = "Operacion de lectura",
            summary = "Se devuelve un producto a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema()),
            description = "No se ha encontrado el producto con el identificador indicado.")
    public ResponseEntity<Book> getBook(@PathVariable String bookId) {

        log.info("Request received for product {}", bookId);
        Book product = service.getBook(bookId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            log.info("No se ha encontrado el libro");
            return ResponseEntity.notFound().build();
        }

    }
}
