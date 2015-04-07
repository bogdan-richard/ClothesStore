package dd.casestudy.clothesstore.support;

import com.squareup.okhttp.OkHttpClient;

import dd.casestudy.clothesstore.api.ClothesStoreApi;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by KITKAT on 2015.04.06..
 */
public class RestClient {

    private static ClothesStoreApi REST_CLIENT;
    private static String SERVER_URL =
            "http://private-9aecb3-ddshop.apiary-mock.com";

    static {
        setupRestClient();
    }

    private RestClient() {}

    public static ClothesStoreApi get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(SERVER_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(ClothesStoreApi.class);
    }
}

