package br.com.planilha.leitorapp.domain.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class LogMessage {

    private static final String ERROR_MESSAGE = "Error: %s";

    public void logMessageError(Exception e) {
        log.info(String.format(ERROR_MESSAGE, e.getMessage()), e);
    }
}
