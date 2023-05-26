package br.com.planilha.leitorapp.domain.city;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class City {

    private Long id;
    private Long idCityWorksheet;
    private String name;
    private String shortName;
    private String latitude;
    private String longitude;
}
