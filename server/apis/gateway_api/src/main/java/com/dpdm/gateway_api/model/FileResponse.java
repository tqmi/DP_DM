package com.dpdm.gateway_api.model;

import java.util.Objects;
import com.dpdm.gateway_api.model.MyUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import javax.xml.bind.annotation.*;

/**
 * FileResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-27T01:38:23.859Z[GMT]")
@JacksonXmlRootElement(localName = "FileResponse")
@XmlRootElement(name = "FileResponse")
@XmlAccessorType(XmlAccessType.FIELD)@JsonInclude(JsonInclude.Include.NON_NULL)

public class FileResponse  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("fileName")
  @JacksonXmlProperty(localName = "fileName")
  private String fileName = null;

  @JsonProperty("owner")
  @JacksonXmlProperty(localName = "owner")
  private String owner = null;

  @JsonProperty("fileid")
  @JacksonXmlProperty(localName = "fileid")
  private String fileid = null;

  @JsonProperty("status")
  @JacksonXmlProperty(localName = "status")
  private String status = null;

  @JsonProperty("signedBy")
  @JacksonXmlProperty(localName = "signedBy")
  @Valid
  private List<Signature> signedBy = null;

  public FileResponse fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * Get fileName
   * @return fileName
   **/
  @Schema(description = "")
  
    public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public FileResponse owner(String owner) {
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

  public FileResponse fileid(String fileid) {
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

  public FileResponse status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @Schema(description = "")
  
    public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public FileResponse signedBy(List<Signature> signedBy) {
    this.signedBy = signedBy;
    return this;
  }

  public FileResponse addSignedByItem(Signature signedByItem) {
    if (this.signedBy == null) {
      this.signedBy = new ArrayList<Signature>();
    }
    this.signedBy.add(signedByItem);
    return this;
  }

  /**
   * Get signedBy
   * @return signedBy
   **/
  @Schema(description = "")
      @Valid
    public List<Signature> getSignedBy() {
    return signedBy;
  }

  public void setSignedBy(List<Signature> signedBy) {
    this.signedBy = signedBy;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileResponse fileResponse = (FileResponse) o;
    return Objects.equals(this.fileName, fileResponse.fileName) &&
        Objects.equals(this.owner, fileResponse.owner) &&
        Objects.equals(this.fileid, fileResponse.fileid) &&
        Objects.equals(this.status, fileResponse.status) &&
        Objects.equals(this.signedBy, fileResponse.signedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName, owner, fileid, status, signedBy);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileResponse {\n");
    
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    fileid: ").append(toIndentedString(fileid)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    signedBy: ").append(toIndentedString(signedBy)).append("\n");
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
