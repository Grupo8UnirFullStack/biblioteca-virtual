package com.unir.library.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="auth")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class auth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name", unique = true)
    private String name;

    @Column(name="last_name")
    private String last_name;

}