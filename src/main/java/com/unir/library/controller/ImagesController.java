package com.unir.library.controller;

import com.unir.library.model.pojo.Image;
import com.unir.library.model.pojo.ImageDto;
import com.unir.library.model.request.CreateImageRequest;
import com.unir.library.service.ImagesService;
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
@Tag(name = "Images Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre imagenes alojadas en una base de datos en memoria.")
public class ImagesController {

    private final ImagesService service;

    @GetMapping("/images")
    @Operation(
            operationId = "Obtener imagenes",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos las imagenes almacenadas en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Image.class)))
    public ResponseEntity<List<Image>> getImages(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "path", description = "Path del libro")
            @RequestParam(required = false) String path)

    {

        log.info("headers: {}", headers);
        List<Image> images = service.getImages(path);

        return ResponseEntity.ok(Objects.requireNonNullElse(images, Collections.emptyList()));
    }


    @GetMapping("/image/{imageId}")
    @Operation(
            operationId = "Obtener una imagen",
            description = "Operacion de lectura",
            summary = "Se devuelve una imagen a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Image.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema()),
            description = "No se ha encontrado la imagen con el identificador indicado.")
    public ResponseEntity<Image> getImage(@PathVariable String imageId) {

        log.info("Request received for image {}", imageId);
        Image image = service.getImage(imageId);

        if (image != null) {
            return ResponseEntity.ok(image);
        } else {
            log.info("No se ha encontrado la imagen");
            return ResponseEntity.notFound().build();
        }

    }
    @PatchMapping("/images/{imageId}")
    @Operation(
            operationId = "Modificar parcialmente una imagen",
            description = "RFC 7386. Operacion de escritura",
            summary = "RFC 7386. Se modifica parcialmente una imagen.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la imagen a crear.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = String.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Image.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Imagen inválida o datos incorrectos introducidos.")
    public ResponseEntity<Image> patchProduct(@PathVariable String imageId, @RequestBody String patchBody) {

        Image patched = service.updateImage(imageId, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/images/{imageId}")
    @Operation(
            operationId = "Modificar totalmente una imagen",
            description = "Operacion de escritura",
            summary = "Se modifica totalmente una imagen.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la imagen a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = ImageDto.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Image.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Imagen no encontrada.")
    public ResponseEntity<Image> updateImage(@PathVariable String imageId, @RequestBody ImageDto body) {

        Image updated = service.updateImage(imageId, body);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/images")
    @Operation(
            operationId = "Insertar una imagen",
            description = "Operacion de escritura",
            summary = "Se crea una imagen a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la imagen a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateImageRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Image.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la imagen con el identificador indicado.")
    public ResponseEntity<Image> addProduct(@RequestBody CreateImageRequest request) {

        Image createdProduct = service.createImage(request);

        if (createdProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/images/{imageId}")
    @Operation(
            operationId = "Eliminar una imagen",
            description = "Operacion de escritura",
            summary = "Se elimina una imagen a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la imagen con el identificador indicado.")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageId) {

        Boolean removed = service.removeImage(imageId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
