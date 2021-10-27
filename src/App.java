import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
        treeView.setRoot(rootItem);

        treeView.setShowRoot(false);

        VBox vbox = new VBox(treeView);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);

        stage.setScene(scene);
        stage.show();
    }
}