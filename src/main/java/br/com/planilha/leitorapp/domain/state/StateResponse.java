package br.com.planilha.leitorapp.domain.state;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StateResponse {
    private Long id;
    private String acronym;
    private String name;
    private Long idRegion;
}
