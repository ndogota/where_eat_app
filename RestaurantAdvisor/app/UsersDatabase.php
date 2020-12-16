<?php


namespace App;

use Illuminate\Support\Facades\DB;

class UsersDatabase
{
    function getUsers() {
        $Users = DB::table("users")->select('id', 'username', 'firstname', 'name', 'age')->get();
        return $Users;
    }
}
