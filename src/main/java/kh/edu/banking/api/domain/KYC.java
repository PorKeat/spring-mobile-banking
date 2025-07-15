package kh.edu.banking.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// known your customer
@Getter
@Setter
@NoArgsConstructor
@Entity
public class KYC {

    @Id
    private Integer id; // UUID

    @Column(unique = true, nullable = false, length = 50)
    private String nationalCardId;


    @Column(nullable = false)
    private Boolean isVerified;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cus_id")
    private Customer customer;

}
