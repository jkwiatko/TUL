@extends('layouts.app')
@section('content')
    <div class="jumbotron text-center">
        <h2> Sn.{{$meter->number}} {{$meter->room()->first()->name}} {{$meter->metertype()->first()->name}}</h1>
        @if(count($readings) > 0)
            @foreach($readings as $reading)
                <div class="well">
                {{$reading->value}}</br> 
                {{$reading->date}}
                </div>
            @endforeach
        @else 
            <p> No meters not found </p>
        @endif
    </div>
@endsection