package br.com.planilha.leitorapp.domain.city;

import br.com.planilha.leitorapp.infrastructure.persistence.city.CityEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CityRequest {

    private Long idCityWorksheet;
    private String name;
    private String shortName;
    private String latitude;
    private String longitude;

    public CityEntity toEntity(Long id) {
        return CityEntity.builder()
                .id(id)
                .idCityWorksheet(this.getIdCityWorksheet())
                .name(this.getName())
                .shortName(this.getShortName())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .build();
    }
}
