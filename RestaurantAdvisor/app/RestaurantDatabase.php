<?php


namespace App;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class RestaurantDatabase
{
    function getRestaurants() {
        return DB::table("restaurant")->select('*')->get();
    }

    function buildData(Request $request) {
        $name = $request->input('name');
        $description = $request->input('description');
        $grade = $request->input('grade');
        $localization = $request->input('localization');
        $phone_number = $request->input('phone_number');
        $website = $request->input('website');
        $hours = $request->input('hours');
        return array('name' => $name, "description" => $description, "grade" => $grade, "localization" => $localization, "phone_number" => $phone_number, "website" => $website, "hours" => $hours);

    }

    function postRestaurant(Request $request) {
       return DB::table('restaurant')->insert($this->buildData($request));
    }

    function putRestaurant($id, Request $request) {
       return $Menu = DB::table('restaurant')->where('id', $id)->update($this->buildData($request));
    }

    function deleteRestaurant($id) {
        if (DB::table('menu')->where('Restaurant_id', $id)->exists())
            DB::table('menu')->where('Restaurant_id', $id)->delete();
        return DB::table('restaurant')->where('id', $id)->delete();
    }
}
