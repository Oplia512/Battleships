package com.java_academy.gui;

import javafx.scene.layout.Pane;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ViewTests {
	
	private Pane pane;
	private View view;

	@BeforeTest
	public void createTestPane() {
		pane = new Pane();
		view = new View();
	}
	
	@Test
	public void missShotTest() {
		view.drawMiss(pane);
		assertEquals(pane.getStyle(), "-fx-background-color: cornflowerblue");
	}
	
	@Test
	public void hitTest() {
		view.drawShot(pane);
		assertEquals(pane.getStyle(), "-fx-background-color: red");
	}
	
	@Test
	public void drawShipTest() {
		view.drawShips(pane);
		assertEquals(pane.getStyle(), "-fx-background-color: black");
	}

}
