package br.com.planilha.leitorapp.domain.microregion.exception;

public class MicroregionException extends RuntimeException {

    private static final String MESSAGE = "Não foi possível atender sua requisição.";

    public MicroregionException() {
        super(MESSAGE);
    }
}
