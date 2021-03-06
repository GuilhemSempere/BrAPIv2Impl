package org.brapi.v2.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBCursor;

import fr.cirad.controller.GigwaMethods;
import fr.cirad.mgdb.model.mongo.maintypes.GenotypingSample;
import fr.cirad.mgdb.model.mongo.maintypes.Individual;
import fr.cirad.mgdb.service.GigwaGa4ghServiceImpl;
import fr.cirad.mgdb.service.GigwaGa4ghServiceImpl.SearchCallSetsResponseWrapper;
import fr.cirad.model.GigwaSearchVariantsRequest;
import fr.cirad.tools.mongo.MongoTemplateManager;
import fr.cirad.tools.security.base.AbstractTokenManager;
import fr.cirad.web.controller.rest.BrapiRestController;
import io.swagger.annotations.*;
import jhi.brapi.api.germplasm.BrapiGermplasm;

import org.brapi.v2.model.Analysis;
import org.brapi.v2.model.CallListResponse;
import org.brapi.v2.model.CallSet;
import org.brapi.v2.model.CallSetsListResponse;
import org.brapi.v2.model.CallSetsListResponseResult;
import org.brapi.v2.model.CallSetsSearchRequest;
import org.brapi.v2.model.CallsSearchRequest;
import org.brapi.v2.model.Germplasm;
import org.brapi.v2.model.GermplasmListResponse;
import org.brapi.v2.model.GermplasmListResponseResult;
import org.brapi.v2.model.GermplasmMCPD;
import org.brapi.v2.model.GermplasmSearchRequest;
import org.brapi.v2.model.MarkerPositionListResponse;
import org.brapi.v2.model.MarkerPositionSearchRequest;
import org.brapi.v2.model.Metadata;
import org.brapi.v2.model.Pagination;
import org.brapi.v2.model.ReferenceListResponse;
import org.brapi.v2.model.ReferenceListResponse1;
import org.brapi.v2.model.Sample;
import org.brapi.v2.model.SampleListResponse;
import org.brapi.v2.model.SampleListResponseResult;
import org.brapi.v2.model.SampleSearchRequest;
import org.brapi.v2.model.SearchReferenceSetsRequest;
import org.brapi.v2.model.SearchReferencesRequest;
import org.brapi.v2.model.Status;
import org.brapi.v2.model.SuccessfulSearchResponse;
import org.brapi.v2.model.VariantListResponse;
import org.brapi.v2.model.VariantSet;
import org.brapi.v2.model.VariantSetListResponse;
import org.brapi.v2.model.VariantSetListResponseResult;
import org.brapi.v2.model.VariantSetsSearchRequest;
import org.brapi.v2.model.VariantsSearchRequest;
import org.brapi.v2.model.GermplasmNewRequest.BiologicalStatusOfAccessionCodeEnum;
import org.ga4gh.methods.SearchCallSetsRequest;
import org.ga4gh.methods.SearchCallSetsResponse;
import org.ga4gh.methods.SearchVariantSetsRequest;
import org.ga4gh.methods.SearchVariantSetsResponse;
import org.ga4gh.models.VariantSetMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeParseException;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-19T12:30:08.794Z[GMT]")
@CrossOrigin
@RestController
public class SearchApiController implements SearchApi {

