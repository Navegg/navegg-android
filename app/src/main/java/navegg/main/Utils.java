package navegg.main;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by william on 23/05/17.
 */

public class Utils {

    private Context context;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    public Utils(Context context) {
        this.context = context;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            List<ActivityManager.AppTask> taskInfo = null;
        }else{
            List<ActivityManager.RunningTaskInfo> taskInfo = null;
        }
        this.mSharedPreferences = context.getSharedPreferences("NVGSDK", Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();

    }

    public String getLinkPlayStore() {

        final String appPackageName = context.getPackageName();
        return "http://play.google.com/store/apps/details?id=" + appPackageName;

    }

    public boolean verifyConnection() {
        boolean connected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        connected = true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        connected = true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                        connected = true;
                    }
                }
            }
        }
        return connected;
    }

    public boolean verifyConnectionWifi() {
        boolean connected;
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = conectivtyManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE || activeNetwork.getType() == ConnectivityManager.TYPE_ETHERNET || activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                connected = true;
            } else {
                connected = false;
            }
        } else {
            connected = false;
        }

        return connected;
    }

    public String dateToString(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
