package codes.wokstym.cookingrecipes.tasks;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import codes.wokstym.cookingrecipes.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BackendTask<T, S> implements Callback<T> {

    public void callTask(Class<S> serviceClass, LambdaService<T, S> lambdaService) {
        S service = createService(serviceClass);
        Call<T> call = lambdaService.execute(service);
        call.enqueue(this);
    }

    public S createService(Class<S> serviceClass) {
        Retrofit retrofit = prepareRetrofit();
        return retrofit.create(serviceClass);
    }

    private Retrofit prepareRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.RecipesURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @FunctionalInterface
    public interface LambdaService<T, S> {
        Call<T> execute(S service);
    }
}
