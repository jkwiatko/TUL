<?php

namespace uWatch;

use Illuminate\Database\Eloquent\Model;

class Meter extends Model
{
    protected $fillable = ['number'];
    
    public function apartment(){
        return $this -> belongsTo('uWatch\Apartment');
    }
    
    public function metertype(){
        return $this -> belongsTo('uWatch\MeterType');
    }

    public function room(){
        return $this -> belongsTo('uWatch\Room');
    }
    
    public function readings(){
        return $this -> hasMany('uWatch\Reading');
    }
}
