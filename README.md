# Tank-Game
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)


## DESCRIPTION
The objective of this game is to destroy the other player i.e., Tank. <br/>
It consists of two tanks each with their own screen and a shared map. <br/>
They are located on two sides of the map and the objective is to destroy the rival tank using arrow keys and WASD keys for Player 1 and 2 respectively.<br/>
The life of each tank is displayed on the tank and there are different power-ups to give special powers to the tank if the player collects it. <br/>
There are breakable and unbreakable walls. <br/>
Power-ups consists of “FULL LIFE”, “2X HITS” and “SUPER (BOTH)”. <br/>
Each player has 3 lives and the game ends if either player dies. The score and life count is displayed on the screen.<br/>
The player with the highest points wins.

## DEVELOPMENT ENVIRONMENT
Version of JAVA Used: Java JDK 1.8.0_191 <br/>
IDE Used: IntelliJ IDEA 2020.2.3

## STEPS TO BUILD AND IMPORT
Use the root folder to import the files directly into IntelliJ.<br/>
In IntelliJ, go to "Project Structure -> Modules", and change the following settings.<br/>
Set the Github repo as the Content Root.<br/>
Set the TankGame/ SuperRainbowReef folder as a Source.<br/>
Build the Tank class and the Game class in first and second game folder respectively. <br/>
The jar file was built by adding Artifacts in the File/Project Structure dialog and declaring the root of the jar file.<br/>
Then the artifact is built using the Build/Build Artifacts and the jar file is created which can be run by double clicking on it.

## STEPS TO RUN THE GAME
Right click on the "Tank" class or “Game” class depending on the game and select Run. <br/>
The game starts immediately once launched.<br/>
The games can also be run by launching the included .jar file via the command line located in the JAR folder or running the jar file in IntelliJ. <br/>
The Tank Game consists of two tanks each with their own screen and a shared map.<br/>
The objective is to destroy the rival tank using ARROW keys and WASD keys to move tank Up or Down and Rotate Left or Right for Player 1 and 2 respectively.<br/>
There are breakable and unbreakable walls.<br/>
Power-ups consists of “FULL LIFE”, “2X HITS” and “SUPER (BOTH)”.<br/>
Each player has 3 lives and the game ends if either player dies.<br/>
The player with the highest points wins.

## CLASS DESCRIPTION OF TANK GAME
* Background.java : Creates and loads the background on the tank game window.
* Bullet.java : Creates a projectile and defines the direction and movement of the bullet.
* Player.java : Used to keep the track of the player’s position on the map when keys are pressed to move the tank.
* PowerUp.java : Used to load and update the powerups in the game and define them.
* Random.java : To load the power up images and to update tanks on collision and to display walls on window.
* Tank.java : This is the main class and is used to load images of tanks, walls, music, collision sound, bullet sounds and all other sprites. Also to define the area of tank movement and the mini map and 2 displays.
* Wall.java : Used to load breakable wall and define it on collision.
* Wall2.java : Used to load unbreakable wall and define it on collision.

## UML DIAGRAM
<img src="uml1.png" alt="UML!" width="100%"/>

## GAMEPLAY
<img src="tank1.gif" alt="TankPlay1!" width="70%"/>
<img src="tank2.gif" alt="TankPlay2!" width="70%"/>
<img src="tank3.gif" alt="TankPlay3!" width="70%"/>

