package org.example.domain;

/**
 * @author by 封心
 * @classname User
 * @description TODO
 * @date 2021/11/11 13:57
 */
public class User {

  private String id;

  private String name;

  private String role_id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole_id() {
    return role_id;
  }

  public void setRole_id(String role_id) {
    this.role_id = role_id;
  }
}
