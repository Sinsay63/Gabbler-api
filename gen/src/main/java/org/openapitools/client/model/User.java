/*
 * Modèle de l'api Gabbler en mode rest
 * Modèle de l'api Gabbler en mode rest
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * objet correspondant à un utilisateur
 */
@ApiModel(description = "objet correspondant à un utilisateur")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-03-21T23:20:40.670220400+01:00[Europe/Paris]")
public class User {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Integer id;

  public static final String SERIALIZED_NAME_USERNAME = "username";
  @SerializedName(SERIALIZED_NAME_USERNAME)
  private String username;

  public static final String SERIALIZED_NAME_FIRSTNAME = "firstname";
  @SerializedName(SERIALIZED_NAME_FIRSTNAME)
  private String firstname;

  public static final String SERIALIZED_NAME_LASTNAME = "lastname";
  @SerializedName(SERIALIZED_NAME_LASTNAME)
  private String lastname;

  public static final String SERIALIZED_NAME_BIRTHDAY = "birthday";
  @SerializedName(SERIALIZED_NAME_BIRTHDAY)
  private String birthday;

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_BIOGRAPHY = "biography";
  @SerializedName(SERIALIZED_NAME_BIOGRAPHY)
  private String biography;


  public User id(Integer id) {
    
    this.id = id;
    return this;
  }

   /**
   * id de l&#39;utilisateur
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "25", value = "id de l'utilisateur")

  public Integer getId() {
    return id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public User username(String username) {
    
    this.username = username;
    return this;
  }

   /**
   * pseudonyme de l&#39;utilisateur
   * @return username
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "Sinsay", value = "pseudonyme de l'utilisateur")

  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public User firstname(String firstname) {
    
    this.firstname = firstname;
    return this;
  }

   /**
   * prénom de l&#39;utilisateur
   * @return firstname
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "Yanis", value = "prénom de l'utilisateur")

  public String getFirstname() {
    return firstname;
  }


  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }


  public User lastname(String lastname) {
    
    this.lastname = lastname;
    return this;
  }

   /**
   * nom de l&#39;utilisateur
   * @return lastname
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "Houdier", value = "nom de l'utilisateur")

  public String getLastname() {
    return lastname;
  }


  public void setLastname(String lastname) {
    this.lastname = lastname;
  }


  public User birthday(String birthday) {
    
    this.birthday = birthday;
    return this;
  }

   /**
   * date d&#39;anniversaire de l&#39;utilisateur
   * @return birthday
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2001-07-28", value = "date d'anniversaire de l'utilisateur")

  public String getBirthday() {
    return birthday;
  }


  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }


  public User email(String email) {
    
    this.email = email;
    return this;
  }

   /**
   * email de l&#39;utilisateur
   * @return email
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "yanis.houdier@gmail.com", value = "email de l'utilisateur")

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public User biography(String biography) {
    
    this.biography = biography;
    return this;
  }

   /**
   * biographie de l&#39;utilisateur
   * @return biography
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "yanis 21 ans célibataire suuuuu", value = "biographie de l'utilisateur")

  public String getBiography() {
    return biography;
  }


  public void setBiography(String biography) {
    this.biography = biography;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.username, user.username) &&
        Objects.equals(this.firstname, user.firstname) &&
        Objects.equals(this.lastname, user.lastname) &&
        Objects.equals(this.birthday, user.birthday) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.biography, user.biography);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, firstname, lastname, birthday, email, biography);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    birthday: ").append(toIndentedString(birthday)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    biography: ").append(toIndentedString(biography)).append("\n");
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
