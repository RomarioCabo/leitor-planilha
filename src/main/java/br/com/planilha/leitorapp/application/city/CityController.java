package br.com.planilha.leitorapp.application.city;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface CityController {

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "BAD_REQUEST"
                            //content = {@Content(schema = @Schema(implementation = ApiError.class))}
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL_SERVER_ERROR"
                            //content = {@Content(schema = @Schema(implementation = ApiError.class))}
                    ),
            }
    )
    ResponseEntity<Void> saveCities();
}
