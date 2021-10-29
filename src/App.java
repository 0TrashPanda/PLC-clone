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
// import javafx.stage.StageStyle;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.VBox;

public class App extends Application {

    Robot robot = new Robot();

    public static void main(String[] args) {
        launch(args);
    }

    List<Gates> gates = Arrays.<Gates>asList(
        new Gates("NOT", "BASIC"),
        new Gates("OR", "EXOTIC"),
        new Gates("AND", "BASIC"));
    TreeItem<String> rootNode = new TreeItem<String>("GATES");

    int move = 1;
    int hory = 200;

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

        DragResizerXY.makeResizable(treeView);
        parent.getChildren().add(treeView);
        parent.getChildren().add(canvas);

        treeView.setShowRoot(false);

        Scene scene = new Scene(parent);
        String css = this.getClass().getResource("main.css").toExternalForm();
        scene.getStylesheets().add(css);
        // stage.initStyle(StageStyle.TRANSPARENT);

        Circle circle = new Circle(100, 100, 100);
        circle.getStyleClass().add("circle");
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
                System.out.println(boundsInScreen.getMaxX());

                System.out.println(hory);
                Circle circle2 = new Circle(robot.getMousePosition().getX() - boundsInScreen.getMinX(),
                        robot.getMousePosition().getY() - boundsInScreen.getMinY(), 10);
                CircleGroup.getChildren().add(circle2);
                circle2.setFill(Color.PINK);
                event.setDropCompleted(true);

            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });

        canvas.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != canvas && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        // private final class TextFieldTreeCellImpl extends TreeCell<String> {

        // private TextField textField;

        // public TextFieldTreeCellImpl() {
        // }

        // @Override
        // public void startEdit() {
        // super.startEdit();

        // if (textField == null) {
        // createTextField();
        // }
        // setText(null);
        // setGraphic(textField);
        // textField.selectAll();
        // }

        // @Override
        // public void cancelEdit() {
        // super.cancelEdit();
        // setText((String) getItem());
        // setGraphic(getTreeItem().getGraphic());
        // }

        // @Override
        // public void updateItem(String item, boolean empty) {
        // super.updateItem(item, empty);

        // if (empty) {
        // setText(null);
        // setGraphic(null);
        // } else {
        // if (isEditing()) {
        // if (textField != null) {
        // textField.setText(getString());
        // }
        // setText(null);
        // setGraphic(textField);
        // } else {
        // setText(getString());
        // setGraphic(getTreeItem().getGraphic());
        // }
        // }
        // }

        // private void createTextField() {
        // textField = new TextField(getString());
        // textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

        // @Override
        // public void handle(KeyEvent t) {
        // if (t.getCode() == KeyCode.ENTER) {
        // commitEdit(textField.getText());
        // } else if (t.getCode() == KeyCode.ESCAPE) {
        // cancelEdit();
        // }
        // }
        // });
        // }

        // private String getString() {
        // return getItem() == null ? "" : getItem().toString();
        // }
        // }

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