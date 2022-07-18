@extends('layouts.app')

@section('content')
    <div class="jumbotron text-center">
        <h1>Welcome to uWatch</h1>
        <p>This is the application that will tell you how much utilities do you actully use!</p>
        <p><a class="btn btn-primary btn-lg" href="{{ route('login') }}" role="button">Login</a> <a class="btn btn-success btn-lg" href="{{ route('register') }}" role="button">Register</a></p>
    </div>
@endsection