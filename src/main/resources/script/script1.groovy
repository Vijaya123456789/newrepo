import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.time.*;


def Message processData(Message message) {
    
    def map = message.getHeaders();
    
    def certExpirydatenew = map.get("CertExpiryDate");                          //  2038-01-15T12:00:00.000            //     2021-07-23T07:42:18.000

     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");                 //  java.text.SimpleDateFormat@f67a0200
     
     Calendar cal = Calendar.getInstance();
     message.setProperty("cal1",cal)
     
     def currentdate = sdf.format(cal.getTime());                               // 2021-06-03
     message.setProperty("currentdate1",currentdate)
     
     Date date1 = sdf.parse(certExpirydatenew);                                 //  Fri Jul 23 00:00:00 UTC 2021
     message.setProperty("date11",date1);
     
     Date date2 = sdf.parse(currentdate);                                       //  Thu Jun 03 00:00:00 UTC 2021
     message.setProperty("date22",6);
     
     long millisec1 = date1.getTime();                                          // 1952035200000
     long millisec2 = date2.getTime();       
     long diff = millisec1 - millisec2;                                         // 375840000000

     String days = diff / (24 * 60 * 60 * 1000);
     
     message.setHeader("days", days);                                           // 4350

return message;
}




