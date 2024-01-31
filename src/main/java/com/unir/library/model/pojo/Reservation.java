package com.unir.library.model.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name="reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Id
    @ManyToOne
    @JoinColumn(name="userid", referencedColumnName = "id")
    private User userid;

    @Column(name = "reservationdate")
    private Date reservationdate;
}
