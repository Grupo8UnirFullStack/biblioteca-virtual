package com.unir.library.model.pojo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="path", unique = true)
    private String path;
}
