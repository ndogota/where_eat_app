<?php


namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\RestaurantDatabase;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Validator;

class RestaurantController extends Controller
{

    function getRestaurantsList() {
        $Restaurants = new RestaurantDatabase();
        $Restaurants = $Restaurants->getRestaurants();
        if (count($Restaurants) > 0)
            return response()->json(['data'=>$Restaurants], "200");
        else
            return response()->json(null, "404");
    }

    function createRestaurant(Request $request) {
        $validator = Validator::make($request->all(), [
            'name' => 'required',
            'description' => 'required',
            'grade' => 'required',
            'localization' => 'required',
            'phone_number' => 'required',
            'website' => 'required',
            'hours' => 'required',
        ]);
        if ($validator->fails())
            return response()->json(['error'=>$validator->errors()], 400);

        $Restaurant = new RestaurantDatabase();
        $Restaurant->postRestaurant($request);
        return response()->json("The restaurant was added successfully", 201);
    }

    function modifyRestaurant($RestaurantId, Request $request) {
        $validator = Validator::make($request->all(), [
            'name' => 'required',
            'description' => 'required',
            'grade' => 'required',
            'localization' => 'required',
            'phone_number' => 'required',
            'website' => 'required',
            'hours' => 'required',
        ]);
        if ($validator->fails())
            return response()->json(['error'=>$validator->errors()], 400);

        $restaurant = DB::select('select name from restaurant where id=?', [$RestaurantId]);
        if (count($restaurant) > 0) {
            $Restaurant = new RestaurantDatabase();
            $Restaurant->putRestaurant($RestaurantId, $request);
            return response()->json("The restaurant was modified successfully", 200);
        } else
            return response()->json("The restaurant doesn't exist", 400);
    }

    function removeRestaurant($RestaurantId) {
        $restaurant = DB::select('select name from restaurant where id=?', [$RestaurantId]);
        if (count($restaurant) > 0) {
            $Restaurant = new RestaurantDatabase();
            $Restaurant->deleteRestaurant($RestaurantId);
            return response()->json("The restaurant '" .$restaurant[0]->name. "' was deleted successfully", "200");
        } else
            return response()->json("The restaurant doesn't exist", 400);
    }
}
