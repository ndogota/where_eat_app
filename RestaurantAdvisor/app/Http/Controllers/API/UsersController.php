<?php


namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\UsersDatabase;

class UsersController extends Controller
{
    function returnUsersList()
    {
        $User = new UsersDatabase();
        $Users = $User->getUsers();
        if (count($Users) > 0) {
            return response()->json($Users, "200");
        } else {
            return response()->json(null, "404");
        }
    }
}
