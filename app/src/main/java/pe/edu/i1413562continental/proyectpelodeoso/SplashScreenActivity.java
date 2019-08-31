package pe.edu.i1413562continental.proyectpelodeoso;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.edu.i1413562continental.proyectpelodeoso.activities.InsertProductActivity;
import pe.edu.i1413562continental.proyectpelodeoso.activities.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        },1000);

    }
}
