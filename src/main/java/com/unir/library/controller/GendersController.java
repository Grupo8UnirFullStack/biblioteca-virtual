package com.unir.library.controller;

import com.unir.library.model.pojo.Gender;
import com.unir.library.model.pojo.GenderDto;
import com.unir.library.model.request.CreateGenderRequest;
import com.unir.library.service.GendersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gender.class)))
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


    @PostMapping("/genders")
    @Operation(
            operationId = "Insertar un género",
            description = "Operacion de escritura",
            summary = "Se crea un género a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del género a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateGenderRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gender.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el producto con el identificador indicado.")
    public ResponseEntity<Gender> addGender(@RequestBody CreateGenderRequest request) {

        Gender createdGender = service.createGender(request);

        if (createdGender != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGender);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PatchMapping("/genders/{genderId}")
    @Operation(
            operationId = "Modificar parcialmente un género",
            description = "RFC 7386. Operacion de escritura",
            summary = "RFC 7386. Se modifica parcialmente un género.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del género a crear.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = String.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gender.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Producto inválido o datos incorrectos introducidos.")
    public ResponseEntity<Gender> patchGender(@PathVariable String genderId, @RequestBody String patchBody) {

        Gender patched = service.updateGender(genderId, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }



    @PutMapping("/genders/{genderId}")
    @Operation(
            operationId = "Modificar totalmente un género",
            description = "Operacion de escritura",
            summary = "Se modifica totalmente un género.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del género a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = GenderDto.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gender.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Género no encontrado.")
    public ResponseEntity<Gender> updateGender(@PathVariable String genderId, @RequestBody GenderDto body) {

        Gender updated = service.updateGender(genderId, body);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/genders/{genderId}")
    @Operation(
            operationId = "Eliminar un genero",
            description = "Operacion de escritura",
            summary = "Se elimina un genero a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el genero con el identificador indicado.")
    public ResponseEntity<Void> deleteGender(@PathVariable String genderId) {

        Boolean removed = service.removeGender(genderId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }




}
