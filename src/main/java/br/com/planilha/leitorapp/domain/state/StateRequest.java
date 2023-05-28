package br.com.planilha.leitorapp.domain.state;

import br.com.planilha.leitorapp.infrastructure.persistence.state.StateEntity;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StateRequest {
    private String acronym;
    private String name;
    private Long idRegion;

    public StateEntity toEntity(Long id) {
        return StateEntity.builder()
                .id(id)
                .acronym(this.getAcronym())
                .name(this.getName())
                .idRegion(this.getIdRegion())
                .build();
    }
}
