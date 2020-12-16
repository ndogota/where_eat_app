package com.example.restaurantadvisorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.restaurantadvisorapp.entities.ApiError;
import com.example.restaurantadvisorapp.entities.RestaurantsResponse;
import com.example.restaurantadvisorapp.network.ApiService;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantInfoActivity extends AppCompatActivity {
    int id;
    String name;
    String description;
    double grade;
    String localization;
    String phone_number;
    String website;
    String hours;

    @BindView(R.id.TitleRestaurant)
    TextView Title_Restaurant;

    @BindView(R.id.DescriptionRestaurant)
    TextView Description_Restaurant;

    @BindView(R.id.LocalizationRestaurant)
    TextView Localization_Restaurant;

    @BindView(R.id.PhoneNumberRestaurant)
    TextView PhoneNumber_Restaurant;

    @BindView(R.id.WebsiteRestaurant)
    TextView Website_Restaurant;

    @BindView(R.id.HoursRestaurant)
    TextView Hours_Restaurant;

    @BindView(R.id.GradeRestaurant)
    TextView Grade_Restaurant;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        ButterKnife.bind(this);

        Intent startingIntent = getIntent();

        id = startingIntent.getIntExtra("id", -1);
        name = startingIntent.getStringExtra("name");
        description = startingIntent.getStringExtra("description");
        grade = startingIntent.getDoubleExtra("grade", 0);
        localization = startingIntent.getStringExtra("localization");
        phone_number = startingIntent.getStringExtra("phone_number");
        website = startingIntent.getStringExtra("website");
        hours = startingIntent.getStringExtra("hours");

        Title_Restaurant.setText(name);
        Description_Restaurant.setText(description);
        Grade_Restaurant.setText(String.valueOf(grade));
        Localization_Restaurant.setText(localization);
        PhoneNumber_Restaurant.setText(phone_number);
        Website_Restaurant.setText(website);
        Hours_Restaurant.setText(hours);
    }
}
