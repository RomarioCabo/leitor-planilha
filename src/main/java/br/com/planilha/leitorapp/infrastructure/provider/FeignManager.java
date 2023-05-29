package br.com.planilha.leitorapp.infrastructure.provider;

import br.com.planilha.leitorapp.domain.provider.FeignProvider;
import br.com.planilha.leitorapp.domain.state.json.EstadoResponse;
import br.com.planilha.leitorapp.infrastructure.provider.ibge.IbgeClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class FeignManager implements FeignProvider {

    private final RetryTemplate retryTemplate;

    private final IbgeClient ibgeClient;

    @Override
    public List<EstadoResponse> getEstados() {
        return retryTemplate.execute(callback -> {
            log.debug("Request /IBGE Estados {}", callback.getRetryCount() + 1);
            return ibgeClient.getEstados();
        });
    }
}
