package br.com.planilha.leitorapp.domain.state.exception;

public class StateNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Não foi possível atender sua requisição. ID informado %s não existe.";

    public StateNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
    }
}
