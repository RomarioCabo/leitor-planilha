package br.com.planilha.leitorapp.domain.region;

import java.util.List;

public interface RegionService {

    RegionResponse upsert(Long id, RegionRequest regionRequest);

    void delete(Long id);

    List<RegionResponse> getAll();
}
