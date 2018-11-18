package com.advertstv.clientui;
//could just import javafx.*
import com.advertstv.client.ClientTest;
import com.advertstv.clientui.player.MinimalPlayer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.geometry.Orientation;

public class GUIMain extends Application {

	private ClientTest client;
	private MinimalPlayer player;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		client = new ClientTest();
		player = new MinimalPlayer();

		primaryStage.setTitle("Client - AdvertsTV");
		BorderPane componentLayout = new BorderPane();
		componentLayout.setPadding(new Insets(20,20,20,20));

		Button clientTest = new Button("ClientTEST");
		Button clientDisc = new Button("ClientDisc");
		Button playButton = new Button("PLAY");

        final Canvas videoSurface = new Canvas();

		TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
		tileButtons.setPadding(new Insets(5, 5, 5, 5));
		tileButtons.setHgap(5.0);
		tileButtons.setVgap(5.0);
		tileButtons.getChildren().addAll(clientTest,clientDisc);

		// Create TextArea
		clientDisc.setOnAction(new EventHandler() {
			public void handle(Event event) {
					client.disconnect();
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
		componentLayout.setBottom(videoSurface);

		Scene appScene = new Scene(componentLayout,200,100);
		//Add the Scene to the Stage
		primaryStage.setScene(appScene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
		        Platform.exit();
		        System.exit(0);
		    });
	}

}