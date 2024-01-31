package com.unir.library.controller;

import com.unir.library.model.pojo.Auth;
import com.unir.library.model.pojo.Book;
import com.unir.library.service.AuthsService;
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
@Tag(name = "Auth Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre autores alojados en una base de datos.")
public class AuthController {
    private final AuthsService service;

    @GetMapping("/auths")
    @Operation(
            operationId = "Obtener autores",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los autores almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Auth.class)))
    public ResponseEntity<List<Auth>> getAuths(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "name", description = "Nombre del autor")
            @RequestParam(required = false)
            String name,
            @Parameter(name = "lastname", description = "Nombre del autor")
            @RequestParam(required = false)
            String lastname) {

        log.info("headers: {}", headers);
        List<Auth> auths = service.getAuths(name, lastname);

        return ResponseEntity.ok(Objects.requireNonNullElse(auths, Collections.emptyList()));
    }


    @GetMapping("/auth/{authId}")
    @Operation(
            operationId = "Obtener un autor",
            description = "Operacion de lectura",
            summary = "Se devuelve un autor a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema()),
            description = "No se ha encontrado el autor con el identificador indicado.")
    public ResponseEntity<Auth> getAuth(@PathVariable String authId) {

        log.info("Request received for auth {}", authId);
        Auth auth = service.getAuth(authId);

        if (auth != null) {
            return ResponseEntity.ok(auth);
        } else {
            log.info("No se ha encontrado el auth");
            return ResponseEntity.notFound().build();
        }

    }
}
