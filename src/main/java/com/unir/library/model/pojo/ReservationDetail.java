package com.unir.library.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reservation_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReservationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "book_id")
    private int book_id;
}
