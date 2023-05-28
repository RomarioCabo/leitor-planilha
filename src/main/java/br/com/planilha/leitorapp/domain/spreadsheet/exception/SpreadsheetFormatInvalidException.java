package br.com.planilha.leitorapp.domain.spreadsheet.exception;

public class SpreadsheetFormatInvalidException extends RuntimeException {

    private static final String MESSAGE = "O arquivo deve estar no formato .xlsx.";

    public SpreadsheetFormatInvalidException() {
        super(MESSAGE);
    }
}
