@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="alert alert-success">
                Query excution time:  {{$time}}s
        </div>
        <header class="homeHeader">
            <h2>{{$property->street}} {{$property->number}} {{$property->city}}</h2>
        </header>
        <div class="homePostNavbar">
            <div>
            <a href="create" class="btn btn-success btn-lg" >add</a>
            </div>
            <div class="searchPanel">
                    {!! Form::open(['action'=>array('HomeController@search', $property->id) , 'method' => 'GET']) !!}
                    {!! Form::text('query','',['class' => 'form-inline','placeholder' => 'Search...'])!!}
                    {!! Form::checkbox('full_text', 1, true)!!} <span> full-text search </span>
                    {!! Form::close() !!}
            </div>
            <div class="clr"></div>   
        </div>
        
       
        @if(count($posts) > 0)
            <ul class="list">
            @foreach($posts as $post)
                <li class="well">
                    <h3><a href="edit/{{$post->id}}">{{$post->title}}</a></h3>
                    <small>{{$post->content}}</small>
                </li>
            @endforeach
            </ul>
            <div class="text-center">
            </div>
        @else 
            <p> No posts found </p>
        @endif
    </div>
@endSection