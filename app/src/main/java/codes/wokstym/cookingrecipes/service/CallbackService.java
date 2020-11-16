package codes.wokstym.cookingrecipes.service;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackService<R, C extends CallHandler<R>> implements Callback<R> {

    private C responseClass;

    public CallbackService(C responseClass) {
        this.responseClass = responseClass;
    }

    @Override
    public void onResponse(Call<R> call, Response<R> response) {
        if (response.isSuccessful()) {
            R responseBody = response.body();
            responseClass.handle((codes.wokstym.cookingrecipes.R) responseBody);
        } else {
            Log.d("Przepis", String.valueOf(response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<R> call, Throwable t) {
        t.printStackTrace();
    }
}
