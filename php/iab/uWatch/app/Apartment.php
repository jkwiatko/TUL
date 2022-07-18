<?php

namespace uWatch;

use Illuminate\Database\Eloquent\Model;

class Apartment extends Model
{
    protected $fillable = ['number'];

    public function user(){
        return $this->belongsTo('uWatch\User');
    }

    public function property(){
        return $this->belongsTo('uWatch\Property');
    }

    public function meters(){
        return $this->hasMany('uWatch\Meter');
    }
}
