package codes.wokstym.cookingrecipes;

import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class Thread extends java.lang.Thread {
    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        String requestJson = "\"09189ba1-de8a-4226-bb35-ab1a97cef800\"";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        String url = BuildConfig.RecipesURL + "ingredient/";
        String answer = restTemplate.postForObject(url, entity, String.class);
        Log.d("Thread", answer );
    }
}
