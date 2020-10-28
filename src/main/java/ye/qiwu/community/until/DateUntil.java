package ye.qiwu.community.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
*   Date-String 格式之间的转换
* */
public class DateUntil {
  /*
  * Date转换为格式String
  * */
 public static  String  getDateString(Long date){
  Date date1 = new Date(date);
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String s = sdf.format(date1);
  return s;


 }

 public static  Date stringToDate(String dateStr,String formate){
  Date date1;
  try {
   SimpleDateFormat sdf = new SimpleDateFormat(formate);
    date1 = sdf.parse(dateStr);
  } catch (ParseException e) {
   e.printStackTrace();
   return null;
  }

return date1;
 }
}
