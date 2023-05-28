package br.com.planilha.leitorapp.infrastructure.persistence.state;

import br.com.planilha.leitorapp.domain.state.StateResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estados")
public class StateEntity {

    @Id
    @Column(name = "id_estado")
    private Long id;

    @Column(name = "sigla", nullable = false)
    private String acronym;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "id_regiao")
    private Long idRegion;

    public StateResponse toModel() {
        return StateResponse.builder()
                .id(this.getId())
                .acronym(this.getAcronym())
                .name(this.getName())
                .idRegion(this.getIdRegion())
                .build();
    }
}
