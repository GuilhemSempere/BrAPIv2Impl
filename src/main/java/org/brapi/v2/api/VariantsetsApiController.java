package org.brapi.v2.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;

import org.brapi.v2.model.CallListResponse;
import org.brapi.v2.model.CallSetsListResponse;
import org.brapi.v2.model.VariantListResponse;
import org.brapi.v2.model.VariantSetListResponse;
import org.brapi.v2.model.VariantSetResponse;
import org.brapi.v2.model.VariantSetsExtractRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-19T12:30:12.318Z[GMT]")
@CrossOrigin
@Controller
public class VariantsetsApiController implements VariantsetsApi {

//    private static final Logger log = LoggerFactory.getLogger(VariantsetsApiController.class);
//
//    private final ObjectMapper objectMapper;
//
//    private final HttpServletRequest request;
//
//    @org.springframework.beans.factory.annotation.Autowired
//    public VariantsetsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
//        this.objectMapper = objectMapper;
//        this.request = request;
//    }
//
//    public ResponseEntity<VariantSetResponse> variantsetsExtractPost(@ApiParam(value = "Study Search request"  )  @Valid @RequestBody VariantSetsExtractRequest body,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<VariantSetResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"availableFormats\" : [ {\n      \"dataFormat\" : \"DartSeq\",\n      \"fileURL\" : \"http://example.com/aeiou\",\n      \"fileFormat\" : \"text/csv\"\n    }, {\n      \"dataFormat\" : \"DartSeq\",\n      \"fileURL\" : \"http://example.com/aeiou\",\n      \"fileFormat\" : \"text/csv\"\n    } ],\n    \"additionalInfo\" : {\n      \"key\" : \"additionalInfo\"\n    },\n    \"variantSetDbId\" : \"variantSetDbId\",\n    \"callSetCount\" : 0,\n    \"referenceSetDbId\" : \"referenceSetDbId\",\n    \"variantSetName\" : \"variantSetName\",\n    \"analysis\" : [ {\n      \"software\" : [ \"software\", \"software\" ],\n      \"analysisDbId\" : \"analysisDbId\",\n      \"created\" : \"created\",\n      \"description\" : \"description\",\n      \"type\" : \"type\",\n      \"updated\" : \"updated\",\n      \"analysisName\" : \"analysisName\"\n    }, {\n      \"software\" : [ \"software\", \"software\" ],\n      \"analysisDbId\" : \"analysisDbId\",\n      \"created\" : \"created\",\n      \"description\" : \"description\",\n      \"type\" : \"type\",\n      \"updated\" : \"updated\",\n      \"analysisName\" : \"analysisName\"\n    } ],\n    \"studyDbId\" : \"studyDbId\",\n    \"variantCount\" : 6\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", VariantSetResponse.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<VariantSetResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//
//        return new ResponseEntity<VariantSetResponse>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    public ResponseEntity<VariantSetListResponse> variantsetsGet(@NotNull @ApiParam(value = "The ID of the `VariantSet` to be retrieved.", required = true) @Valid @RequestParam(value = "variantSetDbId", required = true) String variantSetDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<VariantSetListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"availableFormats\" : [ {\n        \"dataFormat\" : \"DartSeq\",\n        \"fileURL\" : \"http://example.com/aeiou\",\n        \"fileFormat\" : \"text/csv\"\n      }, {\n        \"dataFormat\" : \"DartSeq\",\n        \"fileURL\" : \"http://example.com/aeiou\",\n        \"fileFormat\" : \"text/csv\"\n      } ],\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"variantSetDbId\" : \"variantSetDbId\",\n      \"callSetCount\" : 0,\n      \"referenceSetDbId\" : \"referenceSetDbId\",\n      \"variantSetName\" : \"variantSetName\",\n      \"analysis\" : [ {\n        \"software\" : [ \"software\", \"software\" ],\n        \"analysisDbId\" : \"analysisDbId\",\n        \"created\" : \"created\",\n        \"description\" : \"description\",\n        \"type\" : \"type\",\n        \"updated\" : \"updated\",\n        \"analysisName\" : \"analysisName\"\n      }, {\n        \"software\" : [ \"software\", \"software\" ],\n        \"analysisDbId\" : \"analysisDbId\",\n        \"created\" : \"created\",\n        \"description\" : \"description\",\n        \"type\" : \"type\",\n        \"updated\" : \"updated\",\n        \"analysisName\" : \"analysisName\"\n      } ],\n      \"studyDbId\" : \"studyDbId\",\n      \"variantCount\" : 6\n    }, {\n      \"availableFormats\" : [ {\n        \"dataFormat\" : \"DartSeq\",\n        \"fileURL\" : \"http://example.com/aeiou\",\n        \"fileFormat\" : \"text/csv\"\n      }, {\n        \"dataFormat\" : \"DartSeq\",\n        \"fileURL\" : \"http://example.com/aeiou\",\n        \"fileFormat\" : \"text/csv\"\n      } ],\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"variantSetDbId\" : \"variantSetDbId\",\n      \"callSetCount\" : 0,\n      \"referenceSetDbId\" : \"referenceSetDbId\",\n      \"variantSetName\" : \"variantSetName\",\n      \"analysis\" : [ {\n        \"software\" : [ \"software\", \"software\" ],\n        \"analysisDbId\" : \"analysisDbId\",\n        \"created\" : \"created\",\n        \"description\" : \"description\",\n        \"type\" : \"type\",\n        \"updated\" : \"updated\",\n        \"analysisName\" : \"analysisName\"\n      }, {\n        \"software\" : [ \"software\", \"software\" ],\n        \"analysisDbId\" : \"analysisDbId\",\n        \"created\" : \"created\",\n        \"description\" : \"description\",\n        \"type\" : \"type\",\n        \"updated\" : \"updated\",\n        \"analysisName\" : \"analysisName\"\n      } ],\n      \"studyDbId\" : \"studyDbId\",\n      \"variantCount\" : 6\n    } ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", VariantSetListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<VariantSetListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//
//        return new ResponseEntity<VariantSetListResponse>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    public ResponseEntity<CallListResponse> variantsetsVariantSetDbIdCallsGet(@ApiParam(value = "The ID of the `VariantSet` to be retrieved.",required=true) @PathVariable("variantSetDbId") String variantSetDbId,@ApiParam(value = "Should homozygotes be expanded (true) or collapsed into a single occurence (false)") @Valid @RequestParam(value = "expandHomozygotes", required = false) Boolean expandHomozygotes,@ApiParam(value = "The string to use as a representation for missing data") @Valid @RequestParam(value = "unknownString", required = false) String unknownString,@ApiParam(value = "The string to use as a separator for phased allele calls") @Valid @RequestParam(value = "sepPhased", required = false) String sepPhased,@ApiParam(value = "The string to use as a separator for unphased allele calls") @Valid @RequestParam(value = "sepUnphased", required = false) String sepUnphased,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<CallListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"genotype_likelihood\" : [ 0.8008281904610115, 0.8008281904610115 ],\n      \"phaseset\" : \"phaseset\",\n      \"callSetName\" : \"callSetName\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"callSetDbId\" : \"callSetDbId\",\n      \"variantDbId\" : \"variantDbId\",\n      \"variantName\" : \"variantName\",\n      \"genotype\" : {\n        \"values\" : [ \"\", \"\" ]\n      }\n    }, {\n      \"genotype_likelihood\" : [ 0.8008281904610115, 0.8008281904610115 ],\n      \"phaseset\" : \"phaseset\",\n      \"callSetName\" : \"callSetName\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"callSetDbId\" : \"callSetDbId\",\n      \"variantDbId\" : \"variantDbId\",\n      \"variantName\" : \"variantName\",\n      \"genotype\" : {\n        \"values\" : [ \"\", \"\" ]\n      }\n    } ],\n    \"unknownString\" : \"unknownString\",\n    \"expandHomozygotes\" : true,\n    \"sepPhased\" : \"sepPhased\",\n    \"sepUnphased\" : \"sepUnphased\"\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", CallListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<CallListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//
//        return new ResponseEntity<CallListResponse>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    public ResponseEntity<CallSetsListResponse> variantsetsVariantSetDbIdCallsetsGet(@ApiParam(value = "The ID of the `VariantSet` to be retrieved.",required=true) @PathVariable("variantSetDbId") String variantSetDbId,@ApiParam(value = "The ID of the `CallSet` to be retrieved.") @Valid @RequestParam(value = "callSetDbId", required = false) String callSetDbId,@ApiParam(value = "The human readbale name of the `CallSet` to be retrieved.") @Valid @RequestParam(value = "callSetName", required = false) String callSetName,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<CallSetsListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"sampleDbId\" : \"sampleDbId\",\n      \"callSetName\" : \"callSetName\",\n      \"created\" : \"created\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"callSetDbId\" : \"callSetDbId\",\n      \"updated\" : \"updated\",\n      \"variantSetIds\" : [ \"variantSetIds\", \"variantSetIds\" ],\n      \"studyDbId\" : \"studyDbId\"\n    }, {\n      \"sampleDbId\" : \"sampleDbId\",\n      \"callSetName\" : \"callSetName\",\n      \"created\" : \"created\",\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"callSetDbId\" : \"callSetDbId\",\n      \"updated\" : \"updated\",\n      \"variantSetIds\" : [ \"variantSetIds\", \"variantSetIds\" ],\n      \"studyDbId\" : \"studyDbId\"\n    } ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", CallSetsListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<CallSetsListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//
//        return new ResponseEntity<CallSetsListResponse>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    public ResponseEntity<VariantSetResponse> variantsetsVariantSetDbIdGet(@ApiParam(value = "The ID of the `Variant` to be retrieved.",required=true) @PathVariable("variantSetDbId") String variantSetDbId,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<VariantSetResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"availableFormats\" : [ {\n      \"dataFormat\" : \"DartSeq\",\n      \"fileURL\" : \"http://example.com/aeiou\",\n      \"fileFormat\" : \"text/csv\"\n    }, {\n      \"dataFormat\" : \"DartSeq\",\n      \"fileURL\" : \"http://example.com/aeiou\",\n      \"fileFormat\" : \"text/csv\"\n    } ],\n    \"additionalInfo\" : {\n      \"key\" : \"additionalInfo\"\n    },\n    \"variantSetDbId\" : \"variantSetDbId\",\n    \"callSetCount\" : 0,\n    \"referenceSetDbId\" : \"referenceSetDbId\",\n    \"variantSetName\" : \"variantSetName\",\n    \"analysis\" : [ {\n      \"software\" : [ \"software\", \"software\" ],\n      \"analysisDbId\" : \"analysisDbId\",\n      \"created\" : \"created\",\n      \"description\" : \"description\",\n      \"type\" : \"type\",\n      \"updated\" : \"updated\",\n      \"analysisName\" : \"analysisName\"\n    }, {\n      \"software\" : [ \"software\", \"software\" ],\n      \"analysisDbId\" : \"analysisDbId\",\n      \"created\" : \"created\",\n      \"description\" : \"description\",\n      \"type\" : \"type\",\n      \"updated\" : \"updated\",\n      \"analysisName\" : \"analysisName\"\n    } ],\n    \"studyDbId\" : \"studyDbId\",\n    \"variantCount\" : 6\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", VariantSetResponse.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<VariantSetResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//
//        return new ResponseEntity<VariantSetResponse>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    public ResponseEntity<VariantListResponse> variantsetsVariantSetDbIdVariantsGet(@ApiParam(value = "The ID of the `VariantSet` to be retrieved.",required=true) @PathVariable("variantSetDbId") String variantSetDbId,@ApiParam(value = "The ID of the `Variant` to be retrieved.") @Valid @RequestParam(value = "variantDbId", required = false) String variantDbId,@ApiParam(value = "Which result page is requested. The page indexing starts at 0 (the first page is 'page'= 0). Default is `0`.") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "The size of the pages to be returned. Default is `1000`.") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "HTTP HEADER - Token used for Authorization   <strong> Bearer {token_string} </strong>" ) @RequestHeader(value="Authorization", required=false) String authorization) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                return new ResponseEntity<VariantListResponse>(objectMapper.readValue("{\n  \"result\" : {\n    \"data\" : [ {\n      \"created\" : \"1573671122\",\n      \"referenceBases\" : \"ATCGATTGAGCTCTAGCG\",\n      \"start\" : \"500\",\n      \"cipos\" : [ -12000, 1000 ],\n      \"variantType\" : \"DUP\",\n      \"ciend\" : [ -1000, 0 ],\n      \"alternate_bases\" : [ \"TAGGATTGAGCTCTATAT\" ],\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"variantSetDbId\" : [ \"c8ae400b\", \"ef2c204b\" ],\n      \"filtersFailed\" : [ \"d629a669\", \"3f14f578\" ],\n      \"svlen\" : \"1500\",\n      \"variantDbId\" : \"628e89c5\",\n      \"variantNames\" : [ \"RefSNP_ID_1\", \"06ea312e\" ],\n      \"end\" : \"518\",\n      \"filtersApplied\" : true,\n      \"filtersPassed\" : true,\n      \"updated\" : \"1573672019\",\n      \"referenceName\" : \"chr20\"\n    }, {\n      \"created\" : \"1573671122\",\n      \"referenceBases\" : \"ATCGATTGAGCTCTAGCG\",\n      \"start\" : \"500\",\n      \"cipos\" : [ -12000, 1000 ],\n      \"variantType\" : \"DUP\",\n      \"ciend\" : [ -1000, 0 ],\n      \"alternate_bases\" : [ \"TAGGATTGAGCTCTATAT\" ],\n      \"additionalInfo\" : {\n        \"key\" : \"additionalInfo\"\n      },\n      \"variantSetDbId\" : [ \"c8ae400b\", \"ef2c204b\" ],\n      \"filtersFailed\" : [ \"d629a669\", \"3f14f578\" ],\n      \"svlen\" : \"1500\",\n      \"variantDbId\" : \"628e89c5\",\n      \"variantNames\" : [ \"RefSNP_ID_1\", \"06ea312e\" ],\n      \"end\" : \"518\",\n      \"filtersApplied\" : true,\n      \"filtersPassed\" : true,\n      \"updated\" : \"1573672019\",\n      \"referenceName\" : \"chr20\"\n    } ]\n  },\n  \"metadata\" : {\n    \"pagination\" : {\n      \"totalPages\" : 1,\n      \"pageSize\" : \"1000\",\n      \"currentPage\" : 0,\n      \"totalCount\" : 1\n    },\n    \"datafiles\" : [ {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    }, {\n      \"fileDescription\" : \"This is an Excel data file\",\n      \"fileName\" : \"datafile.xslx\",\n      \"fileSize\" : 4398,\n      \"fileMD5Hash\" : \"c2365e900c81a89cf74d83dab60df146\",\n      \"fileURL\" : \"https://wiki.brapi.org/examples/datafile.xslx\",\n      \"fileType\" : \"application/vnd.ms-excel\"\n    } ],\n    \"status\" : [ {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    }, {\n      \"messageType\" : \"INFO\",\n      \"message\" : \"Request accepted, response successful\"\n    } ]\n  },\n  \"@context\" : [ \"https://brapi.org/jsonld/context/metadata.jsonld\" ]\n}", VariantListResponse.class), HttpStatus.NOT_IMPLEMENTED);
//            } catch (IOException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<VariantListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//
//        return new ResponseEntity<VariantListResponse>(HttpStatus.NOT_IMPLEMENTED);
//    }

}
