<?php

use Illuminate\Database\Seeder;
use uWatch\User;
use uWatch\Role;

class UsersTableSeeder extends Seeder
{   
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {

        //creating residents
        for($i=0 ;$i < 10; ++$i){
            for($j=1; $j < 12; ++$j){
                $user = new User();
                $user -> name       = 'user'.(12*$i+$j);
                $user -> email      = 'user'.(12*$i+$j).'@example.com';
                $user -> password   = bcrypt('user');
                $user -> save();
                $user -> roles() -> attach(2);
            }

            // creating admins
            $user = new User();
            $user -> name       = 'user'.(12*$i+12);
            $user -> email      = 'user'.(12*$i+12).'@example.com';
            $user -> password   = bcrypt('user');
            $user -> save();
            $user -> roles() -> attach(1);
        }
    }
}
