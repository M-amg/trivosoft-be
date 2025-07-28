package com.zenthrex.core.dtos;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * TokenDto
 */
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Builder

@JsonTypeName("Token")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-17T15:19:41.340718800+01:00[Africa/Casablanca]")
public class TokenDto {

  private Integer id;

  private String token;

  /**
   * Gets or Sets tokenType
   */
  public enum TokenTypeEnum {
    BEARER("BEARER");

    private String value;

    TokenTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TokenTypeEnum fromValue(String value) {
      for (TokenTypeEnum b : TokenTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TokenTypeEnum tokenType;

  private Boolean revoked;

  private Boolean expired;

  private UserDto user;

  public TokenDto id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public TokenDto token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  */
  
  @Schema(name = "token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public TokenDto tokenType(TokenTypeEnum tokenType) {
    this.tokenType = tokenType;
    return this;
  }

  /**
   * Get tokenType
   * @return tokenType
  */
  
  @Schema(name = "tokenType", example = "BEARER", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tokenType")
  public TokenTypeEnum getTokenType() {
    return tokenType;
  }

  public void setTokenType(TokenTypeEnum tokenType) {
    this.tokenType = tokenType;
  }

  public TokenDto revoked(Boolean revoked) {
    this.revoked = revoked;
    return this;
  }

  /**
   * Get revoked
   * @return revoked
  */
  
  @Schema(name = "revoked", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("revoked")
  public Boolean getRevoked() {
    return revoked;
  }

  public void setRevoked(Boolean revoked) {
    this.revoked = revoked;
  }

  public TokenDto expired(Boolean expired) {
    this.expired = expired;
    return this;
  }

  /**
   * Get expired
   * @return expired
  */
  
  @Schema(name = "expired", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expired")
  public Boolean getExpired() {
    return expired;
  }

  public void setExpired(Boolean expired) {
    this.expired = expired;
  }

  public TokenDto user(UserDto user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @Valid 
  @Schema(name = "user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user")
  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenDto token = (TokenDto) o;
    return Objects.equals(this.id, token.id) &&
        Objects.equals(this.token, token.token) &&
        Objects.equals(this.tokenType, token.tokenType) &&
        Objects.equals(this.revoked, token.revoked) &&
        Objects.equals(this.expired, token.expired) &&
        Objects.equals(this.user, token.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, token, tokenType, revoked, expired, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("\n");
    sb.append("    revoked: ").append(toIndentedString(revoked)).append("\n");
    sb.append("    expired: ").append(toIndentedString(expired)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

