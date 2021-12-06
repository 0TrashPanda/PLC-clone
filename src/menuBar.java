import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class menuBar {
    public static HBox makeMenuBar(HBox parent) {

        Menu menu1 = new Menu("File");
        MenuItem m1 = new MenuItem("save");
        MenuItem m2 = new MenuItem("load");
        menu1.getItems().add(m1);
        menu1.getItems().add(m2);
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

        m1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("saved");
            }
        });        

        m2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("loaded");
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

        return topbar;
    }
}