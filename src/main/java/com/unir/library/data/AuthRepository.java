package com.unir.library.data;

import com.unir.library.data.utils.SearchCriteria;
import com.unir.library.data.utils.SearchOperation;
import com.unir.library.data.utils.SearchStatement;
import com.unir.library.model.pojo.Auth;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthRepository {
    private final AuthJpaRepository repository;

    public List<Auth> getAuths() {
        return repository.findAll();
    }

    public Auth getById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public List<Auth> search(String name, String lastname) {
        SearchCriteria<Auth> spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(name)) {
            spec.add(new SearchStatement("name", name, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(lastname)) {
            spec.add(new SearchStatement("lastname", lastname, SearchOperation.MATCH));
        }

        return repository.findAll(spec);
    }
}
