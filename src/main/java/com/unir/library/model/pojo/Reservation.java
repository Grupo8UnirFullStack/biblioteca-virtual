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
    private int id;

    @Id
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user_id;

    @Column(name = "reservation_date")
    private Date reservation_date;
}
