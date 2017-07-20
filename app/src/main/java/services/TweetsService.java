package services;

import java.util.List;

import okhttp3.RequestBody;
import pojo.AuthResponse;
import pojo.SearchList;
import pojo.SearchResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface TweetsService {

    @Headers({
            "Content-Type: application/x-www-form-urlencoded;charset=UTF-8",
            "User-Agent: Tweets App v1.0"
    })
    @POST("/oauth2/token")
    Call<AuthResponse> getAuthToken(@Body RequestBody data,
                                    @Header("Authorization") String authorization);

    @Headers({
            "User-Agent: Tweets App v1.0"
    })
    @GET
    Call<SearchList> getSearchResults(@Url String url,
                                      @Header("Authorization") String authorization,
                                      @Query("q") String query,
                                      @Query("&esult_type") String type);

    @GET
    Call<SearchList> getSearchResults(@Url String url,
                                      @Header("Authorization") String authorization,
                                      @Query("q") String query,
                                      @Query("&esult_type") String type,
                                      @Query("max_id") String sinceId);


}
