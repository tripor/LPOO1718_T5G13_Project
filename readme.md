# Ground Up

### Author
João Augusto dos Santos Lima - up201605314@fe.up.pt  
Ka Chon Ho - up201711244@fe.up.pt

### Concept
Let the players deal with the supply and demand between mines, factories, villagers.

### JavaDoc
https://github.com/tripor/LPOO1718_T5G13_Project/tree/finalRelease/doc

### UML Diagram
![UML Diagram](https://github.com/tripor/LPOO1718_T5G13_Project/blob/finalRelease/final/1.PNG)
![UML Diagram](https://github.com/tripor/LPOO1718_T5G13_Project/blob/finalRelease/final/2.PNG)
![UML Diagram](https://github.com/tripor/LPOO1718_T5G13_Project/blob/finalRelease/final/3.PNG)

### Design Patterns
 - `Factory Method` : The Entity class and Place class
 - `Object Pool` : GameStage make 400 materials and 400 persons that can be used. After use they are put in a array for later use
 - `Singleton` : Use in Map and GameStage
 - `Composite` : Entity is the top of the tree
 - `Decorator` : Place is a decorator and factory/house/mine/smelter reimplement diferent ways the place works 
 - `Facade` : Entity represents all things that apear in the map
 - `Command` : Mouse, UserControl and Icons catch the user requests and clicks
 - `Template method` : Entity handler is redefined in the lower classes.They implement a algoritm that is diferent for from each other
 
 ### PIT 
 ![PIT](https://github.com/tripor/LPOO1718_T5G13_Project/blob/finalRelease/final/pit.PNG)
 
 
 ### Eclema
 ![Eclema](https://github.com/tripor/LPOO1718_T5G13_Project/blob/finalRelease/final/eclema.PNG)
 
 ### Manual
 After pressing the play button, we are presented with a map.
 
 -To move in the map we use the W,A,S,D keys.
 
 -To build building just press the build button. While overring the mouse on top there will be a description of what the building does and it's cost. To cancel just press cancel. 
 
 -To remove buildings just press the remove button and then on the building in the map. Price not refunded. To cancel press ESC.
 
 -Enter the menu by pressing menu:
 
  -Resume : Resume the game
  
  -Save : enter a name and press save and it will save the game to a server
  

  -Load : eneter a name and it will load the game from the server
  
  -Exit : exits the game

 ### Other
 Save and load features are working in the game and other algoritms.
 
 Implementing the save/load , the path of the villagers and the movement of the inserter were the hardest things to implement.
 Time spent:
 
 João Lima: 120 hours
 
 Ka Chon Ho: 15 hours
 
 Implementation of the path of the persons was done by Ka Chon Ho.
 
 Other things was done by João Lima.
 
 Lerson learnd: use design patterns more often and organize code has you do it
