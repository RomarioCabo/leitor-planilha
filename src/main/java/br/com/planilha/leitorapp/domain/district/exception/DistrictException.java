package br.com.planilha.leitorapp.domain.district.exception;

public class DistrictException extends RuntimeException {

    private static final String MESSAGE = "Não foi possível atender sua requisição.";

    public DistrictException() {
        super(MESSAGE);
    }
}
