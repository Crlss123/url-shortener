package com.crls.urlshortener.url;

import com.crls.urlshortener.encoder.Base62EncoderService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class URLService {

  private final URLRepository urlRepository;
  private final Base62EncoderService base62EncoderService;
  private final URLProperties urlProperties;

  public String getUrl(String code) {
    URL url = urlRepository.findURLByCode(code)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found."));

    return url.getOriginalUrl();
  }

  public URLResponse registerUrl(String url) {
    var newShortUrl = URL.builder().originalUrl(url).build();
    newShortUrl = urlRepository.save(newShortUrl);
    String code = base62EncoderService.encode(Integer.toString(newShortUrl.getId()));
    newShortUrl.setCode(code);
    urlRepository.save(newShortUrl);

    return new URLResponse(
        urlProperties.getApi() + "api/url/" + code
    );

  }


}
