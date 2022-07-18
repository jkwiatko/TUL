<?php

use Illuminate\Database\Seeder;
use uWatch\Meter;
use uWatch\Reading;

class ReadingsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $meters = Meter::all();
        $faker = Faker\Factory::create("pl_PL");

        foreach($meters as $meter){
            $value = 0;
            $date = '2015-03-14';
            for($i= 0; $i<10; ++$i ){
                $reading = new Reading();
                $date = $faker -> dateTimeBetween($date,'-1 year');
                $value += rand(1,500);
                $reading -> value = $value;
                $reading -> date = $date;
                $reading -> meter() -> associate($meter);
                $reading -> save();
            }
        }
    }
}
