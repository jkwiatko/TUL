<?php

use Illuminate\Database\Seeder;
use uWatch\Meter;
use uWatch\MeterType;
use uWatch\Apartment;
use uWatch\Room;

class MetersTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $apartments     = Apartment::all();
        $rooms          = Room::all();
        $types          = MeterType::all();
        $uniqueSN       = 0; 

        foreach ($apartments as $apartment) {
            ++$uniqueSN;
            for($i=0; $i<7; ++$i){
                if($i<1) {
                    $meter = new Meter();
                    $meter -> number = 2000 + (++$uniqueSN);
                    $meter -> room()        -> associate($rooms[0]); // kitchenID
                    $meter -> metertype()   -> associate($types[1]); // heatID
                    $meter -> apartment()   -> associate($apartment);
                    $meter -> save();
                }

                else if($i<3) {
                    $meter = new Meter();
                    $meter -> number = 1000 + (++$uniqueSN);
                    $meter -> room()        -> associate($rooms[$i-1]); //bathroomID and kitchenID
                    $meter -> metertype()   -> associate($types[0]); //waterID
                    $meter -> apartment()   -> associate($apartment);
                    $meter -> save();
                }

                else{
                    $meter = new Meter();
                    $meter -> number = 3000 + (++$uniqueSN);
                    $meter -> room()        -> associate($rooms[$i-3]); //bathroomID and kitchenID
                    $meter -> metertype()   -> associate($types[2]); //electricityID
                    $meter -> apartment()   -> associate($apartment);
                    $meter -> save();
                }
            }
        }

    }
}
