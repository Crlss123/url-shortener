package com.crls.urlshortener.url;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class URLController {

  private final URLService urlService;

  @PostMapping("/register")
  public ResponseEntity<URLResponse> registerUrl(@RequestBody URLRequest urlRequest) {
    return ResponseEntity.ok(urlService.registerUrl(urlRequest.url()));
  }

  @GetMapping("/{code}")
  public ResponseEntity<Void> redirect (@PathVariable String code) {
    String originalUrl = urlService.getUrl(code);
    return ResponseEntity
        .status(HttpStatus.FOUND)
        .location(URI.create(originalUrl))
        .build();
  }


}
