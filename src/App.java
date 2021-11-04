import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Image icon = new Image("Icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("WTF is this man doing");
        stage.setMaximized(true);
        stage.setMinHeight(250);
        stage.setMinWidth(500);

        HBox parent = new HBox();
        Pane canvas = new Pane();
        canvas.prefWidthProperty().bind(parent.widthProperty());
        canvas.getStyleClass().add("canvas");

        parent.getChildren().addAll(treeCreator.makeTree(), canvas);

        placeItems.placeItem(canvas);

        VBox vBoxAll = new VBox();
        vBoxAll.getChildren().add(menuBar.makeMenuBar(parent));
        vBoxAll.getChildren().add(parent);
        parent.prefHeightProperty().bind(vBoxAll.heightProperty());
        Scene scene = new Scene(vBoxAll);
        String css = this.getClass().getResource("main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

}