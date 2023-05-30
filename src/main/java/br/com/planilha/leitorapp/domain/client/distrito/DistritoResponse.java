package br.com.planilha.leitorapp.domain.client.distrito;

import br.com.planilha.leitorapp.domain.client.estado.EstadoResponse;
import br.com.planilha.leitorapp.infrastructure.persistence.district.DistrictEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DistritoResponse {

    private long id;
    private String nome;
    private Municipio municipio;

    public DistrictEntity toDistrictEntity() {
        return DistrictEntity.builder()
                .id(this.getId())
                .name(this.getNome())
                .microregionId(this.getMunicipio().getMicrorregiao().getId())
                .mesoregionId(this.getMunicipio().getMicrorregiao().getMesorregiao().getId())
                .ufId(this.getMunicipio().getMicrorregiao().getMesorregiao().getUf().getId())
                .immediateRegionId(this.getMunicipio().getRegiaoImediata().getId())
                .intermediateRegionId(this.getMunicipio().getRegiaoImediata().getRegiaoIntermediaria().getId())
                .build();
    }

    @Getter
    @Setter
    @Builder
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Municipio {
        private long id;
        private String nome;
        private Microrregiao microrregiao;
        @JsonProperty("regiao-imediata")
        private RegiaoImediata regiaoImediata;
    }

    @Getter
    @Setter
    @Builder
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Microrregiao {
        private long id;
        private String nome;
        private Mesorregiao mesorregiao;
    }

    @Getter
    @Setter
    @Builder
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Mesorregiao {
        private long id;
        private String nome;
        @JsonProperty("UF")
        private EstadoResponse uf;
    }

    @Getter
    @Setter
    @Builder
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegiaoImediata {
        private long id;
        private String nome;
        @JsonProperty("regiao-intermediaria")
        private RegiaoIntermediaria regiaoIntermediaria;
    }

    @Getter
    @Setter
    @Builder
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegiaoIntermediaria {
        private long id;
        private String nome;
        @JsonProperty("UF")
        private EstadoResponse uf;
    }
}
