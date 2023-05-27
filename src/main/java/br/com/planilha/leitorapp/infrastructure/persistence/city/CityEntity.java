package br.com.planilha.leitorapp.infrastructure.persistence.city;

import br.com.planilha.leitorapp.domain.city.City;
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

    public City toCity() {
        return City.builder()
                .id(this.getId())
                .idCityWorksheet(this.getIdCityWorksheet())
                .name(this.getName())
                .shortName(this.getShortName())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .build();
    }
}
