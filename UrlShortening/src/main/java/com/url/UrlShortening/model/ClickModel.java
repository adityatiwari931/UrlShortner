package com.url.UrlShortening.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "click_model")
public class ClickModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "hashed_url")
    private String hashedUrl;

    @Column(name = "click_timestamp")
    private LocalDateTime clickTimestamp;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashedUrl() {
        return hashedUrl;
    }

    public void setHashedUrl(String hashedUrl) {
        this.hashedUrl = hashedUrl;
    }

    public LocalDateTime getClickTimestamp() {
        return clickTimestamp;
    }

    public void setClickTimestamp(LocalDateTime clickTimestamp) {
        this.clickTimestamp = clickTimestamp;
    }

    // No args constructor
    public ClickModel() {
    }

    // all args constructor
    public ClickModel(Long id, String hashedUrl, LocalDateTime clickTimestamp) {
        this.id = id;
        this.hashedUrl = hashedUrl;
        this.clickTimestamp = clickTimestamp;
    }

    @Override
    public String toString() {
        return "ClickModel{" +
                "id=" + id +
                ", hashedUrl='" + hashedUrl + '\'' +
                ", clickTimestamp=" + clickTimestamp +
                '}';
    }

}
