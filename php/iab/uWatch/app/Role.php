<?php

namespace uWatch;

use Illuminate\Database\Eloquent\Model;

class Role extends Model
{
    protected $fillable = ['name'];


    public function users(){
        return $this->belongsToMany('uWatch\User', 'user_roles');
    }
}
