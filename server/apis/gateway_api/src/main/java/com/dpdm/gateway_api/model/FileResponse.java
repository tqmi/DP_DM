package com.dpdm.gateway_api.model;

import java.util.Objects;
import com.dpdm.gateway_api.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FileResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-19T18:35:22.034933700+03:00[Europe/Bucharest]")
public class FileResponse   {
  @JsonProperty("fileName")
  private String fileName;

  @JsonProperty("owner")
  private String owner;

  @JsonProperty("fileid")
  private String fileid;

  @JsonProperty("status")
  private String status;

  @JsonProperty("signedBy")
  @Valid
  private List<User> signedBy = null;

  public FileResponse fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * Get fileName
   * @return fileName
  */
  @ApiModelProperty(value = "")


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
  */
  @ApiModelProperty(value = "")


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
  */
  @ApiModelProperty(value = "")


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
  */
  @ApiModelProperty(value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public FileResponse signedBy(List<User> signedBy) {
    this.signedBy = signedBy;
    return this;
  }

  public FileResponse addSignedByItem(User signedByItem) {
    if (this.signedBy == null) {
      this.signedBy = new ArrayList<>();
    }
    this.signedBy.add(signedByItem);
    return this;
  }

  /**
   * Get signedBy
   * @return signedBy
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<User> getSignedBy() {
    return signedBy;
  }

  public void setSignedBy(List<User> signedBy) {
    this.signedBy = signedBy;
  }


  @Override
  public boolean equals(Object o) {
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

