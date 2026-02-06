package com.crls.urlshortener.url;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
@Table(name = "url")
public class URL {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String code;

  @Column(nullable = false)
  private String originalUrl;

}
