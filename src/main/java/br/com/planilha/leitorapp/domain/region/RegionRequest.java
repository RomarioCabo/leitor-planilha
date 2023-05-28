package br.com.planilha.leitorapp.domain.region;

import br.com.planilha.leitorapp.infrastructure.persistence.region.RegionEntity;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegionRequest {
    private String acronym;
    private String name;

    public RegionEntity toEntity(Long id) {
        return RegionEntity.builder()
                .id(id)
                .acronym(this.getAcronym())
                .name(this.getName())
                .build();
    }
}
