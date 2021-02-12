package com.example.dndlist.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dndlist.MainActivity;
import com.example.dndlist.R;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;

import java.io.IOException;

public class Login extends AppCompatActivity implements View.OnClickListener {
  private final static String TAG = "loginActivity";
  /* Доступы для гугловской авторизации */
  private final static String G_PLUS_SCOPE = "oauth2:https://www.googleapis.com/auth/plus.me";
  private final static String USERINFO_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
  private final static String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
  private final static String SCOPES = G_PLUS_SCOPE + " " + USERINFO_SCOPE + " " + EMAIL_SCOPE;
  private final static int G_SUCCESS_REQUEST = 123;

  /* Кнопочки */
  private final static int loginBtn_id = R.id.btn_login;
  private final static int linkSugnupBtn_id = R.id.link_signup;
  private final static int googleLoginBtn_id = R.id.google_login;
  Button login_btn;
  TextView linkSugnup_btn;
  View googleLogin_btn;

  /* Текстовые поля */
  private final static int email_field_id = R.id.input_email;
  private final static int psw_field_id = R.id.input_password;
  EditText email_field;
  EditText psw_field;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    login_btn = (Button) findViewById(loginBtn_id);
    linkSugnup_btn = (TextView) findViewById(linkSugnupBtn_id);
    googleLogin_btn = (View) findViewById(R.id.google_login);
    login_btn.setOnClickListener(this);
    linkSugnup_btn.setOnClickListener(this);
    googleLogin_btn.setOnClickListener(this);

    email_field = (EditText) findViewById(email_field_id);
    psw_field = (EditText) findViewById(psw_field_id);
  }

  public void onClick(View v) {
    switch (v.getId()) {
      case loginBtn_id: { login(); break; }
      case linkSugnupBtn_id: { toSignUpPage(); break; }
      case googleLoginBtn_id: { googleAuth(); }
    }
  }

  protected void login() {
    Log.d(TAG, "start login");
    String email = email_field.getText().toString();
    String psw = psw_field.getText().toString();
    Log.d(TAG, String.format("Entered data: Email: %s,  Psw: %s", email, psw));

    /* TODO: После появления api добавить функционал*/
  }

  protected void toMainPage() {
    Log.d(TAG, "start toMainPage");
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }

  protected void toSignUpPage() {
    Log.d(TAG, "start toSignUpPage");
    Intent intent = new Intent(this, SignUp.class);
    startActivity(intent);
    finish();
  }

  protected void googleAuth() {
    Log.d(TAG, "start googleAuth");
    Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"},
            false, null, null, null, null);
    startActivityForResult(intent, G_SUCCESS_REQUEST);
  }

  /* Метод получения токена из гугла, вызывается сам */
  protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == G_SUCCESS_REQUEST && resultCode == RESULT_OK) {
      final String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
      AsyncTask<Void, Void, String> getToken = new AsyncTask<Void, Void, String>() {
        @Override
        protected String doInBackground(Void... params) {
          String token = "";
          try {
            token = GoogleAuthUtil.getToken(Login.this, accountName, SCOPES);
            return token;

          } catch (UserRecoverableAuthException userAuthEx) {
            startActivityForResult(userAuthEx.getIntent(), 123);
          } catch (IOException ioEx) {
            Log.d(TAG, "IOException");
          } catch (GoogleAuthException fatalAuthEx) {
            Log.d(TAG, "Fatal Authorization Exception" + fatalAuthEx.getLocalizedMessage());
          }
          return token;
        }

        @Override
        protected void onPostExecute(String token) {
          reg(token);
        }

      };
      getToken.execute(null, null, null);
    }
  }

  /* TODO: вызовется после получения токена гугла, его надо будет отправлять на наш бек */
  private void reg(String token) {
    Log.d(TAG, "resToken: " + token);
    toMainPage();
    finish();
  }
}