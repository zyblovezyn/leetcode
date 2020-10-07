package javalearn.unittest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.Assert.*;

@DisplayName("ConfigTestTest测试")
public class ConfigTestTest {

    ConfigTest configTest;

    @BeforeEach
    public void setUp() throws Exception {
        configTest=new ConfigTest();
    }

    @AfterEach
    public void tearDown() throws Exception {
        configTest = null;
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void getConfigFileWindows() {
        assertEquals("C:\\test.ini", configTest.getConfigFile("test.ini"));
    }

    @Test
    @EnabledOnOs({OS.LINUX,OS.MAC})
    public void getConfigFileLinux() {
        assertEquals("/usr/local/test.cfg", configTest.getConfigFile("test.cfg"));
    }
}