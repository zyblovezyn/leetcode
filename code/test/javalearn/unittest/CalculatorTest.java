package javalearn.unittest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CalculatorTest测试")
class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator=new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Test
    void add() {
        assertEquals(100,this.calculator.add(100));
        assertEquals(150,this.calculator.add(50));
        assertEquals(130,this.calculator.add(-20));
    }

    @Test
    void sub() {
        assertEquals(-100, this.calculator.sub(100));
        assertEquals(-150, this.calculator.sub(50));
        assertEquals(-130, this.calculator.sub(-20));
    }
}