package com.url.UrlShortening.service;

import com.url.UrlShortening.model.UrlModel;


public interface UrlService {

    UrlModel createHashedUrl(String originalUrl);

    String getOriginalUrl(String hashedUrl);

}
