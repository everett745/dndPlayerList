package com.example.dndlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.dndlist.auth.Login;
import com.example.dndlist.auth.SignUp;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    /* TODO: забей, просто чтобы логинка сразу открывалась (потом исправить) */
    Intent intent = new Intent(this, Login.class);
    startActivity(intent);
    finish();
  }
}