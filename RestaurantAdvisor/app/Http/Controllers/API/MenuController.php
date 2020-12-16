<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\MenuDatabase;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Validator;

class MenuController extends Controller
{
    function getMenuList($RestaurantId) {
        $Menus = new MenuDatabase();
        $Menus = $Menus->getMenu($RestaurantId);
        if (count($Menus) > 0)
            return response()->json($Menus, "200");
        else
            return response()->json(null, "404");
    }

    function createMenu($RestaurantId, Request $request) {
        $validator = Validator::make($request->all(), [
            'name' => 'required',
            'description' => 'required',
            'price' => 'required',
        ]);
        if ($validator->fails())
            return response()->json(['error'=>$validator->errors()], 400);

        if (DB::table('restaurant')->where('id', $RestaurantId)->exists()) {
            $Menu = new MenuDatabase();
            $Menu->postMenu($RestaurantId, $request);
            return response()->json("The menu was added in restaurant successfully", 201);
        } else
            return response()->json("The restaurant doesn't exist", 400);
    }

    function modifyMenu($RestaurantId, $MenuId, Request $request) {
        $validator = Validator::make($request->all(), [
            'name' => 'required',
            'description' => 'required',
            'price' => 'required',
        ]);
        if ($validator->fails())
            return response()->json(['error'=>$validator->errors()], 400);

        if (DB::table('restaurant')->where('id', $RestaurantId)->exists() and DB::table('menu')->where('id', $MenuId)->exists()) {
            $Menu = new MenuDatabase();
            $Menu->putMenu($RestaurantId, $MenuId, $request);
            return response()->json("The menu '" .$request->input('name'). "' of restaurant was modified successfully", 200);
        } else
            return response()->json("The restaurant or menu doesn't exist", 400);
    }

    function removeMenu($RestaurantId, $MenuId) {
        if (DB::table('restaurant')->where('id', $RestaurantId)->exists()) {
            if (DB::table('menu')->where('id', $MenuId)->where('restaurant_id', $RestaurantId)->exists()) {
                $Menu = new MenuDatabase();
                $Menu->deleteMenu($MenuId);
                return response()->json("The menu was deleted successfully", 200);
            } else
                return response()->json("The menu doesn't exist", 400);
        } else
            return response()->json("The restaurant doesn't exist", 400);
    }
}
