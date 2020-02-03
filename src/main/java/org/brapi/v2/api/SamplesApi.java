/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.13).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.brapi.v2.api;

import io.swagger.annotations.*;

import org.brapi.v2.model.SampleListResponse;
import org.brapi.v2.model.SampleNewRequest;
import org.brapi.v2.model.SampleSingleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-19T12:30:12.318Z[GMT]")
@Api(value = "samples", description = "the samples API")
public interface SamplesApi {

//    @ApiOperation(value = "Get the Samples", nickname = "samplesGet", notes = "Used to retrieve list of Samples from a Sample Tracking system based on some search criteria.", response = SampleListResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Samples", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = SampleListResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/samples",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<SampleListResponse> samplesGet(@ApiParam(value = "the internal DB id for a sample") @Valid @RequestParam(value = "sampleDbId", required = false) String sampleDbId,@ApiParam(value = "the internal DB id for an observation unit where a sample was taken from") @Valid @RequestParam(value = "observationUnitDbId", required = false) String observationUnitDbId,@ApiParam(value = "the internal DB id for a plate of samples") @Valid @RequestParam(value = "plateDbId", required = false) String plateDbId,@ApiParam(value = "the internal DB id for a germplasm") @Valid @RequestParam(value = "germplasmDbId", required = false) String germplasmDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);
//
//
//    @ApiOperation(value = "Add new Samples", nickname = "samplesPost", notes = "Call to register the event of a sample being taken. Sample ID is assigned as a result of the operation and returned in response.", response = SampleListResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Samples", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = SampleListResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/samples",
//        produces = { "application/json" }, 
//        consumes = { "application/json" },
//        method = RequestMethod.POST)
//    ResponseEntity<SampleListResponse> samplesPost(@ApiParam(value = ""  )  @Valid @RequestBody List<SampleNewRequest> body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);
//
//
//    @ApiOperation(value = "Get the details of a specific Sample", nickname = "samplesSampleDbIdGet", notes = "Used to retrieve the details of a single Sample from a Sample Tracking system.", response = SampleSingleResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Samples", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = SampleSingleResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class),
//        @ApiResponse(code = 404, message = "Not Found", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/samples/{sampleDbId}",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<SampleSingleResponse> samplesSampleDbIdGet(@ApiParam(value = "the internal DB id for a sample",required=true) @PathVariable("sampleDbId") String sampleDbId,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);
//
//
//    @ApiOperation(value = "Update the details of an existing Sample", nickname = "samplesSampleDbIdPut", notes = "Update the details of an existing Sample", response = SampleSingleResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Samples", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = SampleSingleResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class),
//        @ApiResponse(code = 404, message = "Not Found", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/samples/{sampleDbId}",
//        produces = { "application/json" }, 
//        consumes = { "application/json" },
//        method = RequestMethod.PUT)
//    ResponseEntity<SampleSingleResponse> samplesSampleDbIdPut(@ApiParam(value = "the internal DB id for a sample",required=true) @PathVariable("sampleDbId") String sampleDbId,@ApiParam(value = ""  )  @Valid @RequestBody SampleNewRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);

}
