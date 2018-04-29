# Ground Up

### Author
João Augusto dos Santo Lima - up201605314@fe.up.pt  
Ka Chon Ho - up201711244@fe.up.pt

### Concept
Let the players deal with the supply and demand between mines, factories, villagers.

### Structure
- `Place`: All place types.
  - `Mine`: Produces materials for Factories.
    - `IronMine`: Mines only produce iron.
    - `...`
  - `Factory`: Produces products for villagers.
    - `...`: Factory only produce specific product(s).
  - `House`: For villagers' living.
    - `...`: Different kinds of houses.

- `Person`: All human types.
  - `Worker`: The people who work in a factory.

- `Coisa`: Everything else.
  - `FryingPan`
  - `...`: Different materials, products

- `Conveyor`: For moving one material/product to another place.

- `Logic`
  - `Console`: For programming habit (`Console.log("...")`).
  - `Map`: Manage all the information for the game renderer.
  - `Path`: Path finder
    - `byAStar`: A Star algorithm
  - `Storage`
    - ...

### UML Diagram
![UML Diagram (Updated 2018-04-27)](https://github.com/tripor/LPOO1718_T5G13_Project/blob/master/intermed/uml_20180427_2.jpg)
(Simplified Version)

### Behavioural Aspects
![State Diagram](https://github.com/tripor/LPOO1718_T5G13_Project/blob/master/intermed/states.PNG)

### Design Patterns
 - `Factory Method`
 - `Object Pool`
 Used the two design in combination
 - `Composite` : A tree structure of simple and composite objects
 - `Facade` : A single class that represents an entire subsystem
 - `State` : Alter an object's behavior when its state changes
 - `Strategy` : Encapsulates an algorithm inside a class


