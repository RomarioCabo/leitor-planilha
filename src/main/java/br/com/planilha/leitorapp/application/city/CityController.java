package br.com.planilha.leitorapp.application.city;

import br.com.planilha.leitorapp.application.exception.ApiError;
import br.com.planilha.leitorapp.domain.city.CityRequest;
import br.com.planilha.leitorapp.domain.city.CityResponse;
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
                            responseCode = "201",
                            description = "CREATED",
                            content = {@Content(schema = @Schema(implementation = CityResponse.class))}
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
    ResponseEntity<CityResponse> save(CityRequest cityRequest);

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {@Content(schema = @Schema(implementation = CityResponse.class))}
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
    ResponseEntity<CityResponse> update(Long id, CityRequest cityRequest);

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

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {@Content(schema = @Schema())}
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
    ResponseEntity<Void> delete(Long id);

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CityResponse.class))
                            )
                    )
            }
    )
    ResponseEntity<List<CityResponse>> getAll(Integer page, Integer elementsPerPage);
}
