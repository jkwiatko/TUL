<?php

namespace uWatch\Http\Controllers;

use Illuminate\Http\Request;
use Auth;
use uWatch\Property;
use uWatch\User;
use uWatch\Role;
use uWatch\Apartment;

class DataController extends Controller
{
    
    public function __construct()
    {
        $this->middleware('auth');
    }
    
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $user = Auth::user();
        $roles = $user -> roles() -> get();
        foreach($roles as $role){
            if($role -> name === "Admin"){
                $properties = $user -> properties() -> get();
                $data = array(
                    'properties' => $properties,
                    'controller' => "data"
                );
                return view('pages.properties') -> with($data);
            }
        }

        //dla rezydentow
        $apartments = $user -> apartments() -> get();
        $property = $apartments -> first() -> property() -> first();
        $data = array(
            'apartments'=>$apartments,
            'property'=>$property,
            'controller'=>"Resident"
        );
        return view('pages.apartments')-> with($data);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $property = Property::find($id);
        $apartments = $property -> apartments() -> get ();
        $data = array(
            'apartments' => $apartments,
            'property' => $property,
            'controller' => "Admin"
        );
        return view('pages.apartments') -> with($data);
    }

    public function showMore($property_id, $apartment_id){
        $property = Property::find($property_id);
        $apartment = Apartment::find($apartment_id);
        $meters = $apartment -> meters()->orderBy('number')->get();

        $data = array(
            'property' => $property,
            'apartment' => $apartment,
            'meters'    => $meters
        );
        return view('pages.condo') -> with($data);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
