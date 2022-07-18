<?php

use Illuminate\Database\Seeder;
use uWatch\Property;
use uWatch\User;

class superUser extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $porperties = Property::all();
        $user = new User();
        $user -> name       = 'admin';
        $user -> email      = 'admin'.'@example.com';
        $user -> password   = bcrypt('admin');
        $user -> save();
        $user -> roles() -> attach(1);
        foreach($porperties as $property){
            $user -> properties() -> attach($property->id);
        }
    }
}
