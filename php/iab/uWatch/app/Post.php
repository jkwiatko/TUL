<?php

namespace uWatch;

// use Laravel\Scout\Searchable;
use Illuminate\Database\Eloquent\Model;
use Nicolaslopezj\Searchable\SearchableTrait;

class Post extends Model
{
    use SearchableTrait;

    protected $searchable = [
        'columns' => [
            'title' => 8,
            'content' => 10
        ]
    ];

    protected $fillable = ['title', 'content'];

    public function property(){
        return $this->belongsTo('uWatch\Property');
    }

    public function user(){
        return $this->belongsTo('uWatch\User');
    }
}
