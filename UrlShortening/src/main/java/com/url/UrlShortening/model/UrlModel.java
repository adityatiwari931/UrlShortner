package com.url.UrlShortening.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_model")
public class UrlModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "hashed_url")
    private String hashedUrl;

    @Column(name = "usage_count")
    private int usageCount;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getHashedUrl() {
        return hashedUrl;
    }

    public void setHashedUrl(String hashedUrl) {
        this.hashedUrl = hashedUrl;
    }


    public int getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    // No args constructor
    public UrlModel() {
    }

    // all args constructor
    public UrlModel(Long id, String originalUrl, String hashedUrl, int maxUsageLimit, int usageCount, LocalDateTime expirationDate) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.hashedUrl = hashedUrl;
        this.usageCount = usageCount;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlModel{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", hashedUrl='" + hashedUrl + '\'' +
                ", usageCount=" + usageCount +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
