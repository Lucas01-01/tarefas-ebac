package com.testes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.Fibonacci.Fibonacci;

public class TestFibonacci {

    @Test
    public void testFibonacciZero() {
        Assertions.assertEquals(0, Fibonacci.encontrarElementoPD(0));
    }

    @Test
    public void testFibonacciOne() {
        Assertions.assertEquals(1, Fibonacci.encontrarElementoPD(1));
    }

    @Test
    public void testFibonacciFive() {
        Assertions.assertEquals(5, Fibonacci.encontrarElementoPD(5));
    }

    @Test
    public void testFibonacciNegativeThrows() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            Fibonacci.encontrarElementoPD(-1);
        });
    }
}
