package kh.edu.banking.api.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true,length = 32)
    private String accountNumber;

    @Column(nullable = false,length = 50)
    private String accountCurrency;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "cus_id",referencedColumnName = "id") //rename column & Optional: reference primary key of customer table
    private Customer customer; //customer_id

    @OneToMany(mappedBy = "sender")
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> receivedTransactions;

    @ManyToOne
    @JoinColumn(name = "acc_type_id")
    private AccountType accountType;

}
