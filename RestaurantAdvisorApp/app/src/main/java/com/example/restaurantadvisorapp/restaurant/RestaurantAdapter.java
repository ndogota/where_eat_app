package com.example.restaurantadvisorapp.restaurant;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantadvisorapp.R;
import com.example.restaurantadvisorapp.RestaurantInfoActivity;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private List<RestaurantList> restaurantList;

    public RestaurantAdapter(List<RestaurantList> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.display(restaurantList.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "RestaurantViewHolder";
        
        private TextView nameRestaurant;
        private TextView gradeRestaurant;
        private ConstraintLayout restaurantLayout;

        RestaurantViewHolder(View itemView) {
            super(itemView);
            nameRestaurant = itemView.findViewById(R.id.nameRestaurant);
            gradeRestaurant = itemView.findViewById(R.id.gradeRestaurant);
            restaurantLayout = itemView.findViewById(R.id.constraintLayout);
        }

        void display(RestaurantList restaurantList) {
            nameRestaurant.setText(restaurantList.getName());
            gradeRestaurant.setText(String.valueOf(restaurantList.getGrade()));
            restaurantLayout.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), RestaurantInfoActivity.class);
                intent.putExtra("id", restaurantList.getId());
                intent.putExtra("name", restaurantList.getName());
                intent.putExtra("description", restaurantList.getDescription());
                intent.putExtra("grade", restaurantList.getGrade());
                intent.putExtra("localization", restaurantList.getLocalization());
                intent.putExtra("phone_number", restaurantList.getPhone_number());
                intent.putExtra("website", restaurantList.getWebsite());
                intent.putExtra("hours", restaurantList.getHours());
                v.getContext().startActivity(intent);
            });
        }
    }
}
