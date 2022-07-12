package com.example.calculator.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setup(){
        calculator = new Calculator();
    }

    @Test
    void sumTest() {
        assertEquals(10.0, calculator.sum(2.0, 8.0));
    }

    @Test
    public void numbersNullSumTest() {
        try{
            calculator.sum(2.0, null);
            fail("Missing Exception");
        }catch(Throwable ex){
            assertEquals(NullPointerException.class, ex.getClass());
            assertEquals("Número 1 e número 2 são obrigatórios.", ex.getMessage());
        }
    }

    @Test
    void subTest() {
        assertEquals(10.0, calculator.sub(20.0, 10.0));
    }

    @Test
    void divideTest() {
        assertEquals(5.0, calculator.divide(10.0, 2.0));
    }

    @Test
    public void divisionByZeroTest() {
        try{
            calculator.divide(2.0, 0.0);
            fail("Missing Exception");
        }catch(Throwable ex){
            assertEquals(ArithmeticException.class, ex.getClass());
            assertEquals("Divisão por zero não é permitido.", ex.getMessage());
        }
    }

    @Test
    void factorialTest() {
        assertEquals(120, calculator.factorial(5));
    }

    @Test
    void integerToBinaryTest() {
        assertEquals(1, calculator.integerToBinary(1));
        assertEquals(101, calculator.integerToBinary(5));
        assertEquals(10100, calculator.integerToBinary(20));
    }

    @Test
    void integerToHexadecimalTest() {
        assertEquals("1", calculator.integerToHexadecimal(1));
        assertEquals("AA", calculator.integerToHexadecimal(170));
        assertEquals("3E7", calculator.integerToHexadecimal(999));
    }

    @Test
    void calculeDayBetweenDateTest() {
        LocalDate dateA = LocalDate.of(2020, 3, 15);
        LocalDate dateB = LocalDate.of(2020, 3, 29);

        assertEquals(14, calculator.calculeDayBetweenDate(dateA, dateB));
    }
}