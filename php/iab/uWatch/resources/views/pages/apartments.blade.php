@extends('layouts.app')
@section('content')
    <div class="jumbotron text-center">
        <h2>{{$property->street}} {{$property->number}} {{$property->city}}</h2>
        @if(count($apartments) > 0)
            <ul class="list well">
            @foreach($apartments as $apartment)
                <li>
                    @if($controller === "Admin")
                    <h3><a href="{{$property->id}}/apartment/{{$apartment->id}}"> apartment no.{{$apartment->number}} </a></h3>
                    @endif
                    @if($controller === "Resident")
                    <h3><a href="data/property/{{$property->id}}/apartment/{{$apartment->id}}"> apartment no.{{$apartment->number}} </a></h3>
                    @endif
                </li>
            @endforeach
            </ul>
        @else 
            <p> No apartments found </p>
        @endif
    </div>
@endsection