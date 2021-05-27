package com.dpdm.gateway_api.model;

import java.util.Objects;
import com.dpdm.gateway_api.model.Signature;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-27T01:37:43.340Z[GMT]")
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

  @JsonProperty("signatures")
  @JacksonXmlProperty(localName = "signatures")
  @Valid
  private List<Signature> signatures = null;

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

  public FileResponse signatures(List<Signature> signatures) {
    this.signatures = signatures;
    return this;
  }

  public FileResponse addSignaturesItem(Signature signaturesItem) {
    if (this.signatures == null) {
      this.signatures = new ArrayList<Signature>();
    }
    this.signatures.add(signaturesItem);
    return this;
  }

  /**
   * Get signatures
   * @return signatures
   **/
  @Schema(description = "")
      @Valid
    public List<Signature> getSignatures() {
    return signatures;
  }

  public void setSignatures(List<Signature> signatures) {
    this.signatures = signatures;
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
        Objects.equals(this.signatures, fileResponse.signatures);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName, owner, fileid, status, signatures);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileResponse {\n");
    
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    fileid: ").append(toIndentedString(fileid)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    signatures: ").append(toIndentedString(signatures)).append("\n");
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
