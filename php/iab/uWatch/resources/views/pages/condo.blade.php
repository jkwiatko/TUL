@extends('layouts.app')
@section('content')
    <div class="jumbotron text-center">
        <h2>{{$property->street}}  {{$property->number}} m.{{$apartment->number}}  {{$property->city}} </h2>
        @if(count($meters) > 0)
            <ul class="list well">
            @foreach($meters as $meter)
                <li>
                <h3><a href="{{$apartment->id}}/meter/{{$meter->id}}"> Sn.{{$meter->number}} {{$meter->room()->first()->name}} {{$meter->metertype()->first()->name}}</a></h3>
                </li>
            @endforeach
            </ul>
        @else 
            <p> No meters not found </p>
        @endif
    </div>
@endsection