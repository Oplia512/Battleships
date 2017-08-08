package com.java_academy.gui;

import com.java_academy.network.Connector;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.net.URL;
import java.util.ResourceBundle;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Bartlomiej Janik
 * @since  08.08.17.
 */
@Test
public class ControllerTest {

    private Controller controller;
    private Connector connector = mock(Connector.class);
    @BeforeTest
    private void setUp(){
        controller = new Controller();
        controller.setIp("192.168.1.0");
    }

    @DataProvider(name = "stringIntoLetters")
    private Object[][] gettingNumbersFromStringWithLetters(){
        return new Object[][]{
            {"D2",2},
            {"G3D2",32},
            {"45MSD<45",4545}
        };
    }

    @Test(dataProvider = "stringIntoLetters")
    public void testingIfMethodGetsIdOfSource(String source, Integer numbers){
        Integer out = controller.transformationOfSourceIntoInteger(source);
        Assert.assertEquals(out,numbers);
    }

    @Test
    public void testingIfSetsIp(){
        controller.setIp("localhost");
        Assert.assertEquals(controller.getIp(),"localhost");
    }

    @Test
    public void testIfGetIp(){
        Assert.assertEquals(controller.getIp(),"192.168.1.0");
    }

}
