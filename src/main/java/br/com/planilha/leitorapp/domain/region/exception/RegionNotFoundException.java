package br.com.planilha.leitorapp.domain.region.exception;

public class RegionNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Não foi possível atender sua requisição. ID informado %s não existe.";

    public RegionNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
    }
}
