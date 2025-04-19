package com.zenthrex.caravan.controller.api;

import com.zenthrex.core.dtos.CaravanDto;
import com.zenthrex.core.dtos.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface CaravanApi {

    @Operation(
            operationId = "createCaravan",
            summary = "create new caravan",
            description = "create new caravan",
            tags = { "caravans" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created"),
                    @ApiResponse(responseCode = "default", description = "error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,

            value = "/api/v1/sellers/caravans",
            produces = { "application/json" },
            consumes = { "application/json" }
    )

    ResponseEntity<Void> createCaravan(
            @Parameter(name = "CaravanDto", description = "", required = true) @Valid @RequestBody CaravanDto caravanDto
    );


    /**
     * GET /api/v1/sellers/caravans/{id} : get caravan by id
     * get caravan by id
     *
     * @param id caravan ID (required)
     * @return success (status code 200)
     *         or error (status code 200)
     */
    @Operation(
            operationId = "getCaravan",
            summary = "get caravan by id",
            description = "get caravan by id",
            tags = { "caravans" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CaravanDto.class))
                    }),
                    @ApiResponse(responseCode = "default", description = "error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/v1/sellers/caravans/{id}",
            produces = { "application/json" }
    )

    ResponseEntity<CaravanDto> getCaravan(
            @Parameter(name = "id", description = "caravan ID", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) ;


    /**
     * GET /api/v1/sellers/caravans : list of caravan
     * list of caravans
     *
     * @return success (status code 200)
     *         or error (status code 200)
     */
    @Operation(
            operationId = "getCaravans",
            summary = "list of caravan",
            description = "list of caravans",
            tags = { "caravans" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CaravanDto.class)))
                    }),
                    @ApiResponse(responseCode = "default", description = "error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/v1/sellers/caravans",
            produces = { "application/json" }
    )

     ResponseEntity<List<CaravanDto>> getCaravans();


    /**
     * DELETE /api/v1/sellers/caravans/{id} : Remove a caravan
     *
     * @param id caravan ID (required)
     * @return success (status code 200)
     *         or error (status code 200)
     */
    @Operation(
            operationId = "removeCaravan",
            summary = "Remove a caravan",
            tags = { "caravans" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success"),
                    @ApiResponse(responseCode = "default", description = "error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/api/v1/sellers/caravans/{id}",
            produces = { "application/json" }
    )

    ResponseEntity<Void> removeCaravan(
            @Parameter(name = "id", description = "caravan ID", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    );


    /**
     * PUT /api/v1/sellers/caravans/{id} : Update a caravans
     *
     * @param id caravan ID (required)
     * @param caravanDto  (required)
     * @return success (status code 200)
     *         or error (status code 200)
     */
    @Operation(
            operationId = "updateCaravan",
            summary = "Update a caravans",
            tags = { "caravans" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success"),
                    @ApiResponse(responseCode = "default", description = "error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/api/v1/sellers/caravans/{id}",
            produces = { "application/json" },
            consumes = { "application/json" }
    )

    ResponseEntity<Void> updateCaravan(
            @Parameter(name = "id", description = "caravan ID", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
            @Parameter(name = "CaravanDto", description = "", required = true) @Valid @RequestBody CaravanDto caravanDto
    );
}
