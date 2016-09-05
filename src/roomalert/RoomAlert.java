/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomalert;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author user
 */
public class RoomAlert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        while (true) {

            try {
                long Ztime = getZeroTime();
                //System.out.println(Ztime);
                String[] parameter = Subdate.subDate(); // return array size 3 วัน เดือน ปี ต่ำแหน่งที่ 0 คือวัน ต่ำแหน่งที่ 1 คือ เดือน ต่ำแหน่งที่ 3 คือปี 
                /*  String url = "https://webapp1.sit.kmutt.ac.th/booking/web/day.php?day=" + parameter[0] + "&month=" + parameter[1] + 
                "&year=" + parameter[2]+"&area="+1; // area 1 = sit floor 1 , area 2 = cb 2 */
                String search = "https://webapp1.sit.kmutt.ac.th/booking/web/report.php?"
                        + "From_day=" + parameter[0]
                        + "&From_month=" + parameter[1]
                        + "&From_year=" + parameter[2]
                        + "&To_day=" + parameter[0]
                        + "&To_month=" + parameter[1]
                        + "&To_year=" + parameter[2]
                        + "&areamatch=1"
                        + "&roommatch=1%2F5" // train 1/1  = 1%2F1 < %2F แทน / >
                        + "&typematch%5B%5D=A"
                        + "&namematch="
                        + "&descrmatch="
                        + "&creatormatch="
                        + "&summarize=1"
                        + "&sortby=r"
                        + "&display=e"
                        + "&sumby=d";
                Document doc = Jsoup.connect(search).post();
                System.out.println(doc.toString());
                Elements time = doc.getElementsByClass("BR");
                Elements desc = doc.getElementsByClass("BL");
                ArrayList<String> timers = new ArrayList<String>();
                
                for (Element e : time) {
                    String k = e + "";
                    timers.add(k.substring(k.indexOf(parameter[2]) + parameter[2].length() + 1, k.indexOf(" -") + 1).trim());
                }
                ArrayList<String> end = new ArrayList<String>();
                //  int count = 0 ;
                for (Element ee : time) {
                    String k = ee + "";
                    end.add(k.substring(k.lastIndexOf(parameter[2]) + parameter[2].length() + 1, k.indexOf("</td>")).trim());
                    //   count++;
                }
            
                        System.out.println(Math.abs(convertToTime(end.get(0)).getTime()-convertToTime(timers.get(0)).getTime()));
                System.out.println("Test Begin at :: "+new Date());
                //System.out.println(convertToTime(timers.get(0)));
                      
               for(int i = 0 ; i <= timers.size()-1;i++ ){
                   if(getNow().getTime() > (convertToTime(timers.get(i))).getTime()){
                       continue;
                   }else{
                       Thread.sleep(Math.abs(convertToTime(timers.get(i)).getTime()-getMillisecondNow()));
                       System.out.println("class begin at :"+new Date());
                       Thread.sleep(Math.abs(convertToTime(end.get(i)).getTime()-convertToTime(timers.get(i)).getTime()));
                       System.out.println("class end at :"+new Date());
                   }
               }
                System.out.println("End of Today");
                Thread.sleep(Math.abs(Ztime-getMillisecondNow()));
                //-------------------------------------------------//
            } catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            } catch (ParseException ex) {
                System.out.println(ex);
            }
        }
    }

    public static java.util.Date convertToTime(String time) {
        try {

            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss"); //if 24 hour format
            java.util.Date d1 = (java.util.Date) format.parse(time);
            return d1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static long getZeroTime() throws ParseException {
        try {

            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss"); //if 24 hour format
            java.util.Date d1 = (java.util.Date) format.parse("24:00:00");
            return d1.getTime();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public static long getMillisecondNow() throws ParseException{
    
     Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       return ((java.util.Date)sdf.parse(sdf.format(cal.getTime())+"")).getTime();
       
    }
    public static java.util.Date getNow() throws ParseException{
     Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return (java.util.Date) sdf.parse(sdf.format(cal.getTime()));
    }

}
