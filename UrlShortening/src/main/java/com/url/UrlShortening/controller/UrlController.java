package com.url.UrlShortening.controller;

import com.url.UrlShortening.model.UrlModel;
import com.url.UrlShortening.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;


    @PostMapping("/generate")
    public ResponseEntity<UrlModel> createHashedUrl(@RequestParam String originalUrl) {
        UrlModel hashedUrl = urlService.createHashedUrl(originalUrl);
        if (hashedUrl != null) {
            return ResponseEntity.ok(hashedUrl);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @GetMapping("/get/{hashed-url}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable(name = "hashed-url") String hashedUrl) {
        String originalUrl = urlService.getOriginalUrl(hashedUrl);
        return ResponseEntity.ok(originalUrl);
    }



}
