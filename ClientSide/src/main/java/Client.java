import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Client extends Thread{
	
	Socket socketClient;
	
	ObjectInputStream in;
	ObjectOutputStream out;
	
	public Consumer<Serializable> callback;
	public Consumer<Serializable> updateMe;
	public Consumer<Serializable> updateOp;
	public Consumer<Serializable> gameOver;
	public Consumer<Serializable> newGame;
	public Consumer<Serializable> opGuess;
	public Consumer<Serializable> opPlay;
	public String ip;
	public int port;
	public int pNumber;
	
	Client(   Consumer<Serializable> call
			, Consumer<Serializable> up1
			, Consumer<Serializable> up2
			, Consumer<Serializable> opGuess
			, Consumer<Serializable> opPlay
			, Consumer<Serializable> over
			, Consumer<Serializable> newG
			, String ip
			, int port) {
		this.callback = call;
		this.updateMe = up1;
		this.updateOp = up2;
		this.opGuess = opGuess;
		this.opPlay = opPlay;
		this.gameOver = over;
		this.newGame = newG;
		this.ip= ip;
		this.port = port;
	}
	
	public void updateGame(MorraInfo info) {
		
		if (info.p1Points == 2 || info.p2Points == 2) {
			gameOver.accept(null);
		} else if (info.round == 1) {
			callback.accept("starting new game...");
			newGame.accept(null);
		}
		
		if (pNumber == 1) {
			updateMe.accept(info.p1Points);
			updateOp.accept(info.p2Points);
			opGuess.accept(info.p2Guess);
			opPlay.accept(info.p2Plays);
		} else if (pNumber == 2) {
			updateMe.accept(info.p2Points);
			updateOp.accept(info.p1Points);
			opGuess.accept(info.p1Guess);
			opPlay.accept(info.p1Plays);
		}
		
	}
	

	public void run() {
		try {
			socketClient = new Socket(ip, port);
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
		} catch (Exception e) {}
		
		while (true) {
			try {
				
				Object received = in.readObject();

				if (received instanceof String) {
					callback.accept((String)received);
					
				} else if (received instanceof MorraInfo) {
					updateGame((MorraInfo)received);
					
				} else if (received instanceof Integer) {
					pNumber = (Integer)received;
					
				}
			} catch (Exception e) {}
		}
	}
	
	public void sendInfo(ArrayList<Integer> info) {
		try {
			out.writeObject(info);
		} catch (Exception e) {}
	}
}
