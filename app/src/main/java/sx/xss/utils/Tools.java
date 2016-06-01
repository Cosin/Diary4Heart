package sx.xss.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;

import sx.xss.diary4heart.R;

/**
 * Created by Hm on 2016/5/29.
 */

public class Tools {

    public static String getTime(){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date(System.currentTimeMillis());
        return f.format(d);
    }

}
