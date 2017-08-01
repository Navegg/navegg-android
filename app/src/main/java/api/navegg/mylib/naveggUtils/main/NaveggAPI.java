package api.navegg.mylib.naveggUtils.main;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import api.navegg.mylib.naveggUtils.base.ServerAPI;
import api.navegg.mylib.naveggUtils.bean.User;
import api.navegg.mylib.naveggUtils.connection.SendData;

//import android.support.v7.widget.Toolbar;


public class NaveggAPI {

    private Context context;
    private int codAccount;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;
    private Util util;
    private ServerAPI apiService;
    private String mActivity;
    private SendData sendData;
    private User user = new User();

    public NaveggAPI(Context ctx, final int codAccount) {
        this.context = ctx;
        this.codAccount = codAccount;

        setDataDevice();
    }

    private void setDataDevice() {

        util = new Util(context);

        util.getCallPage();
        util.getVisibleFragment();

        sendData = new SendData(context, codAccount);

        this.mSharedPreferences = context.getSharedPreferences("SDK", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mSharedPreferences.getString("user", "");
        user = gson.fromJson(json, User.class);

        if(user == null) {
            sendData.sendFirstData();
        }


    }



    public void setTrackPage(String mActivity){

        sendData.trackMobile(mActivity);

    }






}
