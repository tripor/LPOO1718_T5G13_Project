# Ground Up

### Author
João Augusto dos Santo Lima - up201605314@fe.up.pt  
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
 

