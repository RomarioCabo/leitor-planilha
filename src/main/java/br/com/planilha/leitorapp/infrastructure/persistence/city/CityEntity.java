package br.com.planilha.leitorapp.infrastructure.persistence.city;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cidades")
public class CityEntity {

    @Id
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
}
