package com.dpdm.gateway_api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.io.Resource;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import javax.xml.bind.annotation.*;

/**
 * Body1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-27T01:37:43.340Z[GMT]")
@JacksonXmlRootElement(localName = "Body1")
@XmlRootElement(name = "Body1")
@XmlAccessorType(XmlAccessType.FIELD)@JsonInclude(JsonInclude.Include.NON_NULL)

public class Body1  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("filename")
  @JacksonXmlProperty(localName = "filename")
  private Resource filename = null;

  public Body1 filename(Resource filename) {
    this.filename = filename;
    return this;
  }

  /**
   * Get filename
   * @return filename
   **/
  @Schema(description = "")
  
    @Valid
    public Resource getFilename() {
    return filename;
  }

  public void setFilename(Resource filename) {
    this.filename = filename;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body1 body1 = (Body1) o;
    return Objects.equals(this.filename, body1.filename);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filename);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body1 {\n");
    
    sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
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
