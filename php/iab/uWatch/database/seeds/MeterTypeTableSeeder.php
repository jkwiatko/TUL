<?php

use Illuminate\Database\Seeder;
use uWatch\MeterType;

class MeterTypeTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $meterType = new MeterType();
        $meterType -> name = 'water';
        $meterType -> save();

        $meterType = new MeterType();
        $meterType -> name = 'heat';
        $meterType -> save();

        $meterType = new MeterType();
        $meterType -> name = 'electricity';
        $meterType -> save();
    }
}
