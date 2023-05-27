package br.com.planilha.leitorapp.domain.city.exception;

public class CityException extends RuntimeException {

    private static final String MESSAGE = "Não foi possível atender sua requisição.";

    public CityException() {
        super(MESSAGE);
    }
}
