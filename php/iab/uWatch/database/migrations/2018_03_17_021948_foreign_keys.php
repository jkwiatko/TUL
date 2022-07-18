<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class ForeignKeys extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('readings', function (Blueprint $table){
            $table->foreign('meter_id') -> references('id') -> on('meters') -> onDelete('cascade');
        });
        
        Schema::table('meters', function (Blueprint $table){
            $table->foreign('room_id')      -> references('id') -> on('rooms') ->onDelete('cascade');
            $table->foreign('metertype_id') -> references('id') -> on('meter_types');
            $table->foreign('apartment_id') -> references('id') -> on('apartments');
        });
        
        Schema::table('apartments', function (Blueprint $table){
            $table->foreign('user_id')      -> references('id')->on('users');
            $table->foreign('property_id')  -> references('id')->on('properties') -> onDelete('cascade');
        });
        
        Schema::table('user_roles', function (Blueprint $table){
            $table->foreign('user_id') -> references('id') -> on('users');
            $table->foreign('role_id') -> references('id') -> on('roles');
        });

        Schema::table('posts', function (Blueprint $table){
            $table->foreign('property_id')  -> references('id') -> on('properties');
            $table->foreign('user_id')      -> references('id') -> on('users');
        });
        
        Schema::table('comments', function (Blueprint $table){
            $table->foreign('post_id') -> references('id') -> on('posts');
            $table->foreign('user_id') -> references('id') -> on('users');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('readings', function (Blueprint $table){
            $table -> dropforeign('readings_meter_id_foreign');
        });
        
        Schema::table('meters', function (Blueprint $table){
            $table -> dropforeign('meters_room_id_foreign');
            $table -> dropforeign('meters_metertype_id_foreign');
            $table -> dropforeign('meters_apartment_id_foreign');
        });
        
        Schema::table('apartments', function (Blueprint $table){
            $table -> dropforeign('apartments_user_id_foreign');
            $table -> dropforeign('apartments_property_id_foreign');
        });
        
        Schema::table('user_roles', function (Blueprint $table){
            $table -> dropforeign('user_roles_user_id_foreign');
            $table -> dropforeign('user_roles_role_id_foreign');
        });

        Schema::table('comments', function (Blueprint $table){
            $table -> dropforeign('comments_user_id_foreign');
            $table -> dropforeign('comments_post_id_foreign');
        });

        Schema::table('posts', function (Blueprint $table){
            $table -> dropforeign('posts_property_id_foreign');
            $table -> dropforeign('posts_user_id_foreign');
        });
        
    }
}
