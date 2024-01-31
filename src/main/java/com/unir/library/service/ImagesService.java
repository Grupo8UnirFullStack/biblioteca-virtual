package com.unir.library.service;

import com.unir.library.model.pojo.Image;
import com.unir.library.model.pojo.ImageDto;
import com.unir.library.model.request.CreateImageRequest;

import java.util.List;

public interface ImagesService {
    List<Image> getImages(String path);

    Image getImage(String imageId);

    Boolean removeImage(String imageId);

    Image createImage (CreateImageRequest request);

    Image updateImage(String ImageId, String updateRequest);

    Image updateImage(String imageId, ImageDto updateRequest);
}
