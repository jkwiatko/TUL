<?php

namespace uWatch;

use Illuminate\Database\Eloquent\Model;

class Reading extends Model
{
    protected $fillable = ['value', 'date'];

    public function meter(){
        return $this -> belongsTo('uWatch\Meter');
    }
}
