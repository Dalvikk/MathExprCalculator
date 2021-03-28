# Mathematical expressions calculator

Easily modifiable recursively mathematical expressions parser and calculator 

## About
Supported:
* Any variables consisting of letters
* Integer values
* Overflow checks during evaluating
* Calculations in various modes
* Binary and unary operators (see the table below)

You can also easily modify the program:
* Change the priority of operations
* Add any unary and binary operations 
* Add new calculation mode   
Instruction

| Mode | Description |
| ---------------|----------------|
| `i` |Calculations in `Int`|
| `ci`  |Calculations in `Int` with overflow detection |
| `bi`  |Calculations in `Big Integer`|
| `d`  |Calculations in `Double`|
| `p`  |Calculations in `group of Int modulo n`|

Operation in ascending priority:
* `ADD, SUB`
* `MUL, DIV`
* `MOD`
*  All unary and 0-ary operations`NEGATE, SQUARE, ABS` `CONST, VAR, LB, RB`

## Run

1. Install [JDK](https://adoptopenjdk.net/) if for some reason you haven't done it yet
2. Install [Kotlin](https://kotlinlang.org/docs/command-line.html)
3. Clone this repository:
> `git clone https://github.com/Dalvikk/MathExprCalculator`
4. Compile and run:
> `cd MathExprCalculator`  
> `kotlinc src -include-runtime -d math.jar`      
> `java -jar math.jar [-debug]`

`-debug` flag prints an expression with brackets for each operation, which allows you to see how the program parses and sets priorities

