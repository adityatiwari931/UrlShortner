package com.url.UrlShortening.service;

import com.url.UrlShortening.model.ClickModel;
import com.url.UrlShortening.model.UrlModel;
import com.url.UrlShortening.repository.ClickRepository;
import com.url.UrlShortening.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    private static final int MAX_USAGE_LIMIT = 10;
    private static final int DATE_EXPIRY_TIME = 30;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private ClickRepository clickRepository;


    @Override
    public UrlModel createHashedUrl(String originalUrl) {

        try {
            String hashedUrl = DigestUtils.sha256Hex(originalUrl);
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime expiryDateTime = currentDateTime.plusDays(DATE_EXPIRY_TIME);

            UrlModel url = new UrlModel();
            url.setOriginalUrl(originalUrl);
            url.setHashedUrl(hashedUrl);
            url.setExpirationDate(expiryDateTime);
            url.setUsageCount(0);

            return urlRepository.save(url);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public String getOriginalUrl(String hashedUrl) {

        Optional<UrlModel> urlOptional = urlRepository.findByHashedUrl(hashedUrl);

        if (urlOptional.isPresent()) {

            // Check if the URL has expired or exceeded its usage limit
            if (urlOptional.get().getUsageCount() >= MAX_USAGE_LIMIT || urlOptional.get().getExpirationDate().isBefore(LocalDateTime.now())) {
                return "Expired or usage limit reached!";
            }

            // Increment usage count
            urlOptional.get().setUsageCount(urlOptional.get().getUsageCount() + 1);
            urlRepository.save(urlOptional.get());

            // Log the click
            ClickModel click = new ClickModel();
            click.setHashedUrl(hashedUrl);
            click.setClickTimestamp(LocalDateTime.now());
            clickRepository.save(click);

            // Return the original URL
            return urlOptional.get().getOriginalUrl();
        } else {
            return "URL not found!";
        }

    }

}
