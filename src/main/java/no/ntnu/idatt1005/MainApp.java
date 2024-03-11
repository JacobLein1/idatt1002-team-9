package no.ntnu.idatt1005;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Text text = new Text("hello");
        text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        StackPane stackPane
                = new StackPane();
        stackPane.getChildren().add(text);

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(new Button("Button 1"));
        flowPane.getChildren().add(new Button("Button 2"));
        flowPane.getChildren().add(new Button("Button 3"));
        flowPane.getChildren().add(new Button("Button 4"));
        flowPane.getChildren().add(new Button("Button 5"));

        VBox root = new VBox();
        root.getChildren().addAll(stackPane,flowPane);

        primaryStage.setTitle("JavaFX Scene");
        Scene scene = new Scene(root,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }



}
