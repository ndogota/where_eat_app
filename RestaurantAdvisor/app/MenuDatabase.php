<?php


namespace App;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class MenuDatabase
{
    function getMenu($RestaurantId) {
        return DB::table("menu")->where("Restaurant_id", "=", $RestaurantId)->get();
    }

    function buildData($RestaurantId, Request $request) {
        $name = $request->input('name');
        $description = $request->input('description');
        $price = $request->input('price');
        return array('name' => $name, "description" => $description, "price" => $price, "Restaurant_id" => $RestaurantId);
    }

    function postMenu($RestaurantId, Request $request) {
        return DB::table('menu')->insert($this->buildData($RestaurantId, $request));
    }

    function putMenu($RestaurantId, $MenuId, Request $request) {
        return DB::table('menu')->where('id', $MenuId)->update($this->buildData($RestaurantId, $request));
    }

    function deleteMenu($id) {
        return DB::table('menu')->where('id', $id)->delete();
    }
}
