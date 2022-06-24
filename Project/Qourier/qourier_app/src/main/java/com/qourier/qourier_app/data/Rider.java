package com.qourier.qourier_app.data;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "rider")
public class Rider {

    @Id private String email;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_email")
    private Account account;

    private String citizenId;

    private Long currentDelivery;

    public Rider(Account account, String citizenId) {
        account.setRole(AccountRole.RIDER);
        this.account = account;
        this.citizenId = citizenId;
        this.currentDelivery = null;
    }

    public Rider() {}
}
