# Tic Tac Toe

A command line Tic Tac Toe game in Clojure.

## Starting the game

```
lein run
```
The default game is 3x3, medium difficulty, and you go first.

## Options

### Size
3x3 to 7x7

```
lein run -s 4
```

### Difficulties
- 1, Easy: The AI choice is random
- 2, Medium: The AI plays strategically, but can be beaten
- 3, Hard: The AI cannot be beaten

```
lein run -d 3
```

### Order

To go second:
```
lein run -o 2
```

### Help
```
lein run -h
```
