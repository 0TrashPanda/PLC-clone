import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static ArrayList<String> GateList = new ArrayList<String>();

    public static String gate = "AND";

    private static VBox vBoxAll = new VBox();
    public static Scene scene = new Scene(vBoxAll);
    public static Pane canvas = new Pane();

    @Override
    public void start(Stage stage) throws Exception {

        Image icon = new Image("Icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("WTF is this man doing");
        stage.setMaximized(true);
        stage.setMinHeight(250);
        stage.setMinWidth(500);
        HBox parent = new HBox();
        canvas.prefWidthProperty().bind(parent.widthProperty());
        canvas.getStyleClass().add("canvas");
        canvas.setOnMousePressed(selectBoxPress);
        canvas.setOnMouseDragged(selectBoxDrag);
        canvas.setOnMouseReleased(selectBoxEnd);

        GateList.add("AND");
        GateList.add("NOT");
        GateList.add("OR");
        GateList.add("INPUT");
        GateList.add("OUTPUT");
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

    static double orgSceneX;
    static double orgSceneY;
    static double orgTranslateX;
    static double orgTranslateY;

    static Line connectLine = new Line(0, 0, 0, 0);

    static EventHandler<MouseEvent> selectBoxPress = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (placeItems.mState == mouseStates.select) {
                App.scene.setCursor(Cursor.DEFAULT);
                double offsetX = t.getX();
                double offsetY = t.getY();
                App.canvas.getChildren().add(connectLine);
                connectLine.setStartX(offsetX);
                connectLine.setStartY(offsetY);
                connectLine.setEndX(offsetX);
                connectLine.setEndY(offsetY);
            }

        }
    };

    static EventHandler<MouseEvent> selectBoxDrag = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (placeItems.mState == mouseStates.select) {
                double offsetX = t.getX();
                double offsetY = t.getY();
                connectLine.setEndX(offsetX);
                connectLine.setEndY(offsetY);
            }
        }
    };
    static EventHandler<MouseEvent> selectBoxEnd = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (placeItems.mState == mouseStates.select) {
                Pane TempPane = (Pane) connectLine.getParent();
                TempPane.getChildren().remove(connectLine);
            }
        }
    };
}