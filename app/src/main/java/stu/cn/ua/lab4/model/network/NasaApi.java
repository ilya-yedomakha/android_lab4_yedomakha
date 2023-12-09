package stu.cn.ua.lab4.model.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NasaApi {
    @GET("resource/gh4g-9sfh.json")
    Call<List<MeteoriteNetworkEntity>> getMeteorites(@Query("$query") String query);
}
