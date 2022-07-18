<?php

use Illuminate\Database\Seeder;
use uWatch\Property;
use uWatch\Post;
use uWatch\User;

class PostTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $faker = Faker\Factory::create('pl_PL');
        $properties = Property::all();
        foreach($properties as $property){
            $max = $property->id * 12;
            $min = $max - 11;
            for($i=1; $i < 101; ++$i){
                $randomUserId = $faker -> numberBetween($min, $max);       
                $user = $property -> users() -> find($randomUserId);
                $post = new Post();
                $post -> content = $faker -> realText($maxNbChars = 200, $indexSize = 2);
                $post -> title = $faker -> realText($maxNbChars = 20, $indexSize = 2);
                $post -> property() -> associate($property);
                $post -> user() -> associate($user);
                $post -> save();
            }
        }
    }
}
