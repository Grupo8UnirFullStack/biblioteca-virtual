package com.unir.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.library.data.ImageRepository;
import com.unir.library.model.pojo.Gender;
import com.unir.library.model.pojo.Image;
import com.unir.library.model.pojo.ImageDto;
import com.unir.library.model.request.CreateImageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Image> getImages(String path) {
        if (StringUtils.hasLength(path) || path != null) {
            return repository.search(path);
        }

        List<Image> products = repository.getImages();
        return products.isEmpty() ? null : products;
    }

    @Override
    public Image getImage(String imageId) {
        return repository.getById(Long.valueOf(imageId));
    }

    @Override
    public Boolean removeImage(String imageId) {
        return null;
    }

    @Override
    public Image createImage(CreateImageRequest request) {
        if (request != null && StringUtils.hasLength(request.getPath().trim()) ) {

            Image image = Image.builder().path(request.getPath()).build();

            return repository.save(image);
        } else {
            return null;
        }
    }

    @Override
    public Image updateImage(String imageId, String request) {
        Image image = repository.getById(Long.valueOf(imageId));
        if (image != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(image)));
                Image patched = objectMapper.treeToValue(target, Image.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating product {}", imageId, e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Image updateImage(String imageId, ImageDto updateRequest){
    Image image = repository.getById(Long.valueOf(imageId));
        if (image != null) {
        image.update(updateRequest);
        repository.save(image);
        return image;
    } else {
        return null;
    }
}
}
