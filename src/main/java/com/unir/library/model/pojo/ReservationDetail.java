package com.unir.library.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reservationdetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReservationDetail {

    @Id
    @OneToOne
    @JoinColumn(name = "reservationid", referencedColumnName = "id")
    private Reservation reservationId;

    @Id
    @OneToOne
    @JoinColumn(name = "bookid", referencedColumnName = "id")
    private Book bookd;
}
