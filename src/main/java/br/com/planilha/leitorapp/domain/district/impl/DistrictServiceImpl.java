package br.com.planilha.leitorapp.domain.district.impl;

import br.com.planilha.leitorapp.domain.client.distrito.DistritoResponse;
import br.com.planilha.leitorapp.domain.district.DistrictService;
import br.com.planilha.leitorapp.domain.district.exception.DistrictException;
import br.com.planilha.leitorapp.domain.log.LogMessage;
import br.com.planilha.leitorapp.domain.provider.FeignProvider;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DistrictServiceImpl extends LogMessage implements DistrictService {

    private final PersistenceProvider provider;
    private final FeignProvider feignProvider;

    @Override
    public void saveAll() {
        try {
            List<DistritoResponse> distritos = feignProvider.getDistritos();
            provider.saveAllDistrict(distritos);
        } catch (Exception e) {
            logMessageError(e);
            throw new DistrictException();
        }
    }
}
