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
    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation_id;

    @Id
    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book_id;
}
