<?php

use Illuminate\Database\Seeder;

class MenuSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('menu')->insert([
            'id' => 1,
            'name' => 'Menu A5',
            'description' => '8 sushis 4 makis 4 california rolls',
            'price' => 16.5,
            'Restaurant_id' => 1,
        ]);

        DB::table('menu')->insert([
            'id' => 2,
            'name' => 'Menu B3',
            'description' => '32 sushis 64 makis 2 california rolls',
            'price' => 89.9,
            'Restaurant_id' => 2,
        ]);
    }
}
