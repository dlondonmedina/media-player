package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane {

	Media media;
	MediaPlayer player;
	MediaView view;
	Pane mpane;
	MediaBar bar;
	
	
	public Player(File file) {
		try {
			media = new Media(file.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player = new MediaPlayer(media);
		view = new MediaView(player);
		DoubleProperty mvw = view.fitWidthProperty();
		DoubleProperty mvh = view.fitHeightProperty();
		mvw.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
		mvh.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
		view.setPreserveRatio(true);
		
		mpane = new Pane();
		mpane.getChildren().add(view);
		
		setCenter(mpane);
		bar = new MediaBar(player);
		setBottom(bar);
		setStyle("-fx-background-color:#bfc2c7");
		player.play();
	}
}
