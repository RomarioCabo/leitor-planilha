package br.com.planilha.leitorapp.infrastructure.persistence.region;

import br.com.planilha.leitorapp.domain.region.RegionResponse;
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
@Builder
@Table(name = "regioes")
public class RegionEntity {

    @Id
    @Column(name = "id_regiao")
    private Long id;

    @Column(name = "sigla", nullable = false)
    private String acronym;

    @Column(name = "nome", nullable = false)
    private String name;

    public RegionResponse toModel() {
        return RegionResponse.builder()
                .id(this.getId())
                .acronym(this.getAcronym())
                .name(this.getName())
                .build();
    }
}
