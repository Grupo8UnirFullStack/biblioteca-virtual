package com.unir.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unir.library.data.ImageRepository;
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
        return null;
    }

    @Override
    public Image updateImage(String imageId, String updateRequest) {
        return null;
    }

    @Override
    public Image updateImage(String imageId, ImageDto updateRequest) {
        return null;
    }
}
