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
 * SignRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-27T01:37:43.340Z[GMT]")
@JacksonXmlRootElement(localName = "SignRequest")
@XmlRootElement(name = "SignRequest")
@XmlAccessorType(XmlAccessType.FIELD)@JsonInclude(JsonInclude.Include.NON_NULL)

public class SignRequest  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  @JacksonXmlProperty(localName = "id")
  private String id = null;

  @JsonProperty("by")
  @JacksonXmlProperty(localName = "by")
  private MyUser by = null;

  @JsonProperty("owner")
  @JacksonXmlProperty(localName = "owner")
  private String owner = null;

  @JsonProperty("fileid")
  @JacksonXmlProperty(localName = "fileid")
  private String fileid = null;

  public SignRequest id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SignRequest by(MyUser by) {
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

  public SignRequest owner(String owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
   **/
  @Schema(description = "")
  
    public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public SignRequest fileid(String fileid) {
    this.fileid = fileid;
    return this;
  }

  /**
   * Get fileid
   * @return fileid
   **/
  @Schema(description = "")
  
    public String getFileid() {
    return fileid;
  }

  public void setFileid(String fileid) {
    this.fileid = fileid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequest signRequest = (SignRequest) o;
    return Objects.equals(this.id, signRequest.id) &&
        Objects.equals(this.by, signRequest.by) &&
        Objects.equals(this.owner, signRequest.owner) &&
        Objects.equals(this.fileid, signRequest.fileid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, by, owner, fileid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SignRequest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    by: ").append(toIndentedString(by)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    fileid: ").append(toIndentedString(fileid)).append("\n");
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
