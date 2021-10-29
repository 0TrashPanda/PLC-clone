import javafx.application.Application;
import javafx.event.EventHandler;
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
        canvas.setPrefHeight(500);

        TreeItem<String> rootItem = new TreeItem<String>("GATES");

        TreeItem<String> basicgates = new TreeItem<String>("BASIC");
        basicgates.getChildren().add(new TreeItem<String>("AND"));
        basicgates.getChildren().add(new TreeItem<String>("NOT"));
        rootItem.getChildren().add(basicgates);

        TreeItem<String> customgates = new TreeItem<String>("CUSTOM");
        customgates.getChildren().add(new TreeItem<String>("NAND"));
        customgates.getChildren().add(new TreeItem<String>("OR"));
        customgates.getChildren().add(new TreeItem<String>("XOR"));
        rootItem.getChildren().add(customgates);

        TreeView<String> treeView = new TreeView<String>();
        canvas.getStyleClass().add("canvas");
        treeView.getStyleClass().add("TreeView");
        treeView.setRoot(rootItem);
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

                int move = 2;
                int x = 100 * move;
                Circle circle2 = new Circle(x, 100 * move, 100);
                circle.getStyleClass().add("circle");
                CircleGroup.getChildren().add(circle2);
                circle2.setFill(Color.PINK);
                event.setDropCompleted(true);
                move = move + 1;

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

        canvas.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                System.out.println("Dropped: " + db.getString());
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });

        canvas.getChildren().add(CircleGroup);

        stage.setScene(scene);
        stage.show();
    }

}