package com.zenthrex.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * ErrorDto
 */
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Builder

@JsonTypeName("Error")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-08T13:21:08.992748+01:00[Africa/Casablanca]")
public class ErrorDto {

  private Integer code;

  private String message;

  public ErrorDto code(Integer code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */

  @JsonProperty("code")
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public ErrorDto message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */

  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDto error = (ErrorDto) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.message, error.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorDto {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

