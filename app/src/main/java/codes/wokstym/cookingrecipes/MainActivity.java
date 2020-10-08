package codes.wokstym.cookingrecipes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import lombok.SneakyThrows;

public class MainActivity extends AppCompatActivity {

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t = new Thread();
        t.start();
        t.join();
    }
}