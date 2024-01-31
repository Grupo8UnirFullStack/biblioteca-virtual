package com.unir.library.model.request;

import com.unir.library.model.pojo.Auth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private String name;
    private String country;
    private String description;
    private Boolean visible;
    private Auth auth;
}
