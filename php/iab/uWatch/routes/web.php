<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome'); }) -> name('welcome');

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home'); //for posts
Route::get('/home/property/{id}', 'HomeController@show');
Route::get('/home/property/{id}/create', 'HomeController@create');
Route::post('/home/property/{id}/store','HomeController@store');
Route::get('home/property/{id}/search', 'HomeController@search');

// Route::get('/apartments', 'ApartmentController@index')->name('apartments'); // wypluc mieszkania odpowiedniej property
Route::get('/data', 'DataController@index') -> name('data');
Route::get('/data/property/{property_id}', 'DataController@show');
Route::get('/data/property/{property_id}/apartment/{apartment_id}','DataController@showMore' );
Route::get('/data/property/{property_id}/apartment/{apartment_id}/meter/{meter_id}','ReadingsController@show');


Route::get('/contact', function(){
    return view('pages.contact');}) -> name('contact');




