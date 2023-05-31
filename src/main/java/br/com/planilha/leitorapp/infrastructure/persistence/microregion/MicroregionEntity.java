package br.com.planilha.leitorapp.infrastructure.persistence.microregion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "micro_regiao")
public class MicroregionEntity {

    @Id
    @Column(name = "id_micro_regiao")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "id_mesorregiao", nullable = false)
    private Long mesoregionId;

    @Column(name = "id_uf", nullable = false)
    private Long ufId;
}
