package com.example.restaurantadvisorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.restaurantadvisorapp.entities.AccessToken;
import com.example.restaurantadvisorapp.entities.ApiError;
import com.example.restaurantadvisorapp.network.ApiService;
import com.example.restaurantadvisorapp.network.RetrofitBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    
    @BindView(R.id.UsernameLayout)
    TextInputLayout Username_Input;

    @BindView(R.id.FirstNameLayout)
    TextInputLayout FirstName_Input;

    @BindView(R.id.NameLayout)
    TextInputLayout Name_Input;

    @BindView(R.id.AgeLayout)
    TextInputLayout Age_Input;

    @BindView(R.id.PasswordLayout)
    TextInputLayout Password_Input;

    @BindView(R.id.EmailLayout)
    TextInputLayout Email_Input;

    @OnClick(R.id.go_to_login)
    void goToRegister() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    ApiService service;
    Call<AccessToken> call;
    AwesomeValidation validator;
    TokenManager tokenManager;

    public void setupRules() {
        validator.addValidation(this, R.id.UsernameLayout, RegexTemplate.NOT_EMPTY, R.string.err_name);
        validator.addValidation(this, R.id.FirstNameLayout, RegexTemplate.NOT_EMPTY, R.string.err_name);
        validator.addValidation(this, R.id.NameLayout, RegexTemplate.NOT_EMPTY, R.string.err_name);
        validator.addValidation(this, R.id.AgeLayout, RegexTemplate.NOT_EMPTY, R.string.err_name);
        validator.addValidation(this, R.id.PasswordLayout, "[a-zA-Z0-9]{6,}", R.string.err_password);
        validator.addValidation(this, R.id.EmailLayout, Patterns.EMAIL_ADDRESS, R.string.err_email);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null) {
            call.cancel();
            call = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);
        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken().getAccessToken() != null) {
            startActivity(new Intent(RegisterActivity.this, RestaurantActivity.class));
            finish();
        }

        setupRules();
    }

    @OnClick(R.id.btn_login)
    void register() {
        String username = Username_Input.getEditText().getText().toString();
        String firstname = FirstName_Input.getEditText().getText().toString();
        String name = Name_Input.getEditText().getText().toString();
        String age = Age_Input.getEditText().getText().toString();
        String password = Password_Input.getEditText().getText().toString();
        String email = Email_Input.getEditText().getText().toString();

        Username_Input.setError(null);
        FirstName_Input.setError(null);
        Name_Input.setError(null);
        Age_Input.setError(null);
        Password_Input.setError(null);
        Email_Input.setError(null);

        validator.clear();

        if (validator.validate()) {
            call = service.register(username, firstname, name, age, password, password, email);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    Log.w(TAG, "onResponse: " + response);
                    if(response.isSuccessful()) {
                        Log.w(TAG, "onResponse : " + response.body());
                        tokenManager.saveToken(response.body());
                        startActivity(new Intent(RegisterActivity.this, RestaurantActivity.class));
                        finish();
                    } else {
                        handleErrors(response.errorBody());
                    }
                }

                private void handleErrors(ResponseBody response) {
                    ApiError apiError = Utils.convertErrors(response);

                    for(Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()) {
                        if (error.getKey().equals("username")) {
                            Username_Input.setError(error.getValue().get(0));
                        }
                        if (error.getKey().equals("firstname")) {
                            FirstName_Input.setError(error.getValue().get(0));
                        }
                        if (error.getKey().equals("name")) {
                            Name_Input.setError(error.getValue().get(0));
                        }
                        if (error.getKey().equals("age")) {
                            Age_Input.setError(error.getValue().get(0));
                        }
                        if (error.getKey().equals("password")) {
                            Password_Input.setError(error.getValue().get(0));
                        }
                        if (error.getKey().equals("email")) {
                            Email_Input.setError(error.getValue().get(0));
                        }
                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Log.w(TAG, "onFailure : " + t.getMessage());
                }
            });
        }
    }
}