    private static final Logger log = LoggerFactory.getLogger(SearchApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired private GigwaGa4ghServiceImpl ga4ghService;
    
    @Autowired private BrapiRestController brapiV1Service;
    
    @Autowired AbstractTokenManager tokenManager;

    @org.springframework.beans.factory.annotation.Autowired
    public SearchApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

//    public ResponseEntity<SuccessfulSearchResponse> searchCallsPost(@ApiParam(value = "Study Search request"  )  @Valid @RequestBody CallsSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<SuccessfulSearchResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"searchResultsDbId\" : \"551ae08c\"\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", SuccessfulSearchResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<SuccessfulSearchResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    public ResponseEntity<CallListResponse> searchCallsSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<CallListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"genotype_likelihood\" : [ 0.8008281904610115, 0.8008281904610115 ],\n      \"phaseset\" : \"phaseset\",\n      \"callSetName\" : \"callSetName\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"callSetDbId\" : \"callSetDbId\",\n      \"variantDbId\" : \"variantDbId\",\n      \"variantName\" : \"variantName\",\n      \"genotype\" : {\n        \"values\" : [ \"\", \"\" ]\n      }\n    }, {\n      \"genotype_likelihood\" : [ 0.8008281904610115, 0.8008281904610115 ],\n      \"phaseset\" : \"phaseset\",\n      \"callSetName\" : \"callSetName\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"callSetDbId\" : \"callSetDbId\",\n      \"variantDbId\" : \"variantDbId\",\n      \"variantName\" : \"variantName\",\n      \"genotype\" : {\n        \"values\" : [ \"\", \"\" ]\n      }\n    } ],\n    \"unknownString\" : \"unknownString\",\n    \"expandHomozygotes\" : true,\n    \"sepPhased\" : \"sepPhased\",\n    \"sepUnphased\" : \"sepUnphased\"\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", CallListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<CallListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public ResponseEntity<CallSetsListResponse> searchCallsetsPost(@ApiParam(value = "Study Search request"  )  @Valid @RequestBody CallSetsSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
    	String token = ServerinfoApiController.readToken(authorization);
    	
        try {
        	CallSetsListResponse cslr = new CallSetsListResponse();
        	CallSetsListResponseResult result = new CallSetsListResponseResult();
			Metadata metadata = new Metadata();
			cslr.setMetadata(metadata);
			
        	boolean fAllowedToReadAnything = false;
        	
			if (body.getVariantSetDbIds() == null || body.getVariantSetDbIds().isEmpty()) {
				Status status = new Status();
				status.setMessage("Some variantSetDbIds must be specified as parameter!");
				metadata.addStatusItem(status);
				return new ResponseEntity<CallSetsListResponse>(cslr, HttpStatus.BAD_REQUEST);
			}

        	if (body.getVariantSetDbIds().size() != 1)
				throw new Exception("You may only ask for callset records from a single variantSet at a time!");    	
        	
        	int nTotalCallSetsEncountered = 0;
    		String variantSetDbId = body.getVariantSetDbIds().get(0);
        	String[] splitId = variantSetDbId.split(GigwaGa4ghServiceImpl.ID_SEPARATOR);
        	SearchCallSetsResponseWrapper v1responseWrapper = (SearchCallSetsResponseWrapper) ga4ghService.searchCallSets(new SearchCallSetsRequest(variantSetDbId, null, body.getPageSize(), integerToString(body.getPage())));
        	SearchCallSetsResponse v1response = v1responseWrapper.getResponse();
        	List<org.ga4gh.models.CallSet> ga4ghCallSets = v1response.getCallSets();
        	nTotalCallSetsEncountered += ga4ghCallSets.size();
    		for (final org.ga4gh.models.CallSet ga4ghCallSet : ga4ghCallSets)
    			if (tokenManager.canUserReadProject(token, splitId[0], Integer.parseInt(splitId[1]))) {
    				fAllowedToReadAnything = true;
	            	result.addDataItem(new CallSet() {{
	            			setCallSetDbId(ga4ghCallSet.getId());
	            			setCallSetName(ga4ghCallSet.getName());
	            			setVariantSetIds(ga4ghCallSet.getVariantSetIds());    			
		            		for (String infoKey : ga4ghCallSet.getInfo().keySet())
		            			putAdditionalInfoItem(infoKey, ga4ghCallSet.getInfo().get(infoKey));
		            		setSampleDbId(ga4ghCallSet.getSampleId());
	            		}} );
            	}
        	if (nTotalCallSetsEncountered > 0 && !fAllowedToReadAnything)
        		return new ResponseEntity<CallSetsListResponse>(HttpStatus.FORBIDDEN);

			Pagination pagination = new Pagination();
			pagination.setPageSize(String.valueOf(result.getData().size()));
			pagination.setCurrentPage(body.getPage());
			pagination.setTotalPages(body.getPageSize() == null ? 1 : (int) Math.ceil((float) v1responseWrapper.getTotalCount() / body.getPageSize()));
			pagination.setTotalCount(v1responseWrapper.getTotalCount());
			metadata.setPagination(pagination);
			
			cslr.setResult(result);
            return new ResponseEntity<CallSetsListResponse>(cslr, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<CallSetsListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    public ResponseEntity<CallSetsListResponse> searchCallsetsSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<CallSetsListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"sampleDbId\" : \"sampleDbId\",\n      \"callSetName\" : \"callSetName\",\n      \"created\" : \"created\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"callSetDbId\" : \"callSetDbId\",\n      \"updated\" : \"updated\",\n      \"variantSetIds\" : [ \"variantSetIds\", \"variantSetIds\" ],\n      \"studyDbId\" : \"studyDbId\"\n    }, {\n      \"sampleDbId\" : \"sampleDbId\",\n      \"callSetName\" : \"callSetName\",\n      \"created\" : \"created\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"callSetDbId\" : \"callSetDbId\",\n      \"updated\" : \"updated\",\n      \"variantSetIds\" : [ \"variantSetIds\", \"variantSetIds\" ],\n      \"studyDbId\" : \"studyDbId\"\n    } ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", CallSetsListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<CallSetsListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    public ResponseEntity<SuccessfulSearchResponse> searchMarkerpositionsPost(@ApiParam(value = ""  )  @Valid @RequestBody MarkerPositionSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<SuccessfulSearchResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"searchResultsDbId\" : \"551ae08c\"\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", SuccessfulSearchResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<SuccessfulSearchResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    public ResponseEntity<MarkerPositionListResponse> searchMarkerpositionsSearchResultsDbIdPost(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<MarkerPositionListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"mapDbId\" : \"3d52bdf3\",\n      \"linkageGroupName\" : \"Chromosome 3\",\n      \"markerDbId\" : \"a1eb250a\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"mapName\" : \"Genome Map 1\",\n      \"position\" : 2390,\n      \"markerName\" : \"Marker_2390\"\n    }, {\n      \"mapDbId\" : \"3d52bdf3\",\n      \"linkageGroupName\" : \"Chromosome 3\",\n      \"markerDbId\" : \"a1eb250a\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"mapName\" : \"Genome Map 1\",\n      \"position\" : 2390,\n      \"markerName\" : \"Marker_2390\"\n    } ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", MarkerPositionListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<MarkerPositionListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//public ResponseEntity<SuccessfulSearchResponse> searchReferencesPost(@ApiParam(value = "References Search request"  )  @Valid @RequestBody SearchReferencesRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<SuccessfulSearchResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"searchResultsDbId\" : \"551ae08c\"\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", SuccessfulSearchResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<SuccessfulSearchResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    public ResponseEntity<ReferenceListResponse> searchReferencesSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<ReferenceListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"referenceDbId\" : \"referenceDbId\",\n      \"isDerived\" : true,\n      \"sourceURI\" : \"sourceURI\",\n      \"species\" : {\n        \"termURI\" : \"termURI\",\n        \"term\" : \"term\"\n      },\n      \"md5checksum\" : \"md5checksum\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"length\" : \"length\",\n      \"sourceDivergence\" : 0.8008282,\n      \"sourceAccessions\" : [ \"sourceAccessions\", \"sourceAccessions\" ],\n      \"referenceName\" : \"referenceName\"\n    }, {\n      \"referenceDbId\" : \"referenceDbId\",\n      \"isDerived\" : true,\n      \"sourceURI\" : \"sourceURI\",\n      \"species\" : {\n        \"termURI\" : \"termURI\",\n        \"term\" : \"term\"\n      },\n      \"md5checksum\" : \"md5checksum\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"length\" : \"length\",\n      \"sourceDivergence\" : 0.8008282,\n      \"sourceAccessions\" : [ \"sourceAccessions\", \"sourceAccessions\" ],\n      \"referenceName\" : \"referenceName\"\n    } ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", ReferenceListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<ReferenceListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    public ResponseEntity<SuccessfulSearchResponse> searchReferencesetsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody SearchReferenceSetsRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//    	try {
//            return new ResponseEntity<SuccessfulSearchResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"searchResultsDbId\" : \"551ae08c\"\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", SuccessfulSearchResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<SuccessfulSearchResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    public ResponseEntity<ReferenceListResponse1> searchReferencesetsSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<ReferenceListResponse1>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"isDerived\" : true,\n      \"sourceURI\" : \"sourceURI\",\n      \"species\" : {\n        \"termURI\" : \"termURI\",\n        \"term\" : \"term\"\n      },\n      \"md5checksum\" : \"md5checksum\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"assemblyPUI\" : \"assemblyPUI\",\n      \"description\" : \"description\",\n      \"referenceSetDbId\" : \"referenceSetDbId\",\n      \"referenceSetName\" : \"referenceSetName\",\n      \"sourceAccessions\" : [ \"sourceAccessions\", \"sourceAccessions\" ]\n    }, {\n      \"isDerived\" : true,\n      \"sourceURI\" : \"sourceURI\",\n      \"species\" : {\n        \"termURI\" : \"termURI\",\n        \"term\" : \"term\"\n      },\n      \"md5checksum\" : \"md5checksum\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"assemblyPUI\" : \"assemblyPUI\",\n      \"description\" : \"description\",\n      \"referenceSetDbId\" : \"referenceSetDbId\",\n      \"referenceSetName\" : \"referenceSetName\",\n      \"sourceAccessions\" : [ \"sourceAccessions\", \"sourceAccessions\" ]\n    } ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", ReferenceListResponse1.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<ReferenceListResponse1>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    private String integerToString(Integer n) {
		return n == null ? null : String.valueOf(n);
	}

	public ResponseEntity<SampleListResponse> searchSamplesPost(@ApiParam(value = "")  @Valid @RequestBody SampleSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
    	String token = ServerinfoApiController.readToken(authorization);

    	// TODO: implement or forbid searching by germplasm id / observation unit id
        try {
        	SampleListResponse slr = new SampleListResponse();
        	SampleListResponseResult result = new SampleListResponseResult();
        	String refSetDbId = null;
        	Integer variantSetId = null;
        	Collection<Integer> sampleIds = new HashSet<>();
        	
        	if (body.getSampleDbIds() == null)
        		throw new Exception("You must provide a list of sample IDs");

        	for (String spId : body.getSampleDbIds()) {
        		String[] info = GigwaSearchVariantsRequest.getInfoFromId(spId, 4);
				if (refSetDbId == null)
					refSetDbId = info[0];
				else if (!refSetDbId.equals(info[0]))
					throw new Exception("You may only ask for germplasm records from one referenceSet at a time!");
				if (variantSetId == null)
					variantSetId = Integer.parseInt(info[1]);
				else if (!variantSetId.equals(Integer.parseInt(info[1])))
					throw new Exception("You may only ask for germplasm records from one variantSet at a time!");
				sampleIds.add(Integer.parseInt(info[3]));
        	}
        	
   			if (!tokenManager.canUserReadProject(token, refSetDbId, variantSetId))
   				return new ResponseEntity<SampleListResponse>(HttpStatus.FORBIDDEN);

        	MongoTemplate mongoTemplate = MongoTemplateManager.get(refSetDbId);
        	Query q = new Query(Criteria.where("_id").in(sampleIds));
        	long count = mongoTemplate.count(q, GenotypingSample.class);
            if (body.getPageSize() != null)
            {
            	q.limit(body.getPageSize());
                if (body.getPage() != null)
                	q.skip(body.getPage() * body.getPageSize());
            }
            
            List<GenotypingSample> genotypingSamples = mongoTemplate.find(q, GenotypingSample.class);
        	for (GenotypingSample mgdbSample : genotypingSamples) {
        		Sample sample = new Sample();
        		sample.sampleDbId(ga4ghService.createId(refSetDbId, variantSetId, mgdbSample.getIndividual(), mgdbSample.getId()));
        		sample.germplasmDbId(ga4ghService.createId(refSetDbId, variantSetId, mgdbSample.getIndividual()));
        		result.addDataItem(sample);
        	}

			Metadata metadata = new Metadata();
			Pagination pagination = new Pagination();
			pagination.setPageSize(String.valueOf(body.getPageSize()));
			pagination.setCurrentPage(body.getPage());
			pagination.setTotalPages(body.getPageSize() == null ? 1 : (int) Math.ceil((float) count / body.getPageSize()));
			pagination.setTotalCount((int) count);
			metadata.setPagination(pagination);
			slr.setMetadata(metadata);
        	
			slr.setResult(result);
            return new ResponseEntity<SampleListResponse>(slr, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<SampleListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    public ResponseEntity<SampleListResponse> searchSamplesSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {		
//            return new ResponseEntity<SampleListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ \"\", \"\" ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", SampleListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<SampleListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    public ResponseEntity<SuccessfulSearchResponse> searchVariantsPost(@ApiParam(value = "Study Search request"  )  @Valid @RequestBody VariantsSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<SuccessfulSearchResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"searchResultsDbId\" : \"551ae08c\"\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", SuccessfulSearchResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<SuccessfulSearchResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    public ResponseEntity<VariantListResponse> searchVariantsSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<VariantListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"created\" : \"1573671122\",\n      \"referenceBases\" : \"ATCGATTGAGCTCTAGCG\",\n      \"start\" : \"500\",\n      \"cipos\" : [ -12000, 1000 ],\n      \"variantType\" : \"DUP\",\n      \"ciend\" : [ -1000, 0 ],\n      \"alternate_bases\" : [ \"TAGGATTGAGCTCTATAT\" ],\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"variantSetDbId\" : [ \"c8ae400b\", \"ef2c204b\" ],\n      \"filtersFailed\" : [ \"d629a669\", \"3f14f578\" ],\n      \"svlen\" : \"1500\",\n      \"variantDbId\" : \"628e89c5\",\n      \"variantNames\" : [ \"RefSNP_ID_1\", \"06ea312e\" ],\n      \"end\" : \"518\",\n      \"filtersApplied\" : true,\n      \"filtersPassed\" : true,\n      \"updated\" : \"1573672019\",\n      \"referenceName\" : \"chr20\"\n    }, {\n      \"created\" : \"1573671122\",\n      \"referenceBases\" : \"ATCGATTGAGCTCTAGCG\",\n      \"start\" : \"500\",\n      \"cipos\" : [ -12000, 1000 ],\n      \"variantType\" : \"DUP\",\n      \"ciend\" : [ -1000, 0 ],\n      \"alternate_bases\" : [ \"TAGGATTGAGCTCTATAT\" ],\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"variantSetDbId\" : [ \"c8ae400b\", \"ef2c204b\" ],\n      \"filtersFailed\" : [ \"d629a669\", \"3f14f578\" ],\n      \"svlen\" : \"1500\",\n      \"variantDbId\" : \"628e89c5\",\n      \"variantNames\" : [ \"RefSNP_ID_1\", \"06ea312e\" ],\n      \"end\" : \"518\",\n      \"filtersApplied\" : true,\n      \"filtersPassed\" : true,\n      \"updated\" : \"1573672019\",\n      \"referenceName\" : \"chr20\"\n    } ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", VariantListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<VariantListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public ResponseEntity<VariantSetListResponse> searchVariantsetsPost(@ApiParam(value = "Study Search request"  )  @Valid @RequestBody VariantSetsSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
    	String token = ServerinfoApiController.readToken(authorization);
    	
        try {
        	VariantSetListResponse vslr = new VariantSetListResponse();
        	VariantSetListResponseResult result = new VariantSetListResponseResult();
        	for (String refSetDbId : body.getReferenceSetDbIds()) {
	        	List<org.ga4gh.models.VariantSet> ga4ghVariantSets = ga4ghService.searchVariantSets(new SearchVariantSetsRequest(refSetDbId, null, null)).getVariantSets();
	        	List<org.ga4gh.models.VariantSet> forbiddenVariantSets = new ArrayList<>();
	        	for (org.ga4gh.models.VariantSet ga4ghVariantSet : ga4ghVariantSets)
	        	{
	        		int variantSetId = Integer.parseInt(ga4ghVariantSet.getId().split(GigwaGa4ghServiceImpl.ID_SEPARATOR)[1]);
	        		if (!tokenManager.canUserReadProject(token, refSetDbId, variantSetId))
	        			forbiddenVariantSets.add(ga4ghVariantSet);
	        	}
	        	ga4ghVariantSets.removeAll(forbiddenVariantSets);

        		for (final org.ga4gh.models.VariantSet ga4ghVariantSet : ga4ghVariantSets) {
	            	result.addDataItem(new VariantSet() {{
		            		setVariantSetDbId(ga4ghVariantSet.getId());
		            		setReferenceSetDbId(ga4ghVariantSet.getReferenceSetId());
		            		setVariantSetName(ga4ghVariantSet.getName());
		            		Analysis analysisItem = new Analysis();
		            		for (VariantSetMetadata metadata : ga4ghVariantSet.getMetadata()) {
		            			if ("description".equals(metadata.getKey()))
		            				putAdditionalInfoItem(metadata.getKey(), metadata.getValue());
		            			else
		            				analysisItem.description(metadata.getValue());
		            		}
			            	analysisItem.setAnalysisDbId(ga4ghVariantSet.getId());
			            	analysisItem.setAnalysisDbId(ga4ghVariantSet.getName());
			            	analysisItem.setType("TODO: check how to deal with this field");
		            		addAnalysisItem(analysisItem);
	            		}} );
        		}
        	}
        	
			Metadata metadata = new Metadata();
			Pagination pagination = new Pagination();
			pagination.setPageSize(String.valueOf(result.getData().size()));
			pagination.setCurrentPage(0);
			pagination.setTotalPages(1);
			pagination.setTotalCount(result.getData().size());
			metadata.setPagination(pagination);
			vslr.setMetadata(metadata);

			vslr.setResult(result);
            return new ResponseEntity<VariantSetListResponse>(vslr, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<VariantSetListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    public ResponseEntity<VariantSetListResponse> searchVariantsetsSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<VariantSetListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"availableFormats\" : [ {\n        \"dataFormat\" : \"DartSeq\",\n        \"fileURL\" : \"http://example.com/aeiou\",\n        \"fileFormat\" : \"text/csv\"\n      }, {\n        \"dataFormat\" : \"DartSeq\",\n        \"fileURL\" : \"http://example.com/aeiou\",\n        \"fileFormat\" : \"text/csv\"\n      } ],\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"variantSetDbId\" : \"variantSetDbId\",\n      \"callSetCount\" : 0,\n      \"referenceSetDbId\" : \"referenceSetDbId\",\n      \"variantSetName\" : \"variantSetName\",\n      \"analysis\" : [ {\n        \"software\" : [ \"software\", \"software\" ],\n        \"analysisDbId\" : \"analysisDbId\",\n        \"created\" : \"created\",\n        \"description\" : \"description\",\n        \"type\" : \"type\",\n        \"updated\" : \"updated\",\n        \"analysisName\" : \"analysisName\"\n      }, {\n        \"software\" : [ \"software\", \"software\" ],\n        \"analysisDbId\" : \"analysisDbId\",\n        \"created\" : \"created\",\n        \"description\" : \"description\",\n        \"type\" : \"type\",\n        \"updated\" : \"updated\",\n        \"analysisName\" : \"analysisName\"\n      } ],\n      \"studyDbId\" : \"studyDbId\",\n      \"variantCount\" : 6\n    }, {\n      \"availableFormats\" : [ {\n        \"dataFormat\" : \"DartSeq\",\n        \"fileURL\" : \"http://example.com/aeiou\",\n        \"fileFormat\" : \"text/csv\"\n      }, {\n        \"dataFormat\" : \"DartSeq\",\n        \"fileURL\" : \"http://example.com/aeiou\",\n        \"fileFormat\" : \"text/csv\"\n      } ],\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"variantSetDbId\" : \"variantSetDbId\",\n      \"callSetCount\" : 0,\n      \"referenceSetDbId\" : \"referenceSetDbId\",\n      \"variantSetName\" : \"variantSetName\",\n      \"analysis\" : [ {\n        \"software\" : [ \"software\", \"software\" ],\n        \"analysisDbId\" : \"analysisDbId\",\n        \"created\" : \"created\",\n        \"description\" : \"description\",\n        \"type\" : \"type\",\n        \"updated\" : \"updated\",\n        \"analysisName\" : \"analysisName\"\n      }, {\n        \"software\" : [ \"software\", \"software\" ],\n        \"analysisDbId\" : \"analysisDbId\",\n        \"created\" : \"created\",\n        \"description\" : \"description\",\n        \"type\" : \"type\",\n        \"updated\" : \"updated\",\n        \"analysisName\" : \"analysisName\"\n      } ],\n      \"studyDbId\" : \"studyDbId\",\n      \"variantCount\" : 6\n    } ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", VariantSetListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<VariantSetListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    public ResponseEntity<GermplasmListResponse> searchGermplasmPost(HttpServletResponse response, @ApiParam(value = ""  )  @Valid @RequestBody GermplasmSearchRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization)  throws Exception {
    	String token = ServerinfoApiController.readToken(authorization);

		if (body.getCommonCropNames() != null && body.getCommonCropNames().size() > 0)
			return new ResponseEntity<GermplasmListResponse>(HttpStatus.OK);	// not supported

		fr.cirad.web.controller.rest.BrapiRestController.GermplasmSearchRequest gsr = new fr.cirad.web.controller.rest.BrapiRestController.GermplasmSearchRequest();
    	gsr.accessionNumbers = body.getAccessionNumbers();
    	gsr.germplasmPUIs = body.getGermplasmPUIs();
    	gsr.germplasmGenus = body.getGermplasmGenus();
    	gsr.germplasmSpecies = body.getGermplasmSpecies();
    	gsr.germplasmNames = body.getGermplasmNames() == null ? null : body.getGermplasmNames().stream().map(nm -> nm.substring(1 + nm.lastIndexOf(GigwaMethods.ID_SEPARATOR))).collect(Collectors.toList());
    	gsr.germplasmDbIds = body.getGermplasmDbIds() == null ? null : body.getGermplasmDbIds().stream().map(id -> id.substring(1 + id.lastIndexOf(GigwaMethods.ID_SEPARATOR))).collect(Collectors.toList());
    	gsr.page = body.getPage();
    	gsr.pageSize = body.getPageSize();

    	try {
			GermplasmListResponse glr = new GermplasmListResponse();
			GermplasmListResponseResult result = new GermplasmListResponseResult();
			Metadata metadata = new Metadata();
			glr.setMetadata(metadata);

			String refSetDbId = null;
			Integer variantSetId = null;
			Collection<String> germplasmIds = new HashSet<>();
			if (body.getGermplasmDbIds() == null || body.getGermplasmDbIds().isEmpty()) {
				Status status = new Status();
				status.setMessage("Some germplasmDbIds must be specified as parameter!");
				metadata.addStatusItem(status);
				return new ResponseEntity<GermplasmListResponse>(glr, HttpStatus.BAD_REQUEST);
			}

			for (String gpId : body.getGermplasmDbIds()) {
				String[] info = GigwaSearchVariantsRequest.getInfoFromId(gpId, 3);
				if (refSetDbId == null)
					refSetDbId = info[0];
				else if (!refSetDbId.equals(info[0]))
//					return new ResponseEntity<GermplasmListResponse>(objectMapper.readValue("{\"error\":\"You may only ask for germplasm records from one referenceSet at a time!\"}", HashMap.class), HttpStatus.BAD_REQUEST);
					throw new Exception("You may only ask for germplasm records from one referenceSet at a time!");
				if (variantSetId == null)
					variantSetId = Integer.parseInt(info[1]);
				else if (!variantSetId.equals(Integer.parseInt(info[1])))
					throw new Exception("You may only ask for germplasm records from one variantSet at a time!");
				germplasmIds.add(info[2]);
			}
			
   			if (!tokenManager.canUserReadProject(token, refSetDbId, variantSetId))
   				return new ResponseEntity<GermplasmListResponse>(HttpStatus.FORBIDDEN);
   			
//   			Map<String, Object> intermediateV1response = (Map<String, Object>) brapiV1Service.executeGermplasmSearch(request, response, refSetDbId, gsr);
//			Map<String, Object> intermediateV1Result = (Map<String, Object>) intermediateV1response.get("result");
//			System.err.println(intermediateV1Result.get("searchResultsDbId"));
 			
   			Map<String, Object> v1response = (Map<String, Object>) brapiV1Service.executeGermplasmSearch(request, response, refSetDbId, gsr);
   			Map<String, Object> v1Result = (Map<String, Object>) v1response.get("result");
   	    	ArrayList<Map<String, Object>> v1data = (ArrayList<Map<String, Object>>) v1Result.get("data");
   	    	for (Map<String, Object> v1germplasmRecord : v1data) {
    			Germplasm germplasm = new Germplasm();
    			
   	    		for (String key : v1germplasmRecord.keySet()) {
   	    			String sLCkey = key.toLowerCase();
   	    			Object val = v1germplasmRecord.get(key);
					if (!BrapiGermplasm.germplasmFields.containsKey(sLCkey) && !"germplasmdbid".equals(sLCkey)) {
						if ("additionalinfo".equals(sLCkey)) {
							for (String aiKey : ((HashMap<String, String>) val).keySet())
								germplasm.putAdditionalInfoItem(aiKey, ((HashMap<String, String>) val).get(aiKey));
						}
						else	
							germplasm.putAdditionalInfoItem(key, val.toString());
					}
					else {
						switch (sLCkey) {
							case "germplasmdbid":
								germplasm.germplasmDbId(ga4ghService.createId(refSetDbId, variantSetId, val.toString()));
								break;
							case "germplasmname":
								germplasm.setGermplasmName(ga4ghService.createId(refSetDbId, variantSetId, val.toString()));
								break;
							case "defaultdisplayname":
								germplasm.setDefaultDisplayName(val.toString());
								break;
							case "accessionnumber":
								germplasm.setAccessionNumber(val.toString());
								break;
							case "germplasmpui":
								germplasm.setGermplasmPUI(val.toString());
								break;
							case "pedigree":
								germplasm.setPedigree(val.toString());
								break;
							case "seedsource":
								germplasm.setSeedSource(val.toString());
								break;
							case "commoncropname":
								germplasm.setCommonCropName(val.toString());
								break;
							case "institutecode":
								germplasm.setInstituteCode(val.toString());
								break;
							case "institutename":
								germplasm.setInstituteName(val.toString());
								break;
							case "biologicalstatusofaccessioncode":
								germplasm.setBiologicalStatusOfAccessionCode(BiologicalStatusOfAccessionCodeEnum.fromValue(val.toString()));
								break;
							case "countryoforigincode":
								germplasm.setCountryOfOriginCode(val.toString());
								break;
							case "typeofgermplasmstoragecode":
								germplasm.setTypeOfGermplasmStorageCode(Arrays.asList(GermplasmMCPD.StorageTypeCodesEnum.fromValue(val.toString()).toString()));
								break;
							case "genus":
								germplasm.setGermplasmGenus(val.toString());
								break;
							case "species":
								germplasm.setGermplasmSpecies(val.toString());
								break;
							case "speciesauthority":
								germplasm.setSpeciesAuthority(val.toString());
								break;
							case "subtaxa":
								germplasm.setSubtaxa(val.toString());
								break;
							case "subtaxaauthority":
								germplasm.setSubtaxaAuthority(val.toString());
								break;
							case "acquisitiondate":
								try {
									germplasm.setAcquisitionDate(LocalDate.parse(val.toString()));
								}
								catch (DateTimeParseException dtpe){
									log.error("Unable to parse germplasm acquisition date: " + val);
								}
								break;
						}
					}
   	    		}
				result.addDataItem(germplasm);
   	    	}
			glr.setResult(result);
			Pagination pagination = new Pagination();
			jhi.brapi.api.Metadata v1Metadata = (jhi.brapi.api.Metadata) v1response.get("metadata");
			pagination.setPageSize(String.valueOf(v1Metadata.getPagination().getPageSize()));
			pagination.setCurrentPage(v1Metadata.getPagination().getCurrentPage());
			pagination.setTotalPages(v1Metadata.getPagination().getTotalPages());
			pagination.setTotalCount((int) v1Metadata.getPagination().getTotalCount());
			metadata.setPagination(pagination);			
			
			return new ResponseEntity<GermplasmListResponse>(glr, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<GermplasmListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//    public ResponseEntity<GermplasmListResponse> searchGermplasmSearchResultsDbIdGet(@ApiParam(value = "Permanent unique identifier which references the search results",required=true) @PathVariable("searchResultsDbId") String searchResultsDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        try {
//            return new ResponseEntity<GermplasmListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ \"\", \"\" ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", GermplasmListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//        } catch (IOException e) {
//            log.error("Couldn't serialize response for content type application/json", e);
//            return new ResponseEntity<GermplasmListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
