import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
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

    public static ArrayList<String> GateList = new ArrayList<String>(); // todo: automate this shit (makes list of all gates)

    public static String gate = "AND"; // *creates var of selected gates

    ArrayList<ArrayList<String>>gateInfoList = new ArrayList<ArrayList<String>>();

    private static VBox vBoxAll = new VBox();
    public static Scene scene = new Scene(vBoxAll);
    public static Group LineGroup = new Group();
    public static Group GateGroup = new Group();
    public static Pane canvas = new Pane(LineGroup, GateGroup);

    @Override
    public void start(Stage stage) throws Exception {

        Image icon = new Image("Icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("WTF is this man doing");
        stage.setMaximized(true);
        stage.setMinHeight(250);
        stage.setMinWidth(500);
        HBox parent = new HBox();
        canvas.prefWidthProperty().bind(parent.widthProperty()); //& just like with = 100% (set with to parent with)
        canvas.getStyleClass().add("canvas"); // css bullshit    //* (makes class 'canvas')

        // fixme: broken code lies ahead, approach with caution
        // //canvas.setOnMousePressed(eventHandlers.selectBoxPress);
        // //canvas.setOnMouseDragged(eventHandlers.selectBoxDrag);
        // //canvas.setOnMouseReleased(eventHandlers.selectBoxEnd);

        GateList.add("AND");
        GateList.add("NOT");
        GateList.add("OR");
        GateList.add("INPUT");
        GateList.add("OUTPUT");
        GateList.add("DATA_ADDER");
        GateList.add("DATA_INPUT");
        GateList.add("DATA_OUTPUT");

        parent.getChildren().addAll(treeCreator.makeTree(), canvas);

        placeItems.placeItem(canvas);

        GateHashmaps.genHashmap(); //* generate the hashmaps

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

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.R) {
                DrawLines.update();
            }
            else if (e.getCode() == KeyCode.C) {
                LineGroup.getChildren().clear();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

}