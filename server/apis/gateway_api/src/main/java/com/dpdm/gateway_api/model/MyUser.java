package com.dpdm.gateway_api.model;

import java.util.Objects;
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
 * MyUser
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-20T13:27:23.285Z[GMT]")
@JacksonXmlRootElement(localName = "MyUser")
@XmlRootElement(name = "MyUser")
@XmlAccessorType(XmlAccessType.FIELD)@JsonInclude(JsonInclude.Include.NON_NULL)

public class MyUser  implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("name")
  @JacksonXmlProperty(localName = "name")
  private String name = null;

  @JsonProperty("email")
  @JacksonXmlProperty(localName = "email")
  private String email = null;

  @JsonProperty("address")
  @JacksonXmlProperty(localName = "address")
  private String address = null;

  @JsonProperty("phone")
  @JacksonXmlProperty(localName = "phone")
  private String phone = null;

  @JsonProperty("accesslevel")
  @JacksonXmlProperty(localName = "accesslevel")
  private String accesslevel = null;

  @JsonProperty("type")
  @JacksonXmlProperty(localName = "type")
  private String type = null;

  @JsonProperty("institutionlink")
  @JacksonXmlProperty(localName = "institutionlink")
  private String institutionlink = null;

  public MyUser name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MyUser email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @Schema(description = "")
  
    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public MyUser address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
   **/
  @Schema(description = "")
  
    public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public MyUser phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
   **/
  @Schema(description = "")
  
    public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public MyUser accesslevel(String accesslevel) {
    this.accesslevel = accesslevel;
    return this;
  }

  /**
   * Get accesslevel
   * @return accesslevel
   **/
  @Schema(description = "")
  
    public String getAccesslevel() {
    return accesslevel;
  }

  public void setAccesslevel(String accesslevel) {
    this.accesslevel = accesslevel;
  }

  public MyUser type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @Schema(description = "")
  
    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public MyUser institutionlink(String institutionlink) {
    this.institutionlink = institutionlink;
    return this;
  }

  /**
   * Get institutionlink
   * @return institutionlink
   **/
  @Schema(description = "")
  
    public String getInstitutionlink() {
    return institutionlink;
  }

  public void setInstitutionlink(String institutionlink) {
    this.institutionlink = institutionlink;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyUser myUser = (MyUser) o;
    return Objects.equals(this.name, myUser.name) &&
        Objects.equals(this.email, myUser.email) &&
        Objects.equals(this.address, myUser.address) &&
        Objects.equals(this.phone, myUser.phone) &&
        Objects.equals(this.accesslevel, myUser.accesslevel) &&
        Objects.equals(this.type, myUser.type) &&
        Objects.equals(this.institutionlink, myUser.institutionlink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, address, phone, accesslevel, type, institutionlink);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MyUser {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    accesslevel: ").append(toIndentedString(accesslevel)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    institutionlink: ").append(toIndentedString(institutionlink)).append("\n");
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
