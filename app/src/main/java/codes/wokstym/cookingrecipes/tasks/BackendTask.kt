package codes.wokstym.cookingrecipes.tasks

import codes.wokstym.cookingrecipes.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract class BackendTask<T, S> : Callback<T> {

    fun callTask(serviceClass: Class<S>, lambda: (service: S) -> Call<T>) {
        val service = createService(serviceClass)
        val call: Call<T> = lambda.invoke(service)
        call.enqueue(this)
    }

    private fun createService(serviceClass: Class<S>): S {
        val retrofit: Retrofit = prepareRetrofit()
        return retrofit.create(serviceClass)
    }

    private fun prepareRetrofit(): Retrofit {
        val gson = GsonBuilder()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create()
        val client = OkHttpClient.Builder()
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.RecipesURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

}

fun <S> createService(serviceClass: Class<S>): S {
    val retrofit: Retrofit = prepareRetrofit()
    return retrofit.create(serviceClass)
}

private fun prepareRetrofit(): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .create()
    val client = OkHttpClient.Builder()
        .connectTimeout(45, TimeUnit.SECONDS)
        .readTimeout(45, TimeUnit.SECONDS)
        .writeTimeout(45, TimeUnit.SECONDS)
        .build()
    return Retrofit.Builder()
        .baseUrl(BuildConfig.RecipesURL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
}