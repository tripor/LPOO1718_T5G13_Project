package logic.storage;


import com.badlogic.gdx.scenes.scene2d.Group;

import inserter.Inserter;

public class InserterList extends Group{
	
	public InserterList() {

	}
	
	public void addInserter(Inserter i) {
		
		this.addActor(i);
	}
}
