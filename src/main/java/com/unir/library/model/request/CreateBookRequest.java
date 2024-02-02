package com.unir.library.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private String title;
    private String isbn;
    private String description;
    private Integer year;
    private Integer stock;
    private Integer authid;
    private Integer imageid;
    private Integer genderid;
}
