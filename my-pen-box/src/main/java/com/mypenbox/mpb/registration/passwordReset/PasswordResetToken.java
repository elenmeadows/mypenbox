package com.mypenbox.mpb.registration.passwordReset;

import com.mypenbox.mpb.models.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PasswordResetToken {

    @SequenceGenerator(name = "password_reset_token_sequence", sequenceName = "password_reset_token_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_reset_token_sequence")
    private Long passwordTokenId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private Boolean wasUsed = false;

    @ManyToOne
    @JoinColumn(nullable = false, name = "account_id")
    private Account account;

    public PasswordResetToken(String token,
                              LocalDateTime createdAt,
                              LocalDateTime expiresAt,
                              Account account) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.account = account;
    }
}
