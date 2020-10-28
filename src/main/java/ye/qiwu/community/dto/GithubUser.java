package ye.qiwu.community.dto;

import lombok.Data;

@Data
public class GithubUser {

 private Long id;
 private String name;
 private String bio;
private String avatarUrl;//GitHub的图片Url地址
/*
 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getBio() {
  return bio;
 }

 public void setBio(String bio) {
  this.bio = bio;
 }*/
}
