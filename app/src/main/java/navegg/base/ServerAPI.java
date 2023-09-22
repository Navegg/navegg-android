package navegg.base;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface ServerAPI {

    @GET("/app")
    Call<ResponseBody> getUser(@Query("acc") int acc, @Query("devid") String mDeviceId);

    @POST("/sdkreq")
    Call<Void> sendDataTrack(@Body  RequestBody mTrack);

    @POST("/sdkinfo")
    Call<ResponseBody> sendDataMobileInfo(@Body RequestBody mMobileInfo);

    @POST("/cus")
    Call<Void> sendCustomId(@Query("acc") int acc, @Query("cus") int id_custom, @Query("id") String id_user);

    @GET("/app")
    Call<ResponseBody> getSegments(@Query("acc") int acc,
                                   @Query("wst") int wst,
                                   @Query("v") int v,
                                   @Query("id") String id_user,
                                   @Query("asdk") String version_sdk,
                                   @Query("wct") int wct,
                                   @QueryMap Map<String,String> onboard);

    @GET("/cd")
    Call<Void> setOnBoarding(@QueryMap Map<String,String> params, @Query("id") String id, @Query("prtid") int partnerId);
}
