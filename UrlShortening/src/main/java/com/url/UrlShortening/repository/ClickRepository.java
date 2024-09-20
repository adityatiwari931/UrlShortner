package com.url.UrlShortening.repository;

import com.url.UrlShortening.model.ClickModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickRepository extends JpaRepository<ClickModel, Long> {
}
