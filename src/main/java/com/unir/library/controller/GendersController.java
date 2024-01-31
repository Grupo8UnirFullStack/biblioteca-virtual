package com.unir.library.controller;

import com.unir.library.model.pojo.Book;
import com.unir.library.model.pojo.Gender;
import com.unir.library.service.GendersService;
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
@Tag(name = "Géneros Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre generos alojados en una base de datos en memoria.")
public class GendersController {

    private final GendersService service;

    @GetMapping("/genders")
    @Operation(
            operationId = "Obtener generos",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los generos almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    public ResponseEntity<List<Gender>> getGenders(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "description", description = "Nombre del Género de Pélicula")
            @RequestParam(required = false) String description)
    {

        log.info("headers: {}", headers);
        List<Gender> genders = service.getGenders(description);

        return ResponseEntity.ok(Objects.requireNonNullElse(genders, Collections.emptyList()));
    }


    @GetMapping("/gender/{genderId}")
    @Operation(
            operationId = "Obtener un género",
            description = "Operacion de lectura",
            summary = "Se devuelve un género a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gender.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema()),
            description = "No se ha encontrado el género con el identificador indicado.")
    public ResponseEntity<Gender> getGender(@PathVariable String genderId) {

        log.info("Request received for product {}", genderId);
        Gender gender = service.getGender(genderId);

        if (gender != null) {
            return ResponseEntity.ok(gender);
        } else {
            log.info("No se ha encontrado el libro");
            return ResponseEntity.notFound().build();
        }

    }
}
