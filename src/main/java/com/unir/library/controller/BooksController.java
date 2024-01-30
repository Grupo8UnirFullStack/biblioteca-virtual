package com.unir.library.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
//@Tag(name = "Products Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre productos alojados en una base de datos en memoria.")
public class BooksController {
    @GetMapping("/books")
    @Operation(
            operationId = "Obtener productos",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los productos almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    public ResponseEntity<List<Product>> getProducts(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "name", description = "Nombre del producto. No tiene por que ser exacto", example = "iPhone", required = false)
            @RequestParam(required = false) String name,
            @Parameter(name = "country", description = "País del producto. Debe ser exacto", example = "ES", required = false)
            @RequestParam(required = false) String country,
            @Parameter(name = "description", description = "Descripcion del producto. No tiene por que ser exacta", example = "Estupendo", required = false)
            @RequestParam(required = false) String description,
            @Parameter(name = "visible", description = "Estado del producto. true o false", example = "true", required = false)
            @RequestParam(required = false) Boolean visible) {

        log.info("headers: {}", headers);
        List<Product> products = service.getProducts(name, country, description, visible);

        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
}
