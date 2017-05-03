package mathew.navjacinth.com.retrofitdemo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Navjacinth Mathew on 1/19/2017.
 */

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://navjacinth9.000webhostapp.com/json/";

    private RetrofitInstance() {}

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
