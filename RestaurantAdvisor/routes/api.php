<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/


Route::get('restaurant/{id}/menus', 'API\MenuController@getMenuList');
//Route::get('restaurants', 'API\RestaurantController@getRestaurantsList');

Route::post('auth', 'API\UserController@login');
Route::post('register', 'API\UserController@register');
Route::get('restaurants', 'API\RestaurantController@getRestaurantsList');

Route::group(['middleware' => 'auth:api'], function(){
    Route::post('logout', 'API\UserController@logout');
    Route::get('users', 'API\UsersController@returnUsersList');
    Route::post('details', 'API\UserController@details');
    Route::post('restaurant', 'API\RestaurantController@createRestaurant');
    Route::put('restaurant/{id}', 'API\RestaurantController@modifyRestaurant');
    Route::delete('restaurant/{id}', 'API\RestaurantController@removeRestaurant');
    Route::post('restaurant/{id}/menu', 'API\MenuController@createMenu');
    Route::put('restaurant/{id}/menu/{id2}', 'API\MenuController@modifyMenu');
    Route::delete('restaurant/{id}/menu/{id2}', 'API\MenuController@removeMenu');
});

