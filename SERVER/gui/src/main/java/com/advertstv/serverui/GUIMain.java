package com.advertstv.serverui;

import com.advertstv.server.ServerProvider;
import com.advertstv.server.ClientTest;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//could just import javafx.*
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.geometry.Orientation;

public class GUIMain extends Application {
	
	//JavaFX applicatoin still use the main method.
	//It should only ever contain the call to the launch method
	private TextArea textArea;
	private ServerProvider server;
	private ClientTest client;
	public static void main(String[] args) {
		launch(args);
	}

	//this is where we put the code for the user interface
	@Override
	public void start(Stage primaryStage) {
		
		ServerProvider server = new ServerProvider();
		ClientTest client = new ClientTest();
		
		//The primaryStage is the top-level container
		primaryStage.setTitle("AdvertsTV - Spain");
		
		//BorderLayout layout manager
		BorderPane componentLayout = new BorderPane();
		componentLayout.setPadding(new Insets(20,20,20,20));

		Button btnStart = new Button("START SERVER");
		Button btnStop = new Button("STOP SERVER");
		
		Button clientTest = new Button("ClientTEST");
		Button clientDisc = new Button("ClientDisc");
	
		btnStart.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		btnStop.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    
		TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
		tileButtons.setPadding(new Insets(5, 5, 5, 5));
		tileButtons.setHgap(5.0);
		tileButtons.setVgap(5.0);
		tileButtons.getChildren().addAll(btnStart, btnStop,clientTest,clientDisc);
		
		// Create TextArea
		textArea = new TextArea();
		textArea.setText("Welcome to AdvertsTV\n");
		
		clientDisc.setOnAction(new EventHandler() {
			public void handle(Event event) {
					client.disconnect((string) -> {textArea.appendText(string + "\n");});
					System.out.print(event.toString());
				}
		});
		
		btnStart.setOnAction(new EventHandler() {
			public void handle(Event event) {
					server.connect((string) -> {textArea.appendText(string + "\n");});
					System.out.print(event.toString());
				}
		});

		
		btnStop.setOnAction(new EventHandler() {
			public void handle(Event event) {
					server.disconnect((string) -> {textArea.appendText(string + "\n");});
					System.out.print(event.toString());
				}
		});
		
		clientTest.setOnAction(new EventHandler() {
			public void handle(Event event) {
					client.connectClient();
				}
		});
		
		//Add the BorderPane to the Scene
		componentLayout.setTop(tileButtons);
		componentLayout.setBottom(textArea);
		
		Scene appScene = new Scene(componentLayout,500,270);
		//Add the Scene to the Stage
		primaryStage.setScene(appScene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
		        Platform.exit();
		        System.exit(0);
		    });
		
	}
	
	
	
}