package com.groundup.game;

public class SaveObject {
	
	public String type;
	public int posX;
	public int posY;
	public int width;
	public int height;
	public int direction;
	public int doorAtBorder;
	public int doorAtPx;
	
	SaveObject(String type,int posX,int posY,int width,int height,int direction,int doorAtBorder,int doorAtPx)
	{
		this.type=type;
		this.posX=posX;
		this.posY=posY;
		this.width=width;
		this.height=height;
		this.direction=direction;
		this.doorAtBorder=doorAtBorder;
		this.doorAtPx=doorAtPx;
	}

}
