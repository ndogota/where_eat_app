package com.example.restaurantadvisorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.restaurantadvisorapp.entities.Restaurant;
import com.example.restaurantadvisorapp.entities.RestaurantsResponse;
import com.example.restaurantadvisorapp.network.ApiService;
import com.example.restaurantadvisorapp.network.RetrofitBuilder;
import com.example.restaurantadvisorapp.restaurant.RestaurantAdapter;
import com.example.restaurantadvisorapp.restaurant.RestaurantList;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantActivity extends AppCompatActivity {
    private static final String TAG_LOG = "MY_LOG_MSG";
    private static final String TAG = "RestaurantActivity";

    private static List<RestaurantList> restaurantList;
    private RecyclerView recyclerViewList;
    private RestaurantAdapter restaurantAdapter;

    Call<RestaurantsResponse> call;
    ApiService service;
    TokenManager tokenManager;

    void goToRestaurantView() {
        startActivity(new Intent(RestaurantActivity.this, RestaurantInfoActivity.class));
        tokenManager.deleteToken();
        finish();
    }

    @OnClick(R.id.btn_logout)
    void logout() {
        startActivity(new Intent(RestaurantActivity.this, LoginActivity.class));
        tokenManager.deleteToken();
    }

    void getRestaurants() {
        recyclerViewList = findViewById(R.id.RestaurantRecyclerView);
        restaurantList = new ArrayList<>();

        call = service.post();
        call.enqueue(new Callback<RestaurantsResponse>() {
            @Override
            public void onResponse(Call<RestaurantsResponse> call, Response<RestaurantsResponse> response) {
                if(response.isSuccessful()) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        Restaurant data = response.body().getData().get(i);
                        restaurantList.add(new RestaurantList(data.getId(), data.getName(), data.getDescription(), data.getGrade(), data.getLocalization(), data.getPhone_number(), data.getWebsite(), data.getHours()));
                    }
                    restaurantAdapter = new RestaurantAdapter(restaurantList);
                    recyclerViewList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerViewList.setAdapter(restaurantAdapter);
                } else {
                    startActivity(new Intent(RestaurantActivity.this, LoginActivity.class));
                    finish();
                    tokenManager.deleteToken();
                }
            }

            @Override
            public void onFailure(Call<RestaurantsResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        ButterKnife.bind(this);

        getRestaurants();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
            call = null;
        }
    }
}
