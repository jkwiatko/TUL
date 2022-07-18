@extends('layouts.app')

@section('content')
    <div class="container">
        <header class="homeHeader">
            <h2>{{$property->street}} {{$property->number}} {{$property->city}}</h2>
        </header>  
        <div class="well">
            <h3><a href="edit/{{$post->id}}">{{$post->title}}</a></h3>
            <small>{{$post->content}}</small>
        </div>
        <br>
        <br>
        <div class="well">
            <legend> Create comment </legend>
            {!!Form::open(['action'=>array('HomeController@upadte', $post->id), 'method' = 'POST']) !!}
            {!!Form::text('postUpdate','',['class' => 'comment','placeholder' => 'Comment...'])!!}
        </div>
    </div>
@endSection