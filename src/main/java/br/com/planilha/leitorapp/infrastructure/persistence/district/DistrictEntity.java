package br.com.planilha.leitorapp.infrastructure.persistence.district;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "distritos")
public class DistrictEntity {

    @Id
    @Column(name = "id_distrito")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "id_microrregiao", nullable = false)
    private Long microregionId;

    @Column(name = "id_mesorregiao", nullable = false)
    private Long mesoregionId;

    @Column(name = "id_uf", nullable = false)
    private Long ufId;

    @Column(name = "id_regiao_imediata", nullable = false)
    private Long immediateRegionId;

    @Column(name = "id_regiao_intermediaria", nullable = false)
    private Long intermediateRegionId;
}
