package br.com.planilha.leitorapp.domain.client.estado;

import br.com.planilha.leitorapp.infrastructure.persistence.region.RegionEntity;
import br.com.planilha.leitorapp.infrastructure.persistence.state.StateEntity;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EstadoResponse {
    private long id;
    private String sigla;
    private String nome;
    private Regiao regiao;

    public RegionEntity toRegionEntity() {
        return RegionEntity.builder()
                .id(this.getRegiao().getId())
                .acronym(this.getRegiao().getSigla())
                .name(this.getRegiao().getNome())
                .build();
    }

    public StateEntity toStateEntity() {
        return StateEntity.builder()
                .id(this.getId())
                .acronym(this.getSigla())
                .name(this.getNome())
                .idRegion(this.getRegiao().getId())
                .build();
    }

    @Getter
    @Setter
    @Builder
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Regiao {
        private long id;
        private String sigla;
        private String nome;
    }
}
