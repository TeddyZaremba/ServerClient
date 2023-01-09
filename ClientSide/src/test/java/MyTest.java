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
	// Client Tests
	//
	public ArrayList<String> listview = new ArrayList<String>();
	public String myScoreVal;
	public String opScoreVal;
	public String opGuessVal;

	public Client c1;
	public MorraInfo m1;
	public MorraInfo m2;
	public MorraInfo m3;
	public MorraInfo m4;
	public MorraInfo m5;
	public MorraInfo m6;

	public Consumer<Serializable> callback = data->{listview.add(data.toString());}; // listview
	public Consumer<Serializable> callback1 = data->{myScoreVal = data.toString();}; // updateMe - myScoreVal
	public Consumer<Serializable> callback2 = data->{opScoreVal = data.toString();}; // updateOp - opScoreVal
	public Consumer<Serializable> callback3 = data->{opGuessVal = data.toString();}; // opGuess - opGuessVal
	public Consumer<Serializable> void1 = data->{};
	public Consumer<Serializable> void2 = data->{};
	public Consumer<Serializable> void3 = data->{};

	@Test
	void testClient1() {
		c1 = new Client(callback,
			callback1,
			callback2,
			callback3,
			void1,
			void2,
			void3,
			"127.0.0.1",
			5555
		);
		assertEquals(5555, c1.port);
		assertEquals("127.0.0.1", c1.ip);
	}

	@Test
	void testClient2() {
		c1 = new Client(callback,
			callback1,
			callback2,
			callback3,
			void1,
			void2,
			void3,
			"127.0.0.1",
			5555
		);
		c1.pNumber = 1;
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(3, 5, 2);
		m1.checkGuess();
		c1.updateGame(m1);
		assertEquals("1", myScoreVal);
	}

	@Test
	void testClient3() {
		c1 = new Client(callback,
			callback1,
			callback2,
			callback3,
			void1,
			void2,
			void3,
			"127.0.0.1",
			5555
		);
		c1.pNumber = 1;
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(3, 5, 2);
		m1.checkGuess();
		c1.updateGame(m1);
		assertEquals("1", myScoreVal);
	}

	@Test
	void testClient4() {
		c1 = new Client(callback,
			callback1,
			callback2,
			callback3,
			void1,
			void2,
			void3,
			"127.0.0.1",
			5555
		);
		c1.pNumber = 1;
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(3, 5, 2);
		m1.checkGuess();
		m1.checkGuess();
		c1.updateGame(m1);
		assertEquals("2", myScoreVal);
	}

	@Test
	void testClient5() {
		c1 = new Client(callback,
			callback1,
			callback2,
			callback3,
			void1,
			void2,
			void3,
			"127.0.0.1",
			5555
		);
		c1.pNumber = 2;
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(3, 5, 2);
		m1.checkGuess();
		c1.updateGame(m1);
		assertEquals("0", myScoreVal);
	}

	@Test
	void testClient6() {
		c1 = new Client(callback,
			callback1,
			callback2,
			callback3,
			void1,
			void2,
			void3,
			"127.0.0.1",
			5555
		);
		c1.pNumber = 1;
		m1 = new MorraInfo();
		m1.addGuess(3, 7, 1);
		m1.addGuess(3, 5, 2);
		m1.checkGuess();
		c1.updateGame(m1);
		assertEquals("0", myScoreVal);
	}

	@Test
	void testClient7() {
		c1 = new Client(callback,
			callback1,
			callback2,
			callback3,
			void1,
			void2,
			void3,
			"127.0.0.1",
			5555
		);
		c1.pNumber = 1;
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(3, 5, 2);
		m1.checkGuess();
		c1.updateGame(m1);
		assertEquals("5", opGuessVal);
	}

	@Test
	void testClient8() {
		c1 = new Client(callback,
			callback1,
			callback2,
			callback3,
			void1,
			void2,
			void3,
			"127.0.0.1",
			5555
		);
		c1.pNumber = 1;
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(3, 5, 2);
		m1.checkGuess();
		c1.updateGame(m1);
		assertEquals("5", opGuessVal);
	}

	@Test
	void testClient9() {
		c1 = new Client(callback,
			callback1,
			callback2,
			callback3,
			void1,
			void2,
			void3,
			"127.0.0.1",
			5555
		);
		c1.pNumber = 1;
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(1, 2, 2);
		m1.checkGuess();
		c1.updateGame(m1);
		assertEquals("0", opScoreVal);
	}

	@Test
	void testClient10() {
		c1 = new Client(callback,
			callback1,
			callback2,
			callback3,
			void1,
			void2,
			void3,
			"127.0.0.1",
			5555
		);
		c1.pNumber = 1;
		m1 = new MorraInfo();
		m1.addGuess(3, 6, 1);
		m1.addGuess(4, 10, 2);
		m1.checkGuess();
		c1.updateGame(m1);
		assertEquals("0", opScoreVal);
	}
}