@extends('layouts.app')
@section('content')
    <div class="jumbotron text-center">
        <hedaer class="propertiesHeader">
            <h2>Your Properties</h2>
        </hedaer>
        <ul class="list well">
        @if(count($properties) > 0)
            @foreach($properties as $property)
                <li class="listLink">
                    @if($controller === "home")
                    <h3><a href="home/property/{{$property->id}}">{{$property->street}} {{$property->number}} {{$property->city}}</a></h3>
                    @endif
                    @if($controller === 'data')
                    <h3><a href="data/property/{{$property->id}}">{{$property->street}} {{$property->number}} {{$property->city}}</a></h3>
                    @endif
                </li>
            @endforeach
        @else
        </ul>
            <p> No properties found </p>
        @endif
    </div>
@endsection