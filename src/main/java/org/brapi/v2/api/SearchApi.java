/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.13).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.brapi.v2.api;

import io.swagger.annotations.*;

import org.brapi.v2.model.CallListResponse;
import org.brapi.v2.model.CallSetsListResponse;
import org.brapi.v2.model.CallSetsSearchRequest;
import org.brapi.v2.model.CallsSearchRequest;
import org.brapi.v2.model.GermplasmListResponse;
import org.brapi.v2.model.GermplasmSearchRequest;
import org.brapi.v2.model.MarkerPositionListResponse;
import org.brapi.v2.model.MarkerPositionSearchRequest;
import org.brapi.v2.model.ReferenceListResponse;
import org.brapi.v2.model.ReferenceListResponse1;
import org.brapi.v2.model.SampleListResponse;
import org.brapi.v2.model.SampleSearchRequest;
import org.brapi.v2.model.SearchReferenceSetsRequest;
import org.brapi.v2.model.SearchReferencesRequest;
import org.brapi.v2.model.SuccessfulSearchResponse;
import org.brapi.v2.model.VariantListResponse;
import org.brapi.v2.model.VariantSetListResponse;
import org.brapi.v2.model.VariantSetsSearchRequest;
import org.brapi.v2.model.VariantsSearchRequest;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-19T12:30:08.794Z[GMT]")
@Api(value = "search", description = "the search API")
public interface SearchApi {

//    public static final String searchReferencesetsPost_url = CallsApi.URL_BASE_PREFIX + "/search/referencesets";
    public static final String searchCallsetsPost_url = ServerinfoApi.URL_BASE_PREFIX + "/search/callsets";
    public static final String searchVariantsetsPost_url = ServerinfoApi.URL_BASE_PREFIX + "/search/variantsets";
//    public static final String searchCallsPost_url = CallsApi.URL_BASE_PREFIX + "/search/calls";
    public static final String searchSamplesPost_url = ServerinfoApi.URL_BASE_PREFIX + "/search/samples";
    public static final String searchGermplasmPost_url = ServerinfoApi.URL_BASE_PREFIX + "/search/germplasm";

//	@ApiOperation(value = "Submit a search request for `Calls`", nickname = "searchCallsPost", notes = "`GET /callsets/{id}` will return a JSON version of `CallSet`.", response = SuccessfulSearchResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Calls", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = SuccessfulSearchResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = searchCallsPost_url,
//        produces = { "application/json" }, 
//        consumes = { "application/json" },
//        method = RequestMethod.POST)
//    ResponseEntity<SuccessfulSearchResponse> searchCallsPost(@ApiParam(value = "Study Search request"  )  @Valid @RequestBody CallsSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Returns a filtered list of `Call` JSON objects.", nickname = "searchCallsSearchResultsDbIdGet", notes = "Returns a filtered list of `Call` JSON objects. See Search Services for additional implementation details.", response = CallListResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Calls", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "A successful response.", response = CallListResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/calls/{searchResultsDbId}",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<CallListResponse> searchCallsSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


