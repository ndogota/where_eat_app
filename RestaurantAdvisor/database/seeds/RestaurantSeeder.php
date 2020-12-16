<?php

use Illuminate\Database\Seeder;

class RestaurantSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('restaurant')->insert([
            'id' => 1,
            'name' => 'MacDonald\'s',
            'description' => 'Classic, long-running fast-food chain known for its burgers, fries & shakes.',
            'grade' => 3.2,
            'localization' => 'Centre Commercial Grand Ciel, 30 Boulevard Paul Vaillant Couturier, 94200 Ivry-sur-Seine',
            'phone_number' => '01 49 60 62 60',
            'website' => 'macdonalds.fr',
            'hours' => 'Monday-Saturday 9AM–9PM, Sunday Closed',
        ]);

        DB::table('restaurant')->insert([
            'id' => 2,
            'name' => 'KFC',
            'description' => 'Classic, long-running fast-food chain known for its burgers, fries & shakes.',
            'grade' => 3.9,
            'localization' => 'Centre Commercial Grand Ciel, 28 Boulevard Paul Vaillant Couturier, 94200 Ivry-sur-Seine',
            'phone_number' => '10 09 00 68 68',
            'website' => 'kfc.fr',
            'hours' => 'Monday-Saturday 9AM–9PM, Sunday Closed',
        ]);
    }
}
