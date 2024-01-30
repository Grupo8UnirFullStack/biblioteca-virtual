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
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private int year;

    @Column(name = "stock")
    private int stock;

    @OneToOne
    @JoinColumn(name="image_id", referencedColumnName = "id")
    private Image image;

    @OneToOne
    @JoinColumn(name="auth_id", referencedColumnName = "id")
    private Auth auth;

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private Gender gender_id;
}
