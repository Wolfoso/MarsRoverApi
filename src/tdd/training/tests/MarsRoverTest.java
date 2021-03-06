package tdd.training.tests;

import static org.junit.Assert.*;

import tdd.training.session1.*;
import org.junit.Test;

public class MarsRoverTest {	
	@Test(expected = MarsRoverException.class)
	public void testMarsRoverWrongClassCreation() throws MarsRoverException {
		new MarsRover(50, 50, "(10,)(");
	}
	
	@Test
	public void testMarsRoverCorrectClassCreation() throws MarsRoverException {
		new MarsRover(50, 50, "(10,10)(5,5)");
	}
	
	@Test
	public void testMarsRoverMoveNoObstacle() throws MarsRoverException {
		MarsRover rover = new MarsRover(50, 50, "(10,10)(5,5)");
		String result = rover.executeCommand("fff");
		assertEquals("(0,3,N)", result);
	}
	
	@Test(expected = MarsRoverException.class)
	public void testMarsRoverMoveWrongCommand() throws MarsRoverException {
		MarsRover rover = new MarsRover(50, 50, "");
		rover.executeCommand("fffgfl");
	}
	
	@Test
	public void testMarsRoverMoveFindOneObstacle() throws MarsRoverException {
		MarsRover rover = new MarsRover(50, 50, "(10,10)(5,5)");
		String result = rover.executeCommand("fffffrfffff");
		assertEquals("(4,5,E)(5,5)", result);
	}
	
	@Test
	public void testMarsRoverMoveFindOneObstacleNoMove() throws MarsRoverException {
		MarsRover rover = new MarsRover(50, 50, "(10,10)(5,5)");
		String result = rover.executeCommand("fffffrfffffffff");
		assertEquals("(4,5,E)(5,5)", result);
	}
	
	@Test
	public void testMarsRoverMoveFindTwoObstacles() throws MarsRoverException {
		MarsRover rover = new MarsRover(50, 50, "(10,10)(5,5)");
		String result = rover.executeCommand("fffffrffffflfffffrffffff");
		assertEquals("(9,10,E)(5,5)(10,10)", result);
	}
	
	@Test
	public void testMarsRoverMoveOverGrid() throws MarsRoverException {
		MarsRover rover = new MarsRover(5, 5, "");
		String result = rover.executeCommand("ffffffff"); // 8 steps, 6th step equals back to 0
		assertEquals("(0,2,N)", result);
	}

}
