package ye.qiwu.community.model;

import lombok.Data;

@Data
public class User1 {
 private Integer id;
 private  String account_Id;
 private String  name;
 private  String token;
 private Long gmtCreate;
 private Long gmtModified;
private String avatarUrl;//GitHub中图片url
 /*
 public Integer getId() {
  return id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public String getAccount_Id() {
  return account_Id;
 }

 public void setAccount_Id(String account_Id) {
  this.account_Id = account_Id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getToken() {
  return token;
 }

 public void setToken(String token) {
  this.token = token;
 }


 public Long getGmtCreate() {
  return gmtCreate;
 }

 public void setGmtCreate(Long gmtCreate) {
  this.gmtCreate = gmtCreate;
 }

 public Long getGmtModified() {
  return gmtModified;
 }

 public void setGmtModified(Long gmtModified) {
  this.gmtModified = gmtModified;
 }

 public String getAvatarUrl() {
  return avatarUrl;
 }

 public void setAvatarUrl(String avatarUrl) {
  this.avatarUrl = avatarUrl;
 }*/
}
