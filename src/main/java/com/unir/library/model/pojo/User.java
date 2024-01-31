package com.unir.library.model.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "dni")
    private String dni;

    @Column(name = "birth_date")
    private Date birth_date;
}
