package com.crls.urlshortener.encoder;

import io.seruco.encoding.base62.Base62;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Base62EncoderService {

  private final Base62 base62;

  public String encode(String str){
    byte[] code = base62.encode(str.getBytes());

    return new String(code);
  }

}
