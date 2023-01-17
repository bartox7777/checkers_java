#Checkers-JAVA

The game is a copy of the classic Checkers game.

Short explenation of rules:
1. Form of normal move `source destination` e.g. `a8 b7`
2. Form of move with take `source taking_pawn1x [dest1 taking_pawn1x dest2 taking_pawn2x ...] destination` e.g. `a8 b7x c6 d5x e4`
3. Queens are promoted automatically
4. Program implements just very basic rules of checkers

It is written in JAVA (without GUI).
The way to compile and run program on Ubuntu 22.04 and openjdk version "19.0.1":
1. There is prepared bash script (`comp.sh`) to compile and run game
2. Give permission to run script: `sudo chmod 777 comp.sh`
3. Run script: `./comp.sh`

You can compile and run game by yourself by write in terminal:
`javac Game.java`
`java Game`
