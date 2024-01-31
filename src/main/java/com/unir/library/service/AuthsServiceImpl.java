package com.unir.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unir.library.data.AuthRepository;
import com.unir.library.model.pojo.Auth;
import com.unir.library.model.pojo.AuthDto;
import com.unir.library.model.request.CreateAuthRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class AuthsServiceImpl implements AuthsService {

    @Autowired
    private AuthRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Auth> getAuths(String name, String lastname) {
        if (StringUtils.hasLength(name) || StringUtils.hasLength(lastname)) {
            return repository.search( name, lastname);
        }

        List<Auth> auths = repository.getAuths();
        return auths.isEmpty() ? null : auths;
    }

    @Override
    public Auth getAuth(String AuthId) {
        return repository.getById(Long.valueOf(AuthId));
    }

    @Override
    public Boolean removeAuth(String AuthId) {
        return null;
    }

    @Override
    public Auth createAuth(CreateAuthRequest request) {
        return null;
    }

    @Override
    public Auth updateAuth(String AuthId, String updateRequest) {
        return null;
    }

    @Override
    public Auth updateAuth(String AuthId, AuthDto updateRequest) {
        return null;
    }
}
