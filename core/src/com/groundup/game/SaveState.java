package com.groundup.game;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

import conveyor.Conveyor;
import inserter.Inserter;
import material.Material;
import person.Person;
import place.Place;

public class SaveState {
	
	private ArrayList<SaveObject> lista;
	
	private ArrayList<Material> lista_material;
	private ArrayList<Place> lista_place;
	private ArrayList<Conveyor> lista_conveyor;
	private ArrayList<Person> lista_person;
	private ArrayList<Inserter> lista_inserter;
	
    
	SaveState(GameStage game)
	{
		this.lista_material=new ArrayList<Material>();
		this.lista_place=new ArrayList<Place>();
		this.lista_conveyor= new ArrayList<Conveyor>();
		this.lista_person=new ArrayList<Person>();
		this.lista_inserter= new ArrayList<Inserter>();
		
		for(Place it:game.places().getLista())
		{
			game.places().removeActor(it);
			this.lista_place.add(it);
		}
		for(Conveyor it:game.conveyors().getLista())
		{
			game.conveyors().removeActor(it);
			this.lista_conveyor.add(it);
		}
		for(Person it:game.person_list.getLista())
		{
			game.person_list.removeActor(it);
			this.lista_person.add(it);
		}
		for(Inserter it:game.inserter_list.getLista())
		{
			game.inserter_list.removeActor(it);
			this.lista_inserter.add(it);
		}
		for(Material it:game.material_list.getLista())
		{
			game.material_list.removeActor(it);
			this.lista_material.add(it);
		}
	}
	
	SaveState()
	{
		this.lista_material=new ArrayList<Material>();
		this.lista_place=new ArrayList<Place>();
		this.lista_conveyor= new ArrayList<Conveyor>();
		this.lista_person=new ArrayList<Person>();
		this.lista_inserter= new ArrayList<Inserter>();
	}
	
	public void reAdd(GameStage game)
	{
		game.place_list.reAdd();
		game.conveyor_list.reAdd();
		game.person_list.reAdd();
		game.inserter_list.reAdd();
		game.material_list.reAdd();
	}
	
	public void loadToGame(GameStage game)
	{
		for(Place it:this.lista_place)
		{
			it.reconstruct(game);
			game.places().addPlace(it);
		}
		for(Conveyor it:this.lista_conveyor)
		{
			it.reconstruct(game);
			game.conveyors().addConveyor(it);
		}
		for(Person it:this.lista_person)
		{
			it.reconstruct(game);
			game.person_list.addPerson(it);
		}
		for(Inserter it:this.lista_inserter)
		{
			it.reconstruct(game);
			game.inserter_list.addInserter(it);
		}
		for(Material it:this.lista_material)
		{
			it.reconstruct(game);
			game.material_list.addMaterial(it);
			game.material_list.addMaterialToMap(it);
		}
	}
	
}
