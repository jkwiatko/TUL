<?php

use Illuminate\Database\Seeder;
use uWatch\User;
use uWatch\Property;

class ProperitesTableSeeder extends Seeder
{   
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {   
        $i = 12; // its 12 bc i assumed that property has 4 floors and 12 apartments
        $users = User::all();
        
        $faker = Faker\Factory::create('pl_PL');

        foreach($users as $user){
            if(++$i > 11){
                $property = new Property();
                $property -> city      = $faker -> city();
                $property -> street    = $faker -> streetName();
                $property -> number    = $faker -> numberBetween(1,100);
                $property -> save();
                $i = 0;
            }
            $user -> properties()->attach($property->id);
        }
    }
}
