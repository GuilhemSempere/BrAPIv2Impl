package org.brapi.v2.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SampleSearchRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-19T12:30:12.318Z[GMT]")
public class SampleSearchRequest   {
  @JsonProperty("germplasmDbIds")
  @Valid
  private List<String> germplasmDbIds = null;

  @JsonProperty("observationUnitDbIds")
  @Valid
  private List<String> observationUnitDbIds = null;

  @JsonProperty("plateDbIds")
  @Valid
  private List<String> plateDbIds = null;

  @JsonProperty("sampleDbIds")
  @Valid
  private List<String> sampleDbIds = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("page")
  private Integer page = null;
  
  public SampleSearchRequest germplasmDbIds(List<String> germplasmDbIds) {
    this.germplasmDbIds = germplasmDbIds;
    return this;
  }

  public SampleSearchRequest addGermplasmDbIdsItem(String germplasmDbIdsItem) {
    if (this.germplasmDbIds == null) {
      this.germplasmDbIds = new ArrayList<String>();
    }
    this.germplasmDbIds.add(germplasmDbIdsItem);
    return this;
  }

  /**
   *  The ID which uniquely identifies a germplasm
   * @return germplasmDbIds
  **/
  @ApiModelProperty(value = " The ID which uniquely identifies a germplasm")
  
    public List<String> getGermplasmDbIds() {
    return germplasmDbIds;
  }

  public void setGermplasmDbIds(List<String> germplasmDbIds) {
    this.germplasmDbIds = germplasmDbIds;
  }

  public SampleSearchRequest observationUnitDbIds(List<String> observationUnitDbIds) {
    this.observationUnitDbIds = observationUnitDbIds;
    return this;
  }

  public SampleSearchRequest addObservationUnitDbIdsItem(String observationUnitDbIdsItem) {
    if (this.observationUnitDbIds == null) {
      this.observationUnitDbIds = new ArrayList<String>();
    }
    this.observationUnitDbIds.add(observationUnitDbIdsItem);
    return this;
  }

  /**
   * The ID which uniquely identifies an observation unit
   * @return observationUnitDbIds
  **/
  @ApiModelProperty(value = "The ID which uniquely identifies an observation unit")
  
    public List<String> getObservationUnitDbIds() {
    return observationUnitDbIds;
  }

  public void setObservationUnitDbIds(List<String> observationUnitDbIds) {
    this.observationUnitDbIds = observationUnitDbIds;
  }

  public SampleSearchRequest plateDbIds(List<String> plateDbIds) {
    this.plateDbIds = plateDbIds;
    return this;
  }

  public SampleSearchRequest addPlateDbIdsItem(String plateDbIdsItem) {
    if (this.plateDbIds == null) {
      this.plateDbIds = new ArrayList<String>();
    }
    this.plateDbIds.add(plateDbIdsItem);
    return this;
  }

  /**
   * The ID which uniquely identifies a plate of samples
   * @return plateDbIds
  **/
  @ApiModelProperty(value = "The ID which uniquely identifies a plate of samples")
  
    public List<String> getPlateDbIds() {
    return plateDbIds;
  }

  public void setPlateDbIds(List<String> plateDbIds) {
    this.plateDbIds = plateDbIds;
  }

  public SampleSearchRequest sampleDbIds(List<String> sampleDbIds) {
    this.sampleDbIds = sampleDbIds;
    return this;
  }

  public SampleSearchRequest addSampleDbIdsItem(String sampleDbIdsItem) {
    if (this.sampleDbIds == null) {
      this.sampleDbIds = new ArrayList<String>();
    }
    this.sampleDbIds.add(sampleDbIdsItem);
    return this;
  }

  /**
   * The ID which uniquely identifies a sample
   * @return sampleDbIds
  **/
  @ApiModelProperty(value = "The ID which uniquely identifies a sample")
  
    public List<String> getSampleDbIds() {
    return sampleDbIds;
  }

  public void setSampleDbIds(List<String> sampleDbIds) {
    this.sampleDbIds = sampleDbIds;
  }
  
  public SampleSearchRequest pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public SampleSearchRequest page(Integer page) {
    this.page = page;
    return this;
  }

  @ApiModelProperty(value = "The page number.")
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SampleSearchRequest sampleSearchRequest = (SampleSearchRequest) o;
    return Objects.equals(this.germplasmDbIds, sampleSearchRequest.germplasmDbIds) &&
        Objects.equals(this.observationUnitDbIds, sampleSearchRequest.observationUnitDbIds) &&
        Objects.equals(this.plateDbIds, sampleSearchRequest.plateDbIds) &&
        Objects.equals(this.sampleDbIds, sampleSearchRequest.sampleDbIds) && 
        Objects.equals(this.pageSize, sampleSearchRequest.pageSize) &&
        Objects.equals(this.page, sampleSearchRequest.page);
  }

  @Override
  public int hashCode() {
    return Objects.hash(germplasmDbIds, observationUnitDbIds, plateDbIds, sampleDbIds, pageSize, page);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SampleSearchRequest {\n");
    
    sb.append("    germplasmDbIds: ").append(toIndentedString(germplasmDbIds)).append("\n");
    sb.append("    observationUnitDbIds: ").append(toIndentedString(observationUnitDbIds)).append("\n");
    sb.append("    plateDbIds: ").append(toIndentedString(plateDbIds)).append("\n");
    sb.append("    sampleDbIds: ").append(toIndentedString(sampleDbIds)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
