<?php

use Illuminate\Database\Seeder;
use uWatch\Room;

class RoomsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $room = new Room();
        $room -> name = 'Kitchen';
        $room -> save();

        $room = new Room();
        $room -> name='Bathroom';
        $room -> save();

        $room = new Room();
        $room -> name='Livingroom';
        $room -> save();

        $room = new Room();
        $room -> name='Bedroom';
        $room -> save();
    }
}
