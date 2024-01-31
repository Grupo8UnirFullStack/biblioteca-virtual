package com.unir.library.service;

import com.unir.library.model.pojo.Auth;
import com.unir.library.model.pojo.AuthDto;
import com.unir.library.model.request.CreateAuthRequest;


import java.util.List;

public interface AuthsService {
    List<Auth> getAuths(String name, String lastname);

    Auth getAuth(String AuthId);

    Boolean removeAuth(String AuthId);

    Auth createAuth(CreateAuthRequest request);

    Auth updateAuth(String AuthId, String updateRequest);

    Auth updateAuth(String AuthId, AuthDto updateRequest);
}
