package com.unir.library.data;

import com.unir.library.data.utils.SearchCriteria;
import com.unir.library.data.utils.SearchOperation;
import com.unir.library.data.utils.SearchStatement;
import com.unir.library.model.pojo.Gender;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenderRepository {
    private final GenderJpaRepository repository;

    public List<Gender> getGenders() {
        return repository.findAll();
    }

    public Gender getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Gender> search( String description) {
        SearchCriteria<Gender> spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(description)) {
            spec.add(new SearchStatement("description", description, SearchOperation.MATCH));
        }

        return repository.findAll(spec);
    }
}
