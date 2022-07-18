@extends('layouts.app')

@section('content')
    <div class="container">
        <header>
            <h1> Create Post for {{$property->street}} {{$property->number}} {{$property->city}}</h1>
        </header>
        {!! Form::open(['action'=>array('HomeController@store', $property->id) , 'method' => 'POST']) !!}
        <div class="form-group">

            {!! Form::label('title', 'Title')!!}
            {!! Form::text('title','',['class' => 'form-control', 'placeholder' => 'Title'])!!}
        </div>
        <div>
            {!! Form::label('body', 'Body')!!}
            {!! Form::textarea('body','',['class' => 'form-control', 'placeholder' => 'Body text'])!!}
        </div>
        {!! Form::submit('Submit', ['class' => 'btn btn-primary'])!!}
        {!! Form::close() !!}
    </div>
@endSection