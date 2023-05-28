package br.com.planilha.leitorapp.domain.state.exception;

public class StateException extends RuntimeException {

    private static final String MESSAGE = "Não foi possível atender sua requisição.";

    public StateException() {
        super(MESSAGE);
    }
}
