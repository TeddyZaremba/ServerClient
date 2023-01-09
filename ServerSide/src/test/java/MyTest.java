import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.function.Consumer;


class MyTest {
	//
	// MorraInfo Tests	
	//
	public MorraInfo m1;

	@Test
	void testMI1() {
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		assertTrue(m1.p1Entered);

		m1.addGuess(3, 5, 2);
		assertTrue(m1.p2Entered);

		assertEquals(1, m1.checkGuess());

		assertFalse(m1.p1Entered);
		assertFalse(m1.p2Entered);
	}

	@Test
	void testMI2() {
		m1 = new MorraInfo();
		m1.addGuess(3, 3, 1);
		assertTrue(m1.p1Entered);

		m1.addGuess(3, 3, 2);
		assertTrue(m1.p2Entered);

		assertEquals(0, m1.checkGuess());

		assertFalse(m1.p1Entered);
		assertFalse(m1.p2Entered);
	}

	@Test
	void testMI3() {
		m1 = new MorraInfo();
		m1.addGuess(5, 10, 1);
		assertTrue(m1.p1Entered);

		m1.addGuess(0, 5, 2);
		assertTrue(m1.p2Entered);

		assertEquals(2, m1.checkGuess());

		assertFalse(m1.p1Entered);
		assertFalse(m1.p2Entered);
	}

	@Test
	void testMI4() {
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		assertTrue(m1.p1Entered);

		m1.addGuess(3, 5, 2);
		assertTrue(m1.p2Entered);

		assertEquals(1, m1.checkGuess());

		assertFalse(m1.p1Entered);
		assertFalse(m1.p2Entered);
	}

	@Test
	void testMI5() {
		m1 = new MorraInfo();
		m1.addGuess(3, 7, 1);
		assertTrue(m1.p1Entered);

		m1.addGuess(4, 7, 2);
		assertTrue(m1.p2Entered);

		assertEquals(0, m1.checkGuess());

		assertFalse(m1.p1Entered);
		assertFalse(m1.p2Entered);
	}

	@Test
	void testMI6() {
		m1 = new MorraInfo();
		assertEquals(0, m1.p1Points);
		assertEquals(0, m1.p2Points);
		assertTrue(m1.have2Players);
		assertFalse(m1.p1Entered);
		assertFalse(m1.p2Entered);
		assertFalse(m1.playAgain);
		assertEquals(1, m1.round);
	}

	@Test
	void testMI7() {
		m1 = new MorraInfo();
		m1.addGuess(0, 0, 1);
		m1.addGuess(0, 0, 2);
		m1.checkGuess();
		assertEquals(2, m1.round);
	}

	@Test
	void testMI8() {
		m1 = new MorraInfo();
		m1.p1Index = 2;
		m1.p2Index = 1;
		assertEquals(2, m1.p1Index);
		assertEquals(1, m1.p2Index);
	}

	@Test
	void testMI9() {
		m1 = new MorraInfo();
		m1.addGuess(0, 0, 1);
		m1.addGuess(0, 0, 2);
		m1.checkGuess();
		assertEquals(0, m1.total);
	}

	@Test
	void testMI10() {
		m1 = new MorraInfo();
		m1.have2Players = true;
		assertTrue(m1.have2Players);
	}

	//
	// Server Tests	
	//
	public ArrayList<String> listview = new ArrayList<String>();
	public String state;
	public String numClientsBox;
	public String play1;
	public String score1;
	public String winner1;
	public String playAgain1;
	public String play2;
	public String score2;
	public String winner2;
	public String playAgain2;

	public Server s1; 
	public MorraInfo m2;
	public MorraInfo m3;
	public MorraInfo m4;
	public MorraInfo m5;
	public MorraInfo m6;

	public Consumer<Serializable> callback = data->{listview.add(data.toString());}; // listview1
	public Consumer<Serializable> callback1 = data->{state = data.toString();}; // state
	public Consumer<Serializable> callback2 = data->{numClientsBox = data.toString();}; // numClientsBox
	public Consumer<Serializable> callback3 = data->{play1= data.toString();}; // play1
	public Consumer<Serializable> callback4 = data->{score1 = data.toString();}; // score1
	public Consumer<Serializable> callback5 = data->{winner1 = data.toString();}; // winner1
	public Consumer<Serializable> callback6 = data->{playAgain1 = data.toString();}; // playAgain1
	public Consumer<Serializable> callback7 = data->{play2 = data.toString();}; // play2
	public Consumer<Serializable> callback8 = data->{score2 = data.toString();}; // score2
	public Consumer<Serializable> callback9 = data->{winner2 = data.toString();};// winner2
	public Consumer<Serializable> callback10 = data->{playAgain2 = data.toString();}; // playAgain2
	public Consumer<Serializable> callback11 = data->{}; // reset game

	@Test
	void testServer1() {
		s1 = new Server(5555,
		callback,
		callback1,
		callback2,
		callback3,
		callback4,
		callback5,
		callback6,
		callback7,
		callback8,
		callback9,
		callback10,
		callback11);
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(3, 5, 2);
		m1.checkGuess();
		s1.gameInfo = m1;
		s1.finishGame();
		assertEquals("Won Game", winner1);
		assertEquals("Lost Game", winner2);
	}

