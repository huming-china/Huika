package com.huika.hkmall.ui.api;

import com.google.gson.GsonBuilder;
import com.huika.hkmall.GlobalApplication;
import com.huika.hkmall.ui.model.CartChangeNum;
import com.huika.hkmall.ui.model.OneClassifyBean;
import com.huika.hkmall.ui.model.OtherOrders;
import com.huika.hkmall.ui.model.UserModel;
import com.huika.hkmall.ui.model.WealthInfo;
import com.huika.lib.core.model.bean.RequestResult;
import com.huika.lib.core.model.bean.ResultCallback;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Created by zhanghao on 2015/12/16.
 */
public class HuiKaRetrofitApi {
    private static HuiKaRetrofitApi instance;
    private RetrofitService mService;

    public static HuiKaRetrofitApi getInstance()

    {
        if (null == instance) {
            synchronized ((HuiKaRetrofitApi.class)) {
                if (null == instance) {
                    instance = new HuiKaRetrofitApi();
                }
            }
        }
        return instance;
    }

    /**
     * 默认加载条数为20条
     */
    public static final int LOAD_LIMIT = 20;

    /**
     * 默认起始加载页
     */
    public static final int LOAD_START = 1;

    private HuiKaRetrofitApi() {
        Cache cache;
        OkHttpClient okHttpClient;
        try {
            File cacheDir = new File(GlobalApplication.getContext().getCacheDir().getPath(), "huika.json");
            cache = new Cache(cacheDir, 10 * 1024 * 1024);
            okHttpClient = new OkHttpClient();
            okHttpClient.setCache(cache);
        } catch (Exception e) {

        }

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.16.240:9009")
                .setClient(new OkClient())
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mService = restAdapter.create(HuiKaRetrofitApi.RetrofitService.class);
    }

    public RetrofitService get() {
        return mService;
    }

    public interface RetrofitService {

        //get请求，url为https://api.github.com/users/...传入的String user是/后面的参数
//        @GET("/users/{user}")
//        Observable<RetrofitTestBean> getRetrofit(@Path("user") String user);

        /**
         * @param userId
         * @return
         * @POST("")这里括号里面如果不需要拼接url可以直接写个/ post请求，传入表单需要用@FormUrlEncoded注解
         * 完整rul是http://api.huikamall.com/Product/GetBrandRecommendList
         * Constants.BASE_URL=http://api.huikamall.com
         */
//        @FormUrlEncoded
//        @POST("/Product/GetBrandRecommendList")
//        Observable<RequestResult<List<BrandRecommendBean>>> getBrandBean(@Field("userId") String userId);

        //用callback返回结果
//        @FormUrlEncoded
//        @POST("/Product/GetBrandRecommendList")
//        void getBrandBean(@Field("userId") String userId, Callback<RequestResult<List<BrandRecommendBean>>> callback);

        /**
         * 上传文件
         *
         * @param
         * @param callback
         */
        @Multipart
        @POST("/User/UploadShareImg")
        void uploadFile(
                @Part("fileName") TypedFile typedFile,
                @Part("userId") String userId,
                @Part("validateKey") String validateKey,
                Callback<RequestResult<Object>> callback);


        @FormUrlEncoded
        @POST("/User/LoginValidate")
        void login(@Field("account") String account,
                   @Field("password") String password,
//                   @Field("type") String type,
                   @Field("registerSource") String registerSource,
//                   @Field("appVersion") String appVersion,
                   ResultCallback<RequestResult<UserModel>> callback);

        @FormUrlEncoded
        @POST("/Product/GetProductFristClassifyList")
        void GetProductFristClassifyList(
                @Field("userId") String userId,
                ResultCallback<RequestResult<ArrayList<OneClassifyBean>>> callback);

        @FormUrlEncoded
        @POST("/Support/GetConfigInfo")
        void GetConfigInfo(
                @Field("") String param,
                ResultCallback<RequestResult<HashMap<String,String>>> callback);

        @FormUrlEncoded
        @POST("/Support/GetCartNum")
        void GetCartNum(
                @Field("userId") String userId,
                ResultCallback<RequestResult<CartChangeNum>> callback);

      @FormUrlEncoded
      @POST("User/GetUserInfo")
      void GetUserInfo(
          @Field("userId") String userId,
          @Field("appVersion") int appVersion,
          ResultCallback<RequestResult<UserModel>> callback);

      @FormUrlEncoded
      @POST("HF/Order/GetUserBalance2")
      void GetUserBalance(
          @Field("userId") String userId,
          ResultCallback<RequestResult<WealthInfo>> callback);

      @FormUrlEncoded
      @POST("HF/Support/OtherOrders")
      void GetOtherOrders(
          @Field("userId") String userId,
          ResultCallback<RequestResult<OtherOrders>> callback);
    }
}
