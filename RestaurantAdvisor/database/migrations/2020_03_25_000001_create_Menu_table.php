<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateMenuTable extends Migration
{
    /**
     * Schema table name to migrate
     * @var string
     */
    public $tableName = 'Menu';

    /**
     * Run the migrations.
     * @table Menu
     *
     * @return void
     */
    public function up()
    {
        Schema::create($this->tableName, function (Blueprint $table) {
            $table->engine = 'InnoDB';
            $table->increments('id');
            $table->string('name', 45);
            $table->text('description');
            $table->float('price');
            $table->unsignedInteger('Restaurant_id');

            $table->index(["Restaurant_id"], 'fk_Menu_Restaurant_idx');

            $table->unique(["id"], 'id_UNIQUE');


            $table->foreign('Restaurant_id', 'fk_Menu_Restaurant_idx')
                ->references('id')->on('Restaurant')
                ->onDelete('no action')
                ->onUpdate('no action');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
     public function down()
     {
       Schema::dropIfExists($this->tableName);
     }
}
