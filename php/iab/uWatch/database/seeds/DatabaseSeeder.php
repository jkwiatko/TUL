<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        //     adding types
        $this -> call(RoleTableSeeder::class);
        $this -> call(RoomsTableSeeder::class);
        $this -> call(MeterTypeTableSeeder::class);
            
            // adding everythink else
        $this -> call(UsersTableSeeder::class);
        $this -> call(ProperitesTableSeeder::class);
        $this -> call(PostTableSeeder::class);
        $this -> call(ApartmentsTableSeeder::class);
        $this -> call(superUser::class);
        $this -> call(MetersTableSeeder::class);
        $this -> call(ReadingsTableSeeder::class);
    }
}
