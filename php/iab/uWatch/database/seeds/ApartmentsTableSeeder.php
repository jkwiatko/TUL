<?php

use Illuminate\Database\Seeder;
use uWatch\User;
use uWatch\Apartment;
use uWatch\Property;

class ApartmentsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $i = 0;
        $j = 1;
        $property;
        $users = User::all();

        foreach($users as $user){
            ++$i;
            if($i < 2){
                $property = Property::find($j);
                ++$j;
            }
            $apartment  = new Apartment();
            $apartment  -> number = $i;
            $apartment -> user()        -> associate($user);
            $apartment -> property()    -> associate($property);
            $apartment -> save();
            if($i > 11) $i = 0;
        }
    }
}