    @ApiOperation(value = "Gets a list of call sets matching the search criteria.", nickname = "searchCallsetsPost", notes = "`POST /callsets/search` must accept a JSON version of `SearchCallSetsRequest` as the post body and will return a JSON version of `SearchCallSetsResponse`.", response = SuccessfulSearchResponse.class, authorizations = {
        @Authorization(value = "AuthorizationToken")    }, tags={ "Call Sets", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SuccessfulSearchResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
    @RequestMapping(value = searchCallsetsPost_url,
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<CallSetsListResponse> searchCallsetsPost(@ApiParam(value = "Study Search request"  )  @Valid @RequestBody CallSetsSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Gets a list of call sets matching the search criteria.", nickname = "searchCallsetsSearchResultsDbIdGet", notes = "`POST /callsets/search` must accept a JSON version of `SearchCallSetsRequest` as the post body and will return a JSON version of `SearchCallSetsResponse`.", response = CallSetsListResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Call Sets", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "A successful response.", response = CallSetsListResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/callsets/{searchResultsDbId}",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<CallSetsListResponse> searchCallsetsSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Get marker position info", nickname = "searchMarkerpositionsPost", notes = "Get marker position information, based on Map, Linkage Group, and Marker ID", response = SuccessfulSearchResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Genome Maps", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = SuccessfulSearchResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/markerpositions",
//        produces = { "application/json" }, 
//        consumes = { "application/json" },
//        method = RequestMethod.POST)
//    ResponseEntity<SuccessfulSearchResponse> searchMarkerpositionsPost(@ApiParam(value = ""  )  @Valid @RequestBody MarkerPositionSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Get marker position info", nickname = "searchMarkerpositionsSearchResultsDbIdPost", notes = "Get marker position information, based on Map, Linkage Group, and Marker ID", response = MarkerPositionListResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Genome Maps", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = MarkerPositionListResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/markerpositions/{searchResultsDbId}",
//        produces = { "application/json" }, 
//        method = RequestMethod.POST)
//    ResponseEntity<MarkerPositionListResponse> searchMarkerpositionsSearchResultsDbIdPost(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Gets a list of `Reference` matching the search criteria.", nickname = "searchReferencesPost", notes = "`POST /references/search` must accept a JSON version of `SearchReferencesRequest` as the post body and will return a JSON version of `SearchReferencesResponse`.", response = SuccessfulSearchResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "References", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = SuccessfulSearchResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/references",
//        produces = { "application/json" }, 
//        consumes = { "application/json" },
//        method = RequestMethod.POST)
//    ResponseEntity<SuccessfulSearchResponse> searchReferencesPost(@ApiParam(value = "References Search request"  )  @Valid @RequestBody SearchReferencesRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Gets a list of `Reference` matching the search criteria.", nickname = "searchReferencesSearchResultsDbIdGet", notes = "`POST /references/search` must accept a JSON version of `SearchReferencesRequest` as the post body and will return a JSON version of `SearchReferencesResponse`.", response = ReferenceListResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "References", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "A successful response.", response = ReferenceListResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/references/{searchResultsDbId}",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<ReferenceListResponse> searchReferencesSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Gets a list of `ReferenceSet` matching the search criteria.", nickname = "searchReferencesetsPost", notes = "`POST /referencesets/search` must accept a JSON version of `SearchReferenceSetsRequest` as the post body and will return a JSON version of `SearchReferenceSetsResponse`.", response = SuccessfulSearchResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Reference Sets", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = SuccessfulSearchResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = searchReferencesetsPost_url,
//        produces = { "application/json" }, 
//        consumes = { "application/json" },
//        method = RequestMethod.POST)
//    ResponseEntity<SuccessfulSearchResponse> searchReferencesetsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody SearchReferenceSetsRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Gets a list of `ReferenceSet` matching the search criteria.", nickname = "searchReferencesetsSearchResultsDbIdGet", notes = "`POST /referencesets/search` must accept a JSON version of `SearchReferenceSetsRequest` as the post body and will return a JSON version of `SearchReferenceSetsResponse`.", response = ReferenceListResponse1.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Reference Sets", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "A successful response.", response = ReferenceListResponse1.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/referencesets/{searchResultsDbId}",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<ReferenceListResponse1> searchReferencesetsSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


    @ApiOperation(value = "Submit a search request for Samples", nickname = "searchSamplesPost", notes = "Used to retrieve list of Samples from a Sample Tracking system based on some search criteria.  See Search Services for additional implementation details.", response = SuccessfulSearchResponse.class, authorizations = {
        @Authorization(value = "AuthorizationToken")    }, tags={ "Samples", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SuccessfulSearchResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
    @RequestMapping(value = ServerinfoApi.URL_BASE_PREFIX + "/search/samples",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<SampleListResponse> searchSamplesPost(@ApiParam(value = ""  )  @Valid @RequestBody SampleSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Get the results of a Samples search request", nickname = "searchSamplesSearchResultsDbIdGet", notes = "Used to retrieve list of Samples from a Sample Tracking system based on some search criteria.  See Search Services for additional implementation details.", response = SampleListResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Samples", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = SampleListResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class),
//        @ApiResponse(code = 404, message = "Not Found", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/samples/{searchResultsDbId}",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<SampleListResponse> searchSamplesSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Gets a list of `Variant` matching the search criteria.", nickname = "searchVariantsPost", notes = "`POST /variants/search` must accept a JSON version of `SearchVariantsRequest` as the post body and will return a JSON version of `SearchVariantsResponse`.", response = SuccessfulSearchResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Variants", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "OK", response = SuccessfulSearchResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/variants",
//        produces = { "application/json" }, 
//        consumes = { "application/json" },
//        method = RequestMethod.POST)
//    ResponseEntity<SuccessfulSearchResponse> searchVariantsPost(@ApiParam(value = "Study Search request"  )  @Valid @RequestBody VariantsSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Gets a list of `Variant` matching the search criteria.", nickname = "searchVariantsSearchResultsDbIdGet", notes = "`POST /variants/search` must accept a JSON version of `SearchVariantsRequest` as the post body and will return a JSON version of `SearchVariantsResponse`.", response = VariantListResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Variants", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "A successful response.", response = VariantListResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/variants/{searchResultsDbId}",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<VariantListResponse> searchVariantsSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


    @ApiOperation(value = "Gets a list of `VariantSet` matching the search criteria.", nickname = "searchVariantsetsPost", notes = "`POST /variantsets/search` must accept a JSON version of `SearchVariantSetsRequest` as the post body and will return a JSON version of `SearchVariantSetsResponse`.", response = SuccessfulSearchResponse.class, authorizations = {
        @Authorization(value = "AuthorizationToken")    }, tags={ "Variant Sets", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SuccessfulSearchResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
    @RequestMapping(value = searchVariantsetsPost_url,
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<VariantSetListResponse> searchVariantsetsPost(@ApiParam(value = "Study Search request"  )  @Valid @RequestBody VariantSetsSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


//    @ApiOperation(value = "Gets a list of `VariantSet` matching the search criteria.", nickname = "searchVariantsetsSearchResultsDbIdGet", notes = "`POST /variantsets/search` must accept a JSON version of `SearchVariantSetsRequest` as the post body and will return a JSON version of `SearchVariantSetsResponse`.", response = VariantSetListResponse.class, authorizations = {
//        @Authorization(value = "AuthorizationToken")    }, tags={ "Variant Sets", })
//    @ApiResponses(value = { 
//        @ApiResponse(code = 200, message = "A successful response.", response = VariantSetListResponse.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
//    @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/variantsets/{searchResultsDbId}",
//        produces = { "application/json" }, 
//        method = RequestMethod.GET)
//    ResponseEntity<VariantSetListResponse> searchVariantsetsSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);


    @ApiOperation(value = "Submit a search request for Germplasm", nickname = "searchGermplasmPost", notes = "Search for a set of germplasm based on some criteria  Addresses these needs   - General germplasm search mechanism that accepts POST for complex queries   - Possibility to search germplasm by more parameters than those allowed by the existing germplasm search   - Possibility to get MCPD details by PUID rather than dbId  See Search Services for additional implementation details.", response = SuccessfulSearchResponse.class, authorizations = {
            @Authorization(value = "AuthorizationToken")    }, tags={ "Germplasm", })
        @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "OK", response = SuccessfulSearchResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 403, message = "Forbidden", response = String.class) })
        @RequestMapping(value = searchGermplasmPost_url,
            produces = { "application/json" }, 
            consumes = { "application/json" },
            method = RequestMethod.POST)
        ResponseEntity<GermplasmListResponse> searchGermplasmPost(HttpServletResponse response, @ApiParam(value = ""  )  @Valid @RequestBody GermplasmSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) throws Exception;


//        @ApiOperation(value = "Get the results of a Germplasm search request", nickname = "searchGermplasmSearchResultsDbIdGet", notes = "See Search Services for additional implementation details.  Addresses these needs:   1. General germplasm search mechanism that accepts POST for complex queries   2. possibility to search germplasm by more parameters than those allowed by the existing germplasm search   3. possibility to get MCPD details by PUID rather than dbId", response = GermplasmListResponse.class, authorizations = {
//            @Authorization(value = "AuthorizationToken")    }, tags={ "Germplasm", })
//        @ApiResponses(value = { 
//            @ApiResponse(code = 200, message = "OK", response = GermplasmListResponse.class),
//            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
//            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
//            @ApiResponse(code = 403, message = "Forbidden", response = String.class),
//            @ApiResponse(code = 404, message = "Not Found", response = String.class) })
//        @RequestMapping(value = CallsApi.URL_BASE_PREFIX + "/search/germplasm/{searchResultsDbId}",
//            produces = { "application/json" }, 
//            method = RequestMethod.GET)
//        ResponseEntity<GermplasmListResponse> searchGermplasmSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization);
}
