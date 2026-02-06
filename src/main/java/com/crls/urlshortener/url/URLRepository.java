package com.crls.urlshortener.url;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface URLRepository extends JpaRepository<URL,Integer> {

  Optional<URL> findURLByCode(String code);
}
