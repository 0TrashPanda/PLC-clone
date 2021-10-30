import java.util.List;
import javafx.application.Application;
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
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
// import javafx.stage.StageStyle;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.VBox;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

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

        Scene scene = new Scene(parent);
        String css = this.getClass().getResource("main.css").toExternalForm();
        scene.getStylesheets().add(css);
        // stage.initStyle(StageStyle.TRANSPARENT);

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

        Group CircleGroup = new Group();

        canvas.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                System.out.println("Dropped: " + db.getString());

                Bounds boundsInScreen = canvas.localToScreen(canvas.getBoundsInLocal());
                Circle circle2 = new Circle(robot.getMousePosition().getX() - boundsInScreen.getMinX(),
                        robot.getMousePosition().getY() - boundsInScreen.getMinY(), 10);
                CircleGroup.getChildren().add(circle2);
                if (db.getString() == "AND") {
                    circle2.setFill(Color.RED);
                } else {
                    circle2.setFill(Color.PINK);
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

        canvas.getChildren().add(CircleGroup);

        stage.setScene(scene);
        stage.show();
    }

    public static class Gates {

        private final SimpleStringProperty name;
        private final SimpleStringProperty department;

        private Gates(String name, String department) {
            this.name = new SimpleStringProperty(name);
            this.department = new SimpleStringProperty(department);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String fName) {
            name.set(fName);
        }

        public String getDepartment() {
            return department.get();
        }

        public void setDepartment(String fName) {
            department.set(fName);
        }
    }

}