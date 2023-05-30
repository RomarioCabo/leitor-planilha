package br.com.planilha.leitorapp.domain.district;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DistrictResponse {
    private Long id;
    private String name;
    private Long microregionId;
    private Long mesoregionId;
    private Long ufId;
    private Long immediateRegionId;
    private Long intermediateRegionId;
}
