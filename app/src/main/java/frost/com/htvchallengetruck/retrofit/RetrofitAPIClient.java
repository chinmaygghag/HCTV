package frost.com.htvchallengetruck.retrofit;

import frost.com.htvchallengetruck.AcceptResponseModel;
import frost.com.htvchallengetruck.ResponseModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by chinmayghag on 11/02/18.
 */

public interface RetrofitAPIClient {

    String BASE_URL = "http://146.148.111.64";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitAPIClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @FormUrlEncoded
    @POST("/registerTruckDevice")
    Call<ResponseModel> callTruckService(@Field("id") String id, @Field("fcmtoken") String tokenId);

    @FormUrlEncoded
    @POST("/accept")
    Call<AcceptResponseModel> callAcceptAPI(@Field("vehilcleID") String vehicleId);


}
