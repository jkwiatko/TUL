<?php

namespace uWatch\Http\Controllers;


use Illuminate\Http\Request;
use Auth;
use uWatch\User;
use uWatch\Role;
use uWatch\Property;
use uWatch\Post;
use Search;
// use \Http\Controllers\Search;


class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $user = Auth::user();
        $roles = $user -> roles() -> get();

        //for admin users
        foreach($roles as $role){
            if($role -> name === "Admin"){
                $properties = $user -> properties() -> get();
                $data = array(
                    'properties' => $properties,
                    'controller' => "home"
                );
                return view('pages.properties') -> with($data);
            }
        }

        //for other users
        $apartments = $user -> apartments() -> get();
        $property = $apartments -> first() -> property() -> first();
        $posts = $property -> posts() -> orderBy('created_at', 'desc') -> paginate(10);
        // $this->show($property->id);
        $data = array(
            'property' => $property,
            'posts' => $posts
        );
        return view('pages.home') -> with ($data);
    }

    //TODO sprawdzic czy dalo by sie wykorzystac wiecej niz raz
    public function show($id){
        $property = Property::find($id);
        $posts = $property -> posts() -> orderBy('created_at','desc') -> paginate(10);
        $data = array(
            'property' => $property,
            'posts' => $posts
        );
        return view('pages.home') -> with($data);
    }


    public function create($id){
        $property = Property::find($id);
    
        return view('pages.addpost') -> with('property',$property);
    }

    public function store(Request $request, $id){
        $this->validate($request, [
            'title' => 'required',
            'body' => 'required'
        ]);

        $property = Property::find($id);
        $post = new Post();
        $user = Auth::user();

        $post -> title = $request -> input('title');
        $post -> content = $request  -> input('body');
        $post -> property()  -> associate($property);
        $post -> user() -> associate($user);
        $post -> save();
        return redirect('/home/property/'.$id) -> with('success','Post created');
    }

    public function search(Request $request, $id){
        //TODO dac jakies sprawdzanie czy Search nie jest pusty!
        if($request -> input('full_text')){
            $query = request('query');
            $property = Property::find($id);
            $start = microtime(true);
            $posts = Search::search("Post", ['title', 'content'], $query, ['id', 'title', 'content', 'property_id'], ['id', 'desc'], false)
            -> where('property_id', $id) -> get();
            $time = microtime(true) - $start;
        }

        else{
            $query = request('query');
            $property = Property::find($id);
            $start = microtime(true);
            $posts = Post::where('property_id', $property->id)->search($query)->get();
            $time = microtime(true) - $start;
        }
        
        $data = [
            'property' => $property,
            'posts' => $posts,
            'time' => $time
        ];
        return view('pages.search') -> with($data);
    }
}
