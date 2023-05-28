package br.com.planilha.leitorapp.application.region;

import br.com.planilha.leitorapp.application.exception.ApiError;
import br.com.planilha.leitorapp.domain.region.RegionRequest;
import br.com.planilha.leitorapp.domain.region.RegionResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegionController {
    
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "CREATED",
                            content = {@Content(schema = @Schema(implementation = RegionResponse.class))}
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
    ResponseEntity<RegionResponse> save(RegionRequest regionRequest);

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {@Content(schema = @Schema(implementation = RegionResponse.class))}
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
    ResponseEntity<RegionResponse> update(Long id, RegionRequest regionRequest);

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
                                    array = @ArraySchema(schema = @Schema(implementation = RegionResponse.class))
                            )
                    )
            }
    )
    ResponseEntity<List<RegionResponse>> getAll();
}
