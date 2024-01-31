package com.unir.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
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
    public Boolean removeGender(String genderId) {

        Gender gender = repository.getById(Long.valueOf(genderId));

        if (gender != null) {
            repository.delete(gender);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Gender createGender(CreateGenderRequest request) {

        //Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
        if (request != null && StringUtils.hasLength(request.getDescription().trim()) ) {

            Gender gender = Gender.builder().description(request.getDescription()).build();

            return repository.save(gender);
        } else {
            return null;
        }
    }

    @Override
    public Gender updateGender(String genderId, String request) {
        //PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
        Gender product = repository.getById(Long.valueOf(genderId));
        if (product != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(product)));
                Gender patched = objectMapper.treeToValue(target, Gender.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating product {}", genderId, e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Gender updateGender(String genderId, GenderDto updateRequest) {
        Gender gender = repository.getById(Long.valueOf(genderId));
        if (gender != null) {
            gender.update(updateRequest);
            repository.save(gender);
            return gender;
        } else {
            return null;
        }
    }
}
