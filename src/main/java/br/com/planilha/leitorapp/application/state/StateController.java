package br.com.planilha.leitorapp.application.state;

import br.com.planilha.leitorapp.application.exception.ApiError;
import br.com.planilha.leitorapp.domain.state.StateRequest;
import br.com.planilha.leitorapp.domain.state.StateResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StateController {
    
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "CREATED",
                            content = {@Content(schema = @Schema(implementation = StateResponse.class))}
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
    ResponseEntity<StateResponse> save(StateRequest stateRequest);

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {@Content(schema = @Schema(implementation = StateResponse.class))}
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
    ResponseEntity<StateResponse> update(Long id, StateRequest stateRequest);

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
                                    array = @ArraySchema(schema = @Schema(implementation = StateResponse.class))
                            )
                    )
            }
    )
    ResponseEntity<List<StateResponse>> getAll();
}
