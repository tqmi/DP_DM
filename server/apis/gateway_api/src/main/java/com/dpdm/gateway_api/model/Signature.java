package com.dpdm.gateway_api.model;

import java.util.Objects;
import com.dpdm.gateway_api.model.MyUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import javax.xml.bind.annotation.*;

/**
 * Signature
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-27T01:37:43.340Z[GMT]")
@JacksonXmlRootElement(localName = "Signature")
@XmlRootElement(name = "Signature")
@XmlAccessorType(XmlAccessType.FIELD)@JsonInclude(JsonInclude.Include.NON_NULL)

public class Signature  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("by")
  @JacksonXmlProperty(localName = "by")
  private MyUser by = null;

  @JsonProperty("publicKey")
  @JacksonXmlProperty(localName = "publicKey")
  private String publicKey = null;

  public Signature by(MyUser by) {
    this.by = by;
    return this;
  }

  /**
   * Get by
   * @return by
   **/
  @Schema(description = "")
  
    @Valid
    public MyUser getBy() {
    return by;
  }

  public void setBy(MyUser by) {
    this.by = by;
  }

  public Signature publicKey(String publicKey) {
    this.publicKey = publicKey;
    return this;
  }

  /**
   * Get publicKey
   * @return publicKey
   **/
  @Schema(description = "")
  
    public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Signature signature = (Signature) o;
    return Objects.equals(this.by, signature.by) &&
        Objects.equals(this.publicKey, signature.publicKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(by, publicKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Signature {\n");
    
    sb.append("    by: ").append(toIndentedString(by)).append("\n");
    sb.append("    publicKey: ").append(toIndentedString(publicKey)).append("\n");
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
