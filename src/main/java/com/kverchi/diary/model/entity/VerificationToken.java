package com.kverchi.diary.model.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by Liudmyla Melnychuk on 26.3.2019.
 */
@Entity
@Table(name="verification_tokens")
@NamedEntityGraph(name = "verification-token-entity-graph", attributeNodes = {
        @NamedAttributeNode("user")
})
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private int tokenId;

    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expiration_date")
    private ZonedDateTime expirationDate;

    @Column(name = "is_valid")
    private boolean isValid;

    public VerificationToken() {}

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
    }


    private void calculateExpirationDate() {
        ZonedDateTime currentTime = ZonedDateTime.now();
        this.expirationDate = currentTime.plusHours(24);
    }
    @PrePersist
    public void beforePersist() {
        this.calculateExpirationDate();
        this.setValid(true);
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ZonedDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(ZonedDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
