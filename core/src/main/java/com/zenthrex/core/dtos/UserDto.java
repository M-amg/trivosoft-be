package com.zenthrex.core.dtos;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UserDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder

@JsonTypeName("User")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-17T15:19:41.340718800+01:00[Africa/Casablanca]")
public class UserDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private String phone;

    private String email;

    private String password;

    private String status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime createdOn;

    /**
     * Gets or Sets role
     */
    public enum RoleEnum {
        BUYER("BUYER"),

        ADMIN("ADMIN"),

        SELLER("SELLER");

        private String value;

        RoleEnum(String value) {
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
        public static RoleEnum fromValue(String value) {
            for (RoleEnum b : RoleEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private RoleEnum role;

    @Valid
    private List<@Valid TokenDto> tokens;

    public UserDto id(Integer id) {
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

    public UserDto firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    /**
     * Get firstname
     * @return firstname
     */

    @Schema(name = "firstname", example = "John", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public UserDto lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    /**
     * Get lastname
     * @return lastname
     */

    @Schema(name = "lastname", example = "Doe", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UserDto phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Get phone
     * @return phone
     */

    @Schema(name = "phone", example = "+1234567890", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserDto email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     * @return email
     */
    @jakarta.validation.constraints.Email
    @Schema(name = "email", example = "john.doe@example.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDto password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Get password
     * @return password
     */

    @Schema(name = "password", example = "password123", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDto status(String status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     * @return status
     */

    @Schema(name = "status", example = "ACTIVE", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDto createdOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Get createdOn
     * @return createdOn
     */
    @Valid
    @Schema(name = "createdOn", example = "2024-07-25T12:34:56Z", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("createdOn")
    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public UserDto role(RoleEnum role) {
        this.role = role;
        return this;
    }

    /**
     * Get role
     * @return role
     */

    @Schema(name = "role", example = "ADMIN", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("role")
    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }


    public UserDto tokens(List<@Valid TokenDto> tokens) {
        this.tokens = tokens;
        return this;
    }

    public UserDto addTokensItem(TokenDto tokensItem) {
        if (this.tokens == null) {
            this.tokens = new ArrayList<>();
        }
        this.tokens.add(tokensItem);
        return this;
    }

    /**
     * Get tokens
     * @return tokens
     */
    @Valid
    @Schema(name = "tokens", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("tokens")
    public List<@Valid TokenDto> getTokens() {
        return tokens;
    }

    public void setTokens(List<@Valid TokenDto> tokens) {
        this.tokens = tokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto user = (UserDto) o;
        return Objects.equals(this.id, user.id) &&
                Objects.equals(this.firstname, user.firstname) &&
                Objects.equals(this.lastname, user.lastname) &&
                Objects.equals(this.phone, user.phone) &&
                Objects.equals(this.email, user.email) &&
                Objects.equals(this.password, user.password) &&
                Objects.equals(this.status, user.status) &&
                Objects.equals(this.createdOn, user.createdOn) &&
                Objects.equals(this.role, user.role) &&
                Objects.equals(this.tokens, user.tokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, phone, email, password, status, createdOn, role, tokens);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserDto {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
        sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
        sb.append("    role: ").append(toIndentedString(role)).append("\n");
        sb.append("    tokens: ").append(toIndentedString(tokens)).append("\n");
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