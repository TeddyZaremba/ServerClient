import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ClientController implements Initializable {

	@FXML
	private TextField ipField;
	
	@FXML
	private TextField portField;
	
	@FXML
	private HBox startView;
	
	@FXML
	private HBox gameView;
	
	@FXML
	private TextField guessField;
	
	@FXML
	private TextField playField;
	
	@FXML
	public ListView<String> fromServer;
	
	@FXML
	public Text myScoreVal;
	
	@FXML
	public Text opScoreVal;
	
	@FXML
	public Button again;
	
	@FXML
	public Button send;
	
	@FXML
	public Text opGuessVal;
	
	@FXML
	public Text opPlayVal;
	
	@FXML
	public Client c;
	
	@FXML
	public ImageView opPlayImage;
	
	@FXML
	public int playNumber = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	public void connectToServer(ActionEvent e) {
		
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/gameScreen.fxml"));
			Parent gameView = loader.load();
			ClientController myctr = loader.getController();
			startView.getScene().setRoot(gameView);
			
			/*
			 * 	First consumer to recieve messages from server.
			 *  Second consumer to update the client's own score.
			 *  Third consumer to update the client's opponent's score.
			 */
			myctr.again.setDisable(true);
			myctr.c = new Client(
					  data->{Platform.runLater(()->{myctr.fromServer.getItems().add(data.toString());});}
					, myPoints->{Platform.runLater(()->{myctr.myScoreVal.setText(myPoints.toString());});}
					, opPoints->{Platform.runLater(()->{myctr.opScoreVal.setText(opPoints.toString());});}
					, opGuess->{Platform.runLater(()->{myctr.opGuessVal.setText(opGuess.toString());});}
					, opPlay->{Platform.runLater(()->{myctr.opPlayImage.setImage(new Image("/images/" + opPlay.toString() + ".png"));});}
					, gameOver->{Platform.runLater(()->{myctr.send.setDisable(true);
														myctr.again.setDisable(false);});}
					, newGame->{Platform.runLater(()->{myctr.send.setDisable(false);
					myctr.again.setDisable(true);});}
					, ipField.getText()
					, Integer.parseInt(portField.getText()));
			myctr.c.start();
			
			
		} catch (Exception ex) {}
		
	}
	
	public void sendInfo(ActionEvent e) {
		ArrayList<Integer> info = new ArrayList<Integer>();
		info.add(playNumber);
		info.add(Integer.parseInt(guessField.getText()));
		c.sendInfo(info);
	}
	
	public void playAgain(ActionEvent e) {
		ArrayList<Integer> againList = new ArrayList<Integer>();
		againList.add(99);
		againList.add(0);
		c.sendInfo(againList);
	}
	
	public void play0(MouseEvent e) {
		playNumber = 0;
		playField.setText("0");
	}
	
	public void play1(MouseEvent e) {
		playNumber = 1;
		playField.setText("1");
	}
	
	public void play2(MouseEvent e) {
		playNumber = 2;
		playField.setText("2");
	}
	
	public void play3(MouseEvent e) {
		playNumber = 3;
		playField.setText("3");
	}
	
	public void play4(MouseEvent e) {
		playNumber = 4;
		playField.setText("4");
	}
	
	public void play5(MouseEvent e) {
		playNumber = 5;
		playField.setText("5");
	}
	
	public void quit(ActionEvent e) {
		System.exit(0);
	}

}
