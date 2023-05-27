package br.com.planilha.leitorapp.application.city;

import br.com.planilha.leitorapp.application.exception.ApiError;
import br.com.planilha.leitorapp.domain.city.City;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CityController {

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {@Content(schema = @Schema(implementation = City[].class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "BAD_REQUEST",
                            content = {@Content(schema = @Schema(implementation = ApiError.class))}
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL_SERVER_ERROR",
                            content = {@Content(schema = @Schema(implementation = ApiError.class))}
                    ),
            }
    )
    ResponseEntity<List<City>> saveCities(MultipartFile spreadsheet);

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = City.class))
                            )
                    )
            }
    )
    ResponseEntity<List<City>> getAllCities();
}
