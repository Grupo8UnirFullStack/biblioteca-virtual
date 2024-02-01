package com.unir.library.controller;

import com.unir.library.model.pojo.Auth;
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
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(required = false)
            String title,
            @Parameter(name = "isbn", description = "Codigo unico del libro")
            @RequestParam(required = false)
            String isbn,
            @Parameter(name = "description", description = "Descripcion del libro")
            @RequestParam(required = false)
            String description,
            @Parameter(name = "year", description = "Anio de publicacion del libro")
            @RequestParam(required = false)
            Integer year,
            @Parameter(name = "stock", description = "Existencias del libro")
            @RequestParam(required = false)
            Integer stock,
            @Parameter(name = "name", description = "Nombre del autor del libro")
            @RequestParam(required = false)
            String name,
            @Parameter(name = "lastname", description = "ID del autor del libro")
            @RequestParam(required = false)
            String lastname)
    {

        log.info("headers: {}", headers);
        List<Book> products = service.getBooks(title, isbn, description, year, stock, name, lastname);

        return ResponseEntity.ok(Objects.requireNonNullElse(products, Collections.emptyList()));
    }


    @GetMapping("/books/{bookId}")
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
