# AliensArrivingNaughtily
## Dont Hang Da Man 

### Team Roster:
 ##### Nakib Abedin (assisted by Joker)
 ##### Ameer Alnasser (assisted by TurtleBoi)
 ##### Alif Rahman (assisted by Grippy)

## What is this Project?
For our project, we coded Hangman inside of the terminal. We thought it would be a good way to utilize many different things that we learned about throughout the year. If you don't know how hangman works, here is a brief explanation:

0. There is a phrase being guessed that appears completely in dashes.
1. The person guessing is allowed to either guess the entire phrase or one letter at a time.
2. After every correct letter guess, the user is shown all instances of the guess inside of the word. After every incorrect guess, the limbs of a man being hung appears. If the guesser guesses the entire phrase correctly, they win the game. Otherwise, a limb appears.
3. If the entire body of the man appears, the guesser loses the game. The objective of the game is to guess the entire phrase before the man is hung (hence the name of our project).

#### Topics Covered:
* String Manipulation
* Iteration
* Comparisons between Classes and Primitives
* Object Oriented Programming
* Generating randomness
* Keeping track of time
* ANSI escape codes
#### Important Tools Used:
* Scanner module
* ArrayList module
* `switch()` statements
* `final` keyword
* `protected` keyword
* `currentTimeMillis()`
* `Math.random()`
* `try` and `catch` statments

## How to Launch?
0. Open up a terminal (Use a terminal that supports Unicode for optimal experience)

1. git clone `git@github.com:AmeerA0506/AliensArrivingNaughtily.git`

2. `cd` into the `AliensArrivingNaughtily` directory

3. enter `javac *.java`

4. enter `java Woo`

5. It should be self-explanatory from there on. Let the instructions inside of the game guide you!

6. On the off chance you encounter an error saying `Exception in thread "main"java.lang.IllegalAccessError`, try repeating steps 3-5 once or twice and it should go away. 

## How does it work?
0. There are three sets of difficulties and a customizeable option. As the difficulty increases, the phrase being guessed will generally get longer. For the customizeable option, you can enter your own inputs. The phrase being guessed will be randomly selected from one of the inputs.
1. You are only allowed to either guess one character at a time or the entire phrase itself. If you input more than one character while trying to guess a single character, we will just use the first character in your input.
2. You are not allowed to guess one character more than once. If you try to do so, we will make you guess again.
3. After three incorrect guesses, you will receive a hint giving you the theme that the phrase falls under. After six incorrect guesses, the game will end.
4. You will see the amount of time you spent guessing at the end of the program.
