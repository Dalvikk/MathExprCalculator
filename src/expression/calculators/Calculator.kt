package expression.calculators

import expression.operations.AbstractNAryOperation

interface Calculator<T> {
    fun add(x: T, y: T, wrapper: AbstractNAryOperation): T
    fun sub(x: T, y: T, wrapper: AbstractNAryOperation): T
    fun mul(x: T, y: T, wrapper: AbstractNAryOperation): T
    fun div(x: T, y: T, wrapper: AbstractNAryOperation): T
    fun mod(x: T, y: T, wrapper: AbstractNAryOperation): T
    fun neg(x: T, wrapper: AbstractNAryOperation): T
    fun parse(s: String, wrapper: AbstractNAryOperation): T
    fun abs(x: T, wrapper: AbstractNAryOperation): T
    fun square(x: T, wrapper: AbstractNAryOperation): T
}
