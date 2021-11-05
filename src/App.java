import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class App extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    public static String gate = "AND";
    
    private static VBox vBoxAll = new VBox();
    public static Scene scene = new Scene(vBoxAll);

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

        vBoxAll.getChildren().add(menuBar.makeMenuBar(parent));
        vBoxAll.getChildren().add(parent);
        parent.prefHeightProperty().bind(vBoxAll.heightProperty());
        String css = this.getClass().getResource("main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                placeItems.mState = mouseStates.select;
                scene.setCursor(Cursor.DEFAULT);          
            }
        });

        stage.setScene(scene);
        stage.show();
    }

}