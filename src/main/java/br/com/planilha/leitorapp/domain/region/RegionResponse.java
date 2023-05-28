package br.com.planilha.leitorapp.domain.region;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegionResponse {
    private Long id;
    private String acronym;
    private String name;
}
