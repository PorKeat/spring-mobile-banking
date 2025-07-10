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
    private String nationalCardId;
    private Boolean isVerified;
    private Boolean isDeleted;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cus_id")
    private Customer customer;

}
