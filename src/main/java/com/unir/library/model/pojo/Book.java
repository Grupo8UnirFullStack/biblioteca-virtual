package com.unir.library.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private Integer year;

    @Column(name = "stock")
    private Integer stock;

    @JoinColumn(name="imageid", referencedColumnName = "id")
    private Integer imageid;

    @JoinColumn(name="authid", referencedColumnName = "id")
    private Integer authid;

    @JoinColumn(name = "genderid", referencedColumnName = "id")
    private Integer genderid;
}
