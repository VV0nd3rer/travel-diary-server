package com.kverchi.diary.model.entity;

/**
 * Created by Liudmyla Melnychuk on 18.2.2019.
 */

import com.kverchi.diary.converter.CryptoConverter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

@Entity
@Table(name="oauth_client_credentials")
public class OauthClientCredentials {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="credentials_id")
    private int credentialsId;

    @Column(name="refresh_token")
    @Convert(converter = CryptoConverter.class)
    private String refreshToken;

    @Column(name="token_url")
    private String tokenUrl;

    @Column(name="oauth_client_id")
    @Convert(converter = CryptoConverter.class)
    private String oauthClientId;

    @Column(name="oauth_secret")
    @Convert(converter = CryptoConverter.class)
    private String oauthSecret;

    @Column(name="access_token")
    @Convert(converter = CryptoConverter.class)
    private String accessToken;

    @Column(name="token_expires")
    private long tokenExpires;

    @Column(name="credentials_email")
    @Convert(converter = CryptoConverter.class)
    private String credentialsEmail;

    public int getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(int credentialsId) {
        this.credentialsId = credentialsId;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getOauthClientId() {
        return oauthClientId;
    }

    public void setOauthClientId(String oauthClientId) {
        this.oauthClientId = oauthClientId;
    }

    public String getOauthSecret() {
        return oauthSecret;
    }

    public void setOauthSecret(String oauthSecret) {
        this.oauthSecret = oauthSecret;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getTokenExpires() {
        return tokenExpires;
    }

    public void setTokenExpires(long tokenExpires) {
        this.tokenExpires = tokenExpires;
    }

    public String getCredentialsEmail() {
        return credentialsEmail;
    }

    public void setCredentialsEmail(String credentialsEmail) {
        this.credentialsEmail = credentialsEmail;
    }
}
