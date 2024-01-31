package com.unir.library.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthDto {
    private String name;
    private String lastname;
}
