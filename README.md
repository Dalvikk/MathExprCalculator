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
  
See the [instruction](#modding) below

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

### Calculate exceptions

All calculate exceptions extend ArithmeticException


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
> java -jar math.jar
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

## Modding
<details>
  <summary>Operation priority changing</summary>

Suppose you are not satisfied that `mod` priority is higher than `mul`.   

```
> java -jar math.jar -debug
10 * 14 mod 5
DEBUG: (10) * ((14) mod (5))
Enter mode:
i
40
```
but you want `0`. Let's change it.   
Go to `src/expression/operations/Operation.kt`   
Find the `operationsByPriority` list:
```kotlin
val operationsByPriority = listOf(
  listOf(ADD, SUB),
  listOf(MUL, DIV),
  listOf(MOD),
  listOf(NEGATE, SQUARE, ABS, CONST, VAR, LB, RB)
)
```
Now the priority of `mod` is equal to its index. Let's change the priority from 3 to 2.

```kotlin
val operationsByPriority = listOf(
  listOf(ADD, SUB),
  listOf(MUL, DIV, MOD),
  listOf(NEGATE, SQUARE, ABS, CONST, VAR, LB, RB)
)
```
Now mod has the same priority as `mul` and `div`. Operations with a higher priority are shifted one value lower accordingly.  
**Warning!** Unary and 0-ary operations must have maximum priority or things won't work as you expect


That's all. Let's compile and check this:
```
> java -jar math.jar -debug
10 * 14 mod 5
DEBUG: ((10) * (14)) mod (5)
Enter mode: 
i
0
```
</details>

<details>
  <summary>New calculating mode adding</summary>

  Suppose that you want a new calculation mode.  
  For example, let's roll back to [this](https://github.com/Dalvikk/MathExprCalculator/tree/4f43c8a44519124313e689d02714e96d58343874) state when BigDecimal wasn't supported and add it.
  
  You can see the diff in [BigDecimal added](https://github.com/Dalvikk/MathExprCalculator/commit/4e0fdf92caebb2d9d90361487d922347ee3cbb22) commit and make sure it's easy.
  
  Go to `src/expression/calculators/`  
  Create calculator what you want, in our case `BigDecimalCalculator`, and inherit it from `Calculate<YOUR_TYPE>`

```kotlin
package expression.calculators

import java.math.BigDecimal

class BigDecimalCalculator : Calculator<BigDecimal> 
```

Implement all methods (if you don't want or cannot support some operation in your calculator, throw `NotImplementedError`):

```kotlin
override fun add(x: BigDecimal, y: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
    return x.add(y)
}

override fun sub(x: BigDecimal, y: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
    return x.subtract(y)
}

override fun mul(x: BigDecimal, y: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
    return x.multiply(y)
}

override fun not_implemented(x: BigDecimal, y: BigDecimal, wrapper: AbstractNAryOperation): BigDecimal {
    throw NotImplementedError("This method isn't implemented for BigDecimal")
    // or TODO("This method isn't implemented for BigDecimal")
}

// ...
```

Go to `src/expression/Main.kt`  
Find `CALCULATOR_BY_MODE` map and insert your calculator to match the mode:  
```kotlin
val CALCULATOR_BY_MODE: Map<String, Calculator<*>> = mapOf(
    Pair("bi", BigIntCalculator()),
    Pair("i", CheckedIntCalculator()),
    Pair("ci", IntCalculator()),
    Pair("d", DoubleCalculator()),
    Pair("bd", BigDecimalCalculator()), // +
    Pair("p", ModCalculator())
)
````

Add small improvements:
```kotlin
var res = result.evaluate(map, calculator)
if (res is BigDecimal) {
    res = res.setScale(50, RoundingMode.HALF_EVEN)
}
println(res)
```

That's all.

</details>

<details>
  <summary>New infix binary operation adding [TODO]</summary>
</details>

<details>
  <summary>New prefix unary operation adding [TODO]</summary>
</details>


## TODO list
* Add BigDecimal support
* Allow variables to contain numbers, but starts with a letter `a1, an, x2`
* Add VarConst support (`pi = 3,1415926535` by default, `e = 2.71828182846`, etc)
* Add Double support to the parser (`0.01, 0.0e-2, 1e+2, Infinity, -Infinity, NaN`)
* More operations
* Add postfix unary operations (`!`, `!!`)
* Add assigment operation and interpreter mode  
  `> S1 = pi * R * l`  
  `> S2 = pi * R^2  + pi * R * l`   
  `> l = 5`  
  `> S1 + S2`  
  `Enter mode:`  
  `> d`   
  `Enter R: `  
  `> 2`  
  `result = 75.36`  
  `> ...`
  