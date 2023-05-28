package br.com.planilha.leitorapp.application.spreadsheet;

import br.com.planilha.leitorapp.application.exception.ApiError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SpreadsheetController {

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {@Content(schema = @Schema())}
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL_SERVER_ERROR",
                            content = {@Content(schema = @Schema(implementation = ApiError.class))}
                    )
            }
    )
    ResponseEntity<Void> saveAll(MultipartFile spreadsheet);
}
