package org.brapi.v2.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets WSMIMEDataTypes
 */
public enum WSMIMEDataTypes {
  APPLICATION_JSON("application/json"),
    TEXT_CSV("text/csv"),
    TEXT_TSV("text/tsv"),
    APPLICATION_FLAPJACK("application/flapjack");

  private String value;

  WSMIMEDataTypes(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static WSMIMEDataTypes fromValue(String text) {
    for (WSMIMEDataTypes b : WSMIMEDataTypes.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
