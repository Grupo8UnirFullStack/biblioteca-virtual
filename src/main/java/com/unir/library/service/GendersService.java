package com.unir.library.service;

import com.unir.library.model.pojo.Gender;
import com.unir.library.model.pojo.GenderDto;
import com.unir.library.model.request.CreateGenderRequest;

import java.util.List;

public interface GendersService {
    List<Gender> getGenders(String description);

    Gender getGender(String genderId);

    Boolean removeGender(String genderId);

    Gender createGender(CreateGenderRequest request);

    Gender updateGender(String genderId, String updateRequest);

    Gender updateGender(String genderId, GenderDto updateRequest);
}