	@Test
	void testServer2() {
		s1 = new Server(5555,
		callback,
		callback1,
		callback2,
		callback3,
		callback4,
		callback5,
		callback6,
		callback7,
		callback8,
		callback9,
		callback10,
		callback11);
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(3, 5, 2);
		m1.checkGuess();
		s1.gameInfo = m1;
		s1.finishGame();
		assertEquals("Won Game", winner1);
		assertEquals("Lost Game", winner2);
	}

	@Test
	void testServer3() {
		s1 = new Server(5555,
		callback,
		callback1,
		callback2,
		callback3,
		callback4,
		callback5,
		callback6,
		callback7,
		callback8,
		callback9,
		callback10,
		callback11);
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(3, 5, 2);
		m1.checkGuess();
		s1.gameInfo = m1;
		s1.finishGame();
		assertEquals("Won Game", winner1);
		assertEquals("Lost Game", winner2);
	}

	@Test
	void testServer4() {
		s1 = new Server(5555,
		callback,
		callback1,
		callback2,
		callback3,
		callback4,
		callback5,
		callback6,
		callback7,
		callback8,
		callback9,
		callback10,
		callback11);
		m1 = new MorraInfo();
		m1.addGuess(5, 10, 1);
		m1.addGuess(5, 5, 2);
		m1.checkGuess();
		s1.gameInfo = m1;
		s1.finishGame();
		assertEquals("Won Game", winner1);
		assertEquals("Lost Game", winner2);
	}

	@Test
	void testServer5() {
		s1 = new Server(5555,
		callback,
		callback1,
		callback2,
		callback3,
		callback4,
		callback5,
		callback6,
		callback7,
		callback8,
		callback9,
		callback10,
		callback11);
		m1 = new MorraInfo();
		m1.addGuess(4, 2, 1);
		m1.addGuess(3, 7, 2);
		m1.checkGuess();

		m1.addGuess(4, 2, 1);
		m1.addGuess(3, 7, 2);
		m1.checkGuess();
		s1.gameInfo = m1;
		s1.finishGame();
		assertEquals("Lost Game", winner1);
		assertEquals("Won Game", winner2);
	}

	@Test
	void testServer6() {
		s1 = new Server(5555,
		callback,
		callback1,
		callback2,
		callback3,
		callback4,
		callback5,
		callback6,
		callback7,
		callback8,
		callback9,
		callback10,
		callback11);
		m1 = new MorraInfo();
		m1.addGuess(3, 4, 1);
		m1.addGuess(3, 2, 2);
		s1.gameInfo = m1;
		s1.updateRound();
		assertEquals("Tie", winner1);
		assertEquals("Tie", winner2);
	}

	@Test
	void testServer7() {
		s1 = new Server(5555,
		callback,
		callback1,
		callback2,
		callback3,
		callback4,
		callback5,
		callback6,
		callback7,
		callback8,
		callback9,
		callback10,
		callback11);
		m1 = new MorraInfo();
		m1.addGuess(3, 4, 1);
		m1.addGuess(3, 2, 2);
		s1.gameInfo = m1;
		s1.gameInfo.addGuess(3, 4, 1);
		s1.gameInfo.addGuess(3, 2, 2);
		s1.updateRound();
		assertEquals("Tie", winner1);
		assertEquals("Tie", winner2);
	}

	@Test
	void testServer8() {
		s1 = new Server(5555,
		callback,
		callback1,
		callback2,
		callback3,
		callback4,
		callback5,
		callback6,
		callback7,
		callback8,
		callback9,
		callback10,
		callback11);
		m1 = new MorraInfo();
		m1.addGuess(3, 4, 1);
		m1.addGuess(3, 2, 2);
		s1.gameInfo = m1;
		s1.gameInfo.addGuess(3, 6, 1);
		s1.gameInfo.addGuess(3, 2, 2);
		s1.updateRound();
		assertEquals("Won round", winner1);
		assertEquals("Lost round", winner2);
	}

	@Test
	void testServer9() {
		s1 = new Server(5555,
		callback,
		callback1,
		callback2,
		callback3,
		callback4,
		callback5,
		callback6,
		callback7,
		callback8,
		callback9,
		callback10,
		callback11);
		m1 = new MorraInfo();
		m1.addGuess(3, 4, 1);
		m1.addGuess(3, 2, 2);
		s1.gameInfo = m1;
		s1.gameInfo.addGuess(3, 5, 1);
		s1.gameInfo.addGuess(3, 6, 2);
		s1.updateRound();
		assertEquals("Lost round", winner1);
		assertEquals("Won round", winner2);
	}

	@Test
	void testServer10() {
		s1 = new Server(5555,
		callback,
		callback1,
		callback2,
		callback3,
		callback4,
		callback5,
		callback6,
		callback7,
		callback8,
		callback9,
		callback10,
		callback11);
		assertEquals(5555, s1.portNumber);
		assertEquals(0, s1.count);
	}
}
