package expression.calculators

import expression.operations.AbstractBinaryOperation
import expression.operations.AbstractUnaryOperation
import expression.operations.Const

interface Calculator<T> {
    fun add(x: T, y: T, wrapper: AbstractBinaryOperation): T
    fun sub(x: T, y: T, wrapper: AbstractBinaryOperation): T
    fun mul(x: T, y: T, wrapper: AbstractBinaryOperation): T
    fun div(x: T, y: T, wrapper: AbstractBinaryOperation): T
    fun mod(x: T, y: T, wrapper: AbstractBinaryOperation): T
    fun neg(x: T, wrapper: AbstractUnaryOperation): T
    fun parse(s: String, wrapper: Const): T
    fun abs(x: T, wrapper: AbstractUnaryOperation): T
    fun square(x: T, wrapper: AbstractUnaryOperation): T
}
