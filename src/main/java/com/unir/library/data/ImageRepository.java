package com.unir.library.data;

import com.unir.library.data.ImageJpaRepository;
import com.unir.library.data.utils.SearchCriteria;
import com.unir.library.data.utils.SearchOperation;
import com.unir.library.data.utils.SearchStatement;
import com.unir.library.model.pojo.Gender;
import com.unir.library.model.pojo.Image;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImageRepository {
    private final ImageJpaRepository repository;

    public List<Image> getImages() {
        return repository.findAll();
    }

    public Image getById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public List<Image> search(String path) {
        SearchCriteria<Image> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(path)) {
            spec.add(new SearchStatement("path", path, SearchOperation.MATCH));
        }
        return repository.findAll(spec);
    }
    public void delete(Image image) {
        repository.delete(image);
    }

    public Image save(Image image) {
        return repository.save(image);
    }
}
