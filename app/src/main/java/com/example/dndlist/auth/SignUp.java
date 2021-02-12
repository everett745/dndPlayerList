package com.example.dndlist.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dndlist.MainActivity;
import com.example.dndlist.R;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
  private final static String TAG = "signUpActivity";
  /* Кнопочки */
  private final static int createAccount_id = R.id.btn_signup;
  private final static int linkLogin_id = R.id.link_login;
  Button createAccount_btn;
  TextView linkLogin_btn;

  /* Текстовые поля */
  private final static int name_field_id = R.id.input_name;
  private final static int email_field_id = R.id.input_email;
  private final static int psw_field_id = R.id.input_password;
  private final static int psw_re_field_id = R.id.input_reEnterPassword;
  EditText name_field;
  EditText email_field;
  EditText psw_field;
  EditText psw_re_field;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);

    createAccount_btn = (Button) findViewById(createAccount_id);
    linkLogin_btn = (TextView) findViewById(linkLogin_id);
    createAccount_btn.setOnClickListener(this);
    linkLogin_btn.setOnClickListener(this);

    name_field = (EditText) findViewById(name_field_id);
    email_field = (EditText) findViewById(email_field_id);
    psw_field = (EditText) findViewById(psw_field_id);
    psw_re_field = (EditText) findViewById(psw_re_field_id);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case createAccount_id: { register(); break; }
      case linkLogin_id: { toLoginPage(); }
    }
  }

  protected void register() {
    Log.d(TAG, "start register");
    String name = name_field.getText().toString();
    String email = email_field.getText().toString();
    String psw = psw_field.getText().toString();
    String psw_re = psw_re_field.getText().toString();
    Log.d(TAG, String.format("Entered data: Name: %s, Email: %s, Psw: %s, PswRe: %s", name, email, psw, psw_re));

    /* TODO: После появления api добавить функционал*/
  }

  private void toLoginPage() {
    Log.d(TAG, "start toLoginPage");
    Intent intent = new Intent(this, Login.class);
    startActivity(intent);
    finish();
  }
}