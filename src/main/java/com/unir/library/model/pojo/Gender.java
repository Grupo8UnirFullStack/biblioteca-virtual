package com.unir.library.model.pojo;

//import javax.persistence.Column;
import jakarta.persistence.*;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;

import lombok.*;

@Entity
@Table(name="gender1")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="description", unique = true)
    private String description;

}
