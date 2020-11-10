package ye.qiwu.community.cache;

import org.springframework.util.StringUtils;
import ye.qiwu.community.dto.TagsDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
 public static List<TagsDTO>  getTags(){
  String[] programs={"javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net",
   "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"};
  String[] frameworks={"laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"};
 // String[] servers={"linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"};

  List<TagsDTO> tagsDTO = new ArrayList<>();
  TagsDTO program = new TagsDTO();
  program.setCategoryName("开发语言");
  program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net",
   "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
  tagsDTO.add(program);

  TagsDTO framework = new TagsDTO();
  framework.setCategoryName("平台框架");
  framework.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
  tagsDTO.add(framework);

  TagsDTO server = new TagsDTO();
  server.setCategoryName("服务器");
  server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
  tagsDTO.add(server);

  TagsDTO db = new TagsDTO();
  db.setCategoryName("数据库");
  db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
  tagsDTO.add(db);

  TagsDTO tool = new TagsDTO();
  tool.setCategoryName("开发工具");
  tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
  tagsDTO.add(tool);

  return tagsDTO;
 }

 public static String filterInValue(String tags){
  String[] split = StringUtils.split(tags, ",");
  List<TagsDTO> tagsDTOS = TagCache.getTags();
  List<String> tagList = tagsDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
  String inValue = Arrays.stream(split).filter(t -> tagList.contains(t)).collect(Collectors.joining(","));

  return inValue;
 }

}
