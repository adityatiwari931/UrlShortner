package com.url.UrlShortening.repository;

import com.url.UrlShortening.model.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlModel, Long> {

    Optional<UrlModel> findByHashedUrl(String hashedUrl);

}
