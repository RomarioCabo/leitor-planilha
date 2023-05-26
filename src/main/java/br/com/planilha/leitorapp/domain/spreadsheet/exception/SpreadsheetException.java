package br.com.planilha.leitorapp.domain.spreadsheet.exception;

public class SpreadsheetException extends RuntimeException {

    private static final String MESSAGE = "Não foi possível ler a planilha enviada.";

    public SpreadsheetException() {
        super(MESSAGE);
    }
}
