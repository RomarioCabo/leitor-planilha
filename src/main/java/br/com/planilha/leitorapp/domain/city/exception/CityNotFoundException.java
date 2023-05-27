package br.com.planilha.leitorapp.domain.city.exception;

public class CityNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Não foi possível atender sua requisição. ID informado %s não existe.";

    public CityNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
    }
}
