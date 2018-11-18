package com.advertstv.serverui;
//could just import javafx.*
import com.advertstv.server.ServerProvider;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.geometry.Orientation;

import java.io.IOException;

public class GUIMain extends Application {
	
	//JavaFX applicatoin still use the main method.
	//It should only ever contain the call to the launch method
	private TextArea textArea;
	private ServerProvider server;
	public static void main(String[] args) {
		launch(args);
	}

	//this is where we put the code for the user interface
	@Override
	public void start(Stage primaryStage) {
		server = new ServerProvider();
		//The primaryStage is the top-level container
		primaryStage.setTitle("Server AdvertsTV");

		//BorderLayout layout manager
		BorderPane componentLayout = new BorderPane();
		componentLayout.setPadding(new Insets(20,20,20,20));

		Button btnStart = new Button("START SERVER");
		Button btnStop = new Button("STOP SERVER");

		btnStart.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		btnStop.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    
		TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
		tileButtons.setPadding(new Insets(5, 5, 5, 5));
		tileButtons.setHgap(5.0);
		tileButtons.setVgap(5.0);
		tileButtons.getChildren().addAll(btnStart, btnStop);
		
		// Create TextArea
		textArea = new TextArea();
		textArea.setText("Welcome to AdvertsTV\n");
		
		btnStart.setOnAction(new EventHandler() {
			public void handle(Event event) {
				server.connect((string) -> {textArea.appendText(string + "\n");});
				System.out.print(event.toString());
			}
		});

		
		btnStop.setOnAction(new EventHandler() {
			public void handle(Event event) {
					synchronized(server)
					{
						server.disconnect((string) -> {textArea.appendText(string + "\n");});
						Platform.runLater(() -> {textArea.appendText("Server stopped.\n");} );
						System.out.print(event.toString());
					}

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