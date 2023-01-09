import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ProcessHandle.Info;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class Server{
	public ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	public int portNumber;
	public String ipAddress;
	public TheServer theServer;

	public Consumer<Serializable> callback; // listview1
	public Consumer<Serializable> callback1; // state
	public Consumer<Serializable> callback2; // numClientsBox
	public Consumer<Serializable> callback3; // play1
	public Consumer<Serializable> callback4; // score1
	public Consumer<Serializable> callback5; // winner1
	public Consumer<Serializable> callback6; // playAgain1
	public Consumer<Serializable> callback7; // play2
	public Consumer<Serializable> callback8; // score2
	public Consumer<Serializable> callback9; // winner2
	public Consumer<Serializable> callback10; // playAgain2
	public Consumer<Serializable> callback11; //reset game

	public MorraInfo gameInfo;
	public int count;

	Server(int portNumber, 
	Consumer<Serializable> call,
	Consumer<Serializable> call1,
	Consumer<Serializable> call2,
	Consumer<Serializable> call3,
	Consumer<Serializable> call4,
	Consumer<Serializable> call5,
	Consumer<Serializable> call6,
	Consumer<Serializable> call7,
	Consumer<Serializable> call8,
	Consumer<Serializable> call9,
	Consumer<Serializable> call10,
	Consumer<Serializable> call11) {
		this.callback = call;
		this.callback1 = call1;
		this.callback2 = call2;
		this.callback3 = call3;
		this.callback4 = call4;
		this.callback5 = call5;
		this.callback6 = call6;
		this.callback7 = call7;
		this.callback8 = call8;
		this.callback9 = call9;
		this.callback10 = call10;
		this.callback11 = call11;

		this.portNumber = portNumber;
		this.count = 0;
		theServer = new TheServer();
		theServer.start();
	}

	//
	// finishGame
	//
	public void finishGame() {
		if (gameInfo.p1Points > gameInfo.p2Points) {
			callback5.accept("Won Game");
			callback9.accept("Lost Game");
		} else {
			callback5.accept("Lost Game");
			callback9.accept("Won Game");
		}
	}


	//
	// Updates who won the game
	//
	public void updateRound() {
		if (gameInfo.p1Entered && gameInfo.p2Entered) {
			int playerWon = gameInfo.checkGuess();
			if (playerWon == 1) {
				callback5.accept("Won round");
				callback9.accept("Lost round");
			} else if (playerWon == 2) {
				callback5.accept("Lost round");
				callback9.accept("Won round");
			} else {
				callback5.accept("Tie");
				callback9.accept("Tie");
			}
			callback1.accept("Round " + gameInfo.round + " in progress");
			callback4.accept(gameInfo.p1Points + " Points");
			callback8.accept(gameInfo.p2Points + " Points");

			if (gameInfo.p1Points == 2 || gameInfo.p2Points == 2) {
				finishGame();
			}
		}
	}	
	
	public class TheServer extends Thread{

		public void run() {
			try(ServerSocket mysocket = new ServerSocket(portNumber);) {
				callback.accept("Server waiting for clients...");
		    while(true) {
		
					ClientThread c = new ClientThread(mysocket.accept(), count);
					clients.add(c);
					clients.get(count).start();
					count++;
					callback.accept("Client has connected to server: " + "client #" + count);
					callback2.accept("# Clients: " + count);

					if  (count == 1) {
						callback3.accept("Joined game");
					} else if (count == 2) {
						callback7.accept("Joined game");
						gameInfo = new MorraInfo();
						callback1.accept("Round " + gameInfo.round + " in progress");
						//clients.get(0).start();
						//clients.get(1).start();
					} else {

					}
			    }
			}//end of try
				catch(Exception e) {
					// callback.accept("Server socket did not launch");
				}
			}//end of while
		}

		class ClientThread extends Thread{
			
		
			Socket connection;
			ObjectInputStream in;
			ObjectOutputStream out;
			int clientNumber;

			ClientThread(Socket s, int n){
				this.connection = s;
				this.clientNumber = n+1;
			}
			
			//
			// Sends all clients the updates MorraInfo object
			//
			public void updateClients(MorraInfo info) {
				for(int i = 0; i < clients.size(); i++) {
				 	ClientThread t = clients.get(i);
				 	try {
						t.out.writeUnshared(info);
				 	}
				 	catch(Exception e) {}
				 }
			}
			
			
			//
			// A simple method to send messages to all clients
			//
			public void sendMessage(String s) {
				for (int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
						t.out.writeObject(s);
					} catch (Exception e) {}
				}
			}
			
			//
			//
			//
			public boolean checkPlayAgain() {
				
				if (gameInfo.p1Again && gameInfo.p2Again) {
					callback.accept("starting new game...");
	    			gameInfo = new MorraInfo();
	    			updateClients(gameInfo);
	    			callback1.accept("Round 1 in progress");
	    			return true;
	    		}
				return false;
				
			}

			public void run(){
					
				try {
					
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
					callback.accept("Client number: " + clientNumber + " started.");
					out.writeObject(clientNumber);
					out.writeObject("You are client #" + clientNumber);
					
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
					
				 while(true) {
					    try {
					    	
					    	// data = [play, guess]
					    	ArrayList<Integer> data = (ArrayList<Integer>) in.readObject();
					    	
					    	if (data.get(0) != 99) {
					    		callback.accept("client: " + clientNumber + " sent: " + data.toString());
					    	} else {
					    		callback.accept("client: " + clientNumber + " wants to play again.");
					    		sendMessage("client: " + clientNumber + " wants to play again.");
					    		
					    		if (clientNumber == 1) {
					    			gameInfo.p1Again = true;
					    			callback6.accept("Yes");
					    		} else if (clientNumber == 2) {
					    			gameInfo.p2Again = true;
					    			callback10.accept("Yes");
					    		}
					    	}
					    	
					    	
					    	if (!checkPlayAgain()) {
						    	if (clientNumber <= 2) {
						    		gameInfo.addGuess(data.get(0), data.get(1), clientNumber);
									updateRound();
						    	}
					    	}
					    	
					    	// updates clients if MorraInfo has relevant update
					    	if (gameInfo.update) {
					    		gameInfo.update = false;
					    		updateClients(gameInfo);
					    	}
					    	
					    	
					    }
					    catch(Exception e) {
					    	callback.accept("Client " + clientNumber + " disconnected.");
					    	sendMessage("Client #" + clientNumber + " has left the server!");
					    	clients.remove(this);
					    	gameInfo.have2Players = false;
					    	count = clients.size();
					    	break;
					    }
					}
				}
		}
}