package com.example.servico_restful.Entities;

public class Actor {
  private final String id;
  private String type;
  private String login;
  private String avatar_url;

  Actor(String id) {
    this.id = id;
  }

  public Actor(String id, String type, String login, String avatar_url) {
    this.id = id;
    this.type = type;
    this.login = login;
    this.avatar_url = avatar_url;
  }

  public String getId() {
    return this.id;
  }
}