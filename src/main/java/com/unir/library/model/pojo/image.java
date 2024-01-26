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
public class image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="path", unique = true)
    private String path;
}
