package com.sirmiss.home.journals.test;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissKoResponse {
  private int id;
  private String name;
  private String description;
}
