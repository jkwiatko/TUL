<?php

namespace uWatch;


use Illuminate\Database\Eloquent\Model;

class Property extends Model
{
    protected $fillable = ['city','street','number'];

    public function posts(){
        return $this->hasMany('uWatch\Post');
    }

    public function apartments(){
        return $this->hasMany('uWatch\Apartment');
    }

    public function users(){
        return $this->belongsToMany('uWatch\User', 'user_property');
    }
}