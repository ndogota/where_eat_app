package com.example.restaurantadvisorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

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
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    
    ApiService service;
    TokenManager tokenManager;
    AwesomeValidation validator;
    Call<AccessToken> call;

    @BindView(R.id.EmailLayout)
    TextInputLayout Email_Input;

    @BindView(R.id.PasswordLayout)
    TextInputLayout Password_Input;

    @OnClick(R.id.go_to_restaurants)
    void goToRestaurant() {
        startActivity(new Intent(LoginActivity.this, RestaurantActivity.class));
    }

    @OnClick(R.id.go_to_register)
    void goToRegister() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @OnClick(R.id.btn_login)
    void login() {
        String email = Objects.requireNonNull(Email_Input.getEditText()).getText().toString();
        String password = Objects.requireNonNull(Password_Input.getEditText()).getText().toString();

        Password_Input.setError(null);
        Email_Input.setError(null);

        if(validator.validate()) {
            call = service.login(email, password);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    Log.w(TAG, "onResponse: " + response);

                    if(response.isSuccessful()) {
                        assert response.body() != null;
                        tokenManager.saveToken(response.body());
                        startActivity(new Intent(LoginActivity.this, RestaurantActivity.class));
                        finish();
                    } else {
                        if(response.code() == 422) {
                            handleErrors(response.errorBody());
                        }
                        if(response.code() == 401) {
                            Toast.makeText(LoginActivity.this, "You have entered an invalid username or password", Toast.LENGTH_LONG).show();
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

    public void setupRules() {
        validator.addValidation(this, R.id.EmailLayout, Patterns.EMAIL_ADDRESS, R.string.err_email);
        validator.addValidation(this, R.id.PasswordLayout, RegexTemplate.NOT_EMPTY, R.string.err_password);
    }

    private void handleErrors(ResponseBody response) {
        ApiError apiError = Utils.convertErrors(response);

        for(Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()) {
            if (error.getKey().equals("email")) {
                Email_Input.setError(error.getValue().get(0));
            }
            if (error.getKey().equals("password")) {
                Password_Input.setError(error.getValue().get(0));
            }
        }
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
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);
        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        setupRules();

        if(tokenManager.getToken().getAccessToken() != null) {
            startActivity(new Intent(LoginActivity.this, RestaurantActivity.class));
            finish();
        }
    }
}
