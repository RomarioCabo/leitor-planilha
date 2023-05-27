package br.com.planilha.leitorapp.infrastructure.persistence.city;

import br.com.planilha.leitorapp.domain.city.CityResponse;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cidades")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "id_cidade")
    private Long id;

    @Column(name = "id_cidade_planilha", nullable = false)
    private Long idCityWorksheet;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "nome_abreviado", nullable = false)
    private String shortName;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    public CityResponse toCity() {
        return CityResponse.builder()
                .id(this.getId())
                .idCityWorksheet(this.getIdCityWorksheet())
                .name(this.getName())
                .shortName(this.getShortName())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .build();
    }
}
