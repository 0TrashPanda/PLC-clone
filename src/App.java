import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.robot.Robot;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeCell;
import javafx.stage.StageStyle;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.HashMap;
import javafx.scene.layout.VBox;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    Robot robot = new Robot();
    private static int cellCount = 0;

    List<Gates> gates = Arrays.<Gates>asList(new Gates("NOT", "BASIC"), new Gates("OR", "EXOTIC"),
            new Gates("AND", "BASIC"));
    TreeItem<String> rootNode = new TreeItem<String>("GATES");

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

        rootNode.setExpanded(true);
        for (Gates gate : gates) {
            TreeItem<String> empLeaf = new TreeItem<String>(gate.getName());
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(gate.getDepartment())) {
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> depNode = new TreeItem<String>(gate.getDepartment());
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }

        stage.setTitle("Tree View Sample");
        VBox box = new VBox();
        parent.getChildren().add(box);
        TreeView<String> treeView = new TreeView<String>(rootNode);

        treeView.setCellFactory(tv -> new TreeCell<String>() {
            {
                System.out.println("Cells created: " + (++cellCount));

                setOnDragDetected(e -> {
                    if (!isEmpty()) {
                        Dragboard db = startDragAndDrop(TransferMode.COPY);
                        ClipboardContent cc = new ClipboardContent();
                        cc.putString(getItem());
                        db.setContent(cc);
                        Label label = new Label(getItem());
                        Rectangle rect = new Rectangle(100, 50, 100, 50);
                        rect.setFill(Color.RED);
                        Group dragImg = new Group();
                        dragImg.getChildren().add(rect);
                        dragImg.getChildren().add(label);
                        new Scene(dragImg);
                        db.setDragView(dragImg.snapshot(null, null));
                    }
                });
            }

            @Override
            public void updateItem(String value, boolean empty) {
                super.updateItem(value, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(getItem());
                }
            }
        });

        DragResizerXY.makeResizable(treeView);
        parent.getChildren().add(treeView);
        parent.getChildren().add(canvas);

        treeView.setShowRoot(false);

        stage.initStyle(StageStyle.TRANSPARENT);

        Circle circle = new Circle(100, 100, 100);
        canvas.getChildren().add(circle);
        circle.setFill(Color.LIMEGREEN);

        circle.setOnDragDetected((MouseEvent event) -> {
            System.out.println("Circle 1 drag detected");

            Dragboard db = circle.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("Circle source text");
            db.setContent(content);
        });


        HashMap<String, Color> colors = new HashMap<String, Color>();
        colors.put("AND", Color.RED);
        colors.put("OR", Color.YELLOW);
        colors.put("NOT", Color.PURPLE);

        HashMap<String, Integer> inputs = new HashMap<String, Integer>();
        inputs.put("AND", 2);
        inputs.put("OR", 2);
        inputs.put("NOT", 1);

        HashMap<String, Integer> outputs = new HashMap<String, Integer>();
        outputs.put("AND", 1);
        outputs.put("OR", 1);
        outputs.put("NOT", 1);

        canvas.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                System.out.println("Dropped: " + db.getString());

                if (colors.containsKey(db.getString())) {
                    Bounds boundsInScreen = canvas.localToScreen(canvas.getBoundsInLocal());
                    Group gateGroup = new Group();
                    gateGroup.setTranslateX(robot.getMousePosition().getX() - boundsInScreen.getMinX());
                    gateGroup.setTranslateY(robot.getMousePosition().getY() - boundsInScreen.getMinY());
                    gateGroup.setOnMousePressed(pressMouse);
                    gateGroup.setOnMouseDragged(dragMouse);
                    int rectheight;
                    if (inputs.get(db.getString())>outputs.get(db.getString())) {
                        rectheight=inputs.get(db.getString())*25;
                    } else {
                        rectheight=outputs.get(db.getString())*25;
                    }
                    Rectangle temprect = new Rectangle(0, -12,100 , rectheight);
                    temprect.setFill(colors.get(db.getString()));
                    gateGroup.getChildren().add(temprect);
                    Text gateText = new Text();
                    gateText.setText(db.getString());
                    gateText.setX(25);
                    gateText.setY(5);
                    gateGroup.getChildren().add(gateText);
                    for (int i = 0; i < inputs.get(db.getString()); i++) {
                        gateGroup.getChildren().add(new Circle(0, 25 * i, 10));
                    }
                    for (int i = 0; i < outputs.get(db.getString()); i++) {
                        gateGroup.getChildren().add(new Circle(100, 25 * i, 10));
                    }
                    canvas.getChildren().add(gateGroup);


                }

                event.setDropCompleted(true);

            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });

        canvas.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != canvas && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.ANY);
                }

                event.consume();
            }
        });

        Menu menu1 = new Menu("Menu 1");
        MenuBar menuBar = new MenuBar();
        menuBar.getStyleClass().add("topbar");
        menuBar.getMenus().add(menu1);
        Button exit = new Button();
        exit.getStyleClass().add("exitbutton");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage stage = (Stage) exit.getScene().getWindow();
                stage.close();
            }
        });
        exit.setText("X");
        exit.prefWidthProperty().set(50);
        HBox topbar = new HBox();
        topbar.getStyleClass().add("topbar");
        topbar.getChildren().add(menuBar);
        topbar.getChildren().add(exit);
        topbar.prefWidthProperty().bind(parent.widthProperty());
        menuBar.prefWidthProperty().bind(parent.widthProperty());

        VBox vBoxAll = new VBox();
        vBoxAll.getChildren().add(topbar);
        vBoxAll.getChildren().add(parent);
        parent.prefHeightProperty().bind(vBoxAll.heightProperty());
        Scene scene = new Scene(vBoxAll);
        String css = this.getClass().getResource("main.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    EventHandler<MouseEvent> pressMouse = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Group) (t.getSource())).getTranslateX();
            orgTranslateY = ((Group) (t.getSource())).getTranslateY();
        }
    };

    EventHandler<MouseEvent> dragMouse = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            ((Group) (t.getSource())).setTranslateX(newTranslateX);
            ((Group) (t.getSource())).setTranslateY(newTranslateY);
        }
    };

}