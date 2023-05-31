package br.com.planilha.leitorapp.domain.microregion;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MicroregionResponse {
    private Long id;
    private String name;
    private Long mesoregionId;
    private Long ufId;
}
