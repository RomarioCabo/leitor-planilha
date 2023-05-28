package br.com.planilha.leitorapp.domain.city;

import br.com.planilha.leitorapp.infrastructure.persistence.city.CityEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CityRequest {

    @NotNull
    private Long idCityWorksheet;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String shortName;

    @NotNull
    @NotBlank
    private String latitude;

    @NotNull
    @NotBlank
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
