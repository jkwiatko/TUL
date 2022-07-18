<?php

use Illuminate\Database\Seeder;
use uWatch\Role;


class RoleTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $role = new Role();
        $role -> name = 'Admin';
        $role -> save();

        $role = new Role();
        $role -> name = 'Resident';
        $role -> save();
    }
}
