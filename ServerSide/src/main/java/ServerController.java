import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.text.Text; 
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ListView;

public class ServerController implements Initializable {
    private Server gameServer;
    private int portNumber;

    @FXML
    public VBox root;

    @FXML
    public HBox hBox1;

    @FXML
    public VBox vBox2;

    @FXML
    public TextField portNumBox;

    @FXML
    public Button startButton;

    @FXML
    public VBox vBox3;

    @FXML
    public Text state; 

    @FXML
    public Text numClientsBox; 

    @FXML
    public VBox vBox4;

    @FXML 
    public HBox hBox2;

    @FXML
    public Text player1;

    @FXML
    public Text play1;

    @FXML
    public Text score1;

    @FXML
    public Text winner1;

    @FXML
    public Text playAgain1;

    @FXML
    public HBox hBox3;

    @FXML
    public Text player2;

    @FXML
    public Text play2;

    @FXML
    public Text score2;

    @FXML
    public Text winner2;

    @FXML
    public Text playAgain2;

    @FXML
    public ListView listview1;

	@Override
	public void initialize(URL location, ResourceBundle resources) { 
	}

    public void startGame() {
        gameServer = new Server(portNumber,
        data->{Platform.runLater(()->{listview1.getItems().add(data.toString());});},

        data1->{Platform.runLater(()->{state.setText(data1.toString());});},
        data2->{Platform.runLater(()->{numClientsBox.setText(data2.toString());});},

        data3->{Platform.runLater(()->{play1.setText(data3.toString());});},
        data4->{Platform.runLater(()->{score1.setText(data4.toString());});},
        data5->{Platform.runLater(()->{winner1.setText(data5.toString());});},
        data6->{Platform.runLater(()->{playAgain1.setText(data6.toString());});},

        data7->{Platform.runLater(()->{play2.setText(data7.toString());});},
        data8->{Platform.runLater(()->{score2.setText(data8.toString());});},
        data9->{Platform.runLater(()->{winner2.setText(data9.toString());});},
        data10->{Platform.runLater(()->{playAgain2.setText(data10.toString());});},
        
        reset->{Platform.runLater(()->{});}
        );
    }

    public void setPortNumber (ActionEvent e) throws IOException{
        portNumber = Integer.parseInt(portNumBox.getText());
        startGame();
    }
    
    public void setPlay1(String s) {
        play1.setText(s);
    }
}