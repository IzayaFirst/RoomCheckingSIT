/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomalert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Subdate {

    public static String[] subDate() {
        String[] a = new String[3];
        Date date = new Date();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        a[0] = calendar1.get(Calendar.DAY_OF_MONTH) + "";
        a[1] = calendar1.get(Calendar.MONTH) + 1 + "";
        a[2] = calendar1.get(Calendar.YEAR) - 543 + "";
        return a;
    }

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        // System.out.println( sdf.format(cal.getTime()) );
        System.out.println(sdf.format(cal.getTime()));

    }

}
