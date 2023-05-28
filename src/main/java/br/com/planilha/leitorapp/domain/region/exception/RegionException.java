package br.com.planilha.leitorapp.domain.region.exception;

public class RegionException extends RuntimeException {

    private static final String MESSAGE = "Não foi possível atender sua requisição.";

    public RegionException() {
        super(MESSAGE);
    }
}
