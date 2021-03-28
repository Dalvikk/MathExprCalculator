# Mathematical expressions calculator

Easily modifiable recursively mathematical expressions parser and calculator

## About

Supported:

* Any variables consisting of letters
* Integer values
* Overflow checks during evaluating
* Calculations in various modes
* Any infix binary and prefix unary operators  

You can also easily modify the program:

* Change the priority of operations
* Add any prefix unary and infix binary operations
* Add new calculation mode   
  See the instruction below

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
* All unary and 0-ary operations`NEGATE, SQUARE, ABS` `CONST, VAR, LB, RB`

## Run

1. Install [JDK](https://adoptopenjdk.net/) if for some reason you haven't done it yet
2. Install [Kotlin](https://kotlinlang.org/docs/command-line.html)
3. Clone this repository:

> `git clone https://github.com/Dalvikk/MathExprCalculator`

4. Compile and run:

> `cd MathExprCalculator`  
> `kotlinc src -include-runtime -d math.jar`      
> `java -jar math.jar [-debug]`

`-debug` flag prints an expression with brackets for each operation, which allows you to see how the program parses and
sets priorities


## Examples
```
> java -jar math.jar -debug
first * second mod lol
DEBUG: (first) * ((second) mod (lol))
Enter mode:
i
Enter first value:
5
Enter second value:
11
Enter lol value:
3
10
```

```
> java -jar math.jar
(first + last)/2 * n
Enter mode:
d
Enter first value:
1
Enter last value:
100
Enter n value:
100
5050.0
```

### Input errors

```
> java -jar math.jar
hello + bye
Enter mode:
lol
Incorrect mode: lol. Available mods: [bi, i, ci, d, p]. Please, retry:
p
Enter mod:
mod
mod isn't a Int. Please, retry:
5
Enter hello value:
string
Enter bye value:
decimal
NumberFormatException while parsing: string isn't a Int
===========
hello + bye
^==========
```

### Parsing errors

#### Common parse exceptions

  ``` 
  > java -jar math.jar
  1 + (x *  / 9) + 3
  Const, variable, '(' or unary operation expected:
  ==================
  1 + (x *  / 9) + 3
  ==========^=======
  
  > java -jar math.jar
  x @ * y
  Expected binary operation or nothing
  =======
  x @ * y
  ==^====
  ```

#### MissingLeftBracketException

  ``` 
  > java -jar math.jar
  (x + y     )    )
  Left bracket missing
  =================
  (x + y     )    )
  ================^
  ```

#### MissingRightBracketException

  ``` 
  > java -jar math.jar
  ((1+1)
  Right bracket missing
  ======
  ((1+1)
  ^=====
  ```

### Evaluates exceptions

All evaluates exceptions extends ArithmeticException

#### DivisionByZeroException

```
> java -jar math.jar
x / (abs -5 - 5)
Enter mode:
i
Enter x value:
9999
Division by zero. Left = 9999, right = 0.
================
x / (abs -5 - 5)
==^=============
```

#### ModByZeroException

```
> java -jar math.jar
x mod (abs -5 - 5)
Enter mode:
i
Enter x value:
0
Mod by zero. Left = 0, right = 0.
==================
x mod (abs -5 - 5)
==^===============
```

#### ArithmeticException

```
> java -jar math.jar
x mod y
Enter mode:
bi
Enter x value:
100
Enter y value:
-2
Mod by negative number. BigInteger's mode always returns a non-negative BigInteger.
Maybe you want add 'remainder' operation?
Left = 100, right = -2.
=======
x mod y
==^====

// Arithmetic exception caused by NumberFormatException
> java -jar math.jar
first + second - third
Enter mode:
i
Enter first value:
123
Enter second value:
456
Enter third value:
hello
NumberFormatException while parsing: hello isn't a Int
======================
first + second - third
=================^====

// Constant overflows in type
java -jar math.jar
x + 2
Enter mode:
i
Enter x value:
1000000000000000000
NumberFormatException while parsing: 1000000000000000000 isn't a Int
=====
x + 2
^====
```
