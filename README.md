# PRG-1203
OOPF Group 5 assignment

### Boat Racing Game
You are required to build a simple game ‘Boat Race’ in Java program that fulfil the below
requirements. Analyze and develop the Java program as per described using the Object-Oriented
design. You should design your program for optimum maintainability and reusability with the best
practices of object-oriented techniques you have learnt. You also need to document your design
using the UML class and class relationship diagrams.

### The game rules:
* The game is a two players game. At the beginning of the game, each player will be allocated
with a boat. During the game, the players take turn to throw the dice (you can use the
random function to generate the random dice number) to decide how many steps should
the boat move forward
* The river can be visualised as 100-columns track as below, which is filled with random
  number of traps(#) and currents(C)
* Once the game started, all the traps and currents will be scattered randomly in the river.
  Some currents are stronger than the others, so as the traps. The stronger current or trap
  will make the boat moves more steps forward or backward. When boat hits the trap, the
  boat will need to move backward x number of steps, when the boat hits the current, it will
  move forward x number of steps. The boat should not be allowed to move beyond the
  river’s boundary
* Game will end when either player’s boat reaches the end of the river. Display the location of
  the boats after every move.

When the game starts, display the Top 5 scores and ask the player for the name (short name with
one word). You should count the total turns that each player takes in the games. When the game
ended and the score of the player is within the top 5 scores, store the player’s score and name in the
‘TopScore.txt’ text file. The list should be ordered by score in ascending order.
Tips: You can add any additional attributes to the objects in this game which you see fit


### Technical info
- Java 11 as main language
- This project use Maven as build tool

### Team
- Kambar Mirmanov - head of the project, developer/QA
- Egor Voronianskii(id: 19102631) - developer
- Ray - developer
- Huseina - developer/QA
- Lousim Lim - develop/QA
