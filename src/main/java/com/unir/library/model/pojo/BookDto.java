package com.unir.library.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDto {
    private String title;
    private String isbn;
    private String description;
    private Integer year;
    private Integer stock;
}
