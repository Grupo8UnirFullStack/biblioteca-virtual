package com.unir.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unir.library.data.GenderRepository;
import com.unir.library.model.pojo.Gender;
import com.unir.library.model.pojo.GenderDto;
import com.unir.library.model.request.CreateGenderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class GendersServiceImpl implements GendersService {

    @Autowired
    private GenderRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Gender> getGenders( String description) {
        if (StringUtils.hasLength(description)) {
            return repository.search( description);
        }

        List<Gender> products = repository.getGenders();
        return products.isEmpty() ? null : products;
    }

    @Override
    public Gender getGender(String genderId) {
        return repository.getById(Long.valueOf(genderId));
    }

    @Override
    public Boolean removeGender(String bookId) {
        return null;
    }

    @Override
    public Gender createGender(CreateGenderRequest request) {
        return null;
    }

    @Override
    public Gender updateGender(String bookId, String updateRequest) {
        return null;
    }

    @Override
    public Gender updateGender(String bookId, GenderDto updateRequest) {
        return null;
    }
}
