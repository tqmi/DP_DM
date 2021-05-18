package com.dpdm.gateway_api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FileResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-18T19:45:07.825591900+03:00[Europe/Bucharest]")
public class FileResponse   {
  @JsonProperty("fileName")
  private String fileName;

  @JsonProperty("owner")
  private String owner;

  @JsonProperty("fileid")
  private String fileid;

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
        Objects.equals(this.fileid, fileResponse.fileid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName, owner, fileid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileResponse {\n");
    
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    fileid: ").append(toIndentedString(fileid)).append("\n");
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

