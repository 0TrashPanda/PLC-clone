import javafx.scene.Group;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.event.EventHandler;

public class placeItems {

    static ArrayList<Group> gateList = new ArrayList<>();
    static ArrayList<Rectangle> rectList = new ArrayList<>();
    public static mouseStates mState = mouseStates.select;

    public static void placeItem(Pane canvas) {

        canvas.setOnDragDropped((DragEvent event) -> {
            double x = event.getX();
            double y = event.getY();
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                Group c = drawpoint.drawPoint(x, y);
                gateList.add(c);
                canvas.getChildren().add(c);
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

        canvas.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();

            // for (Rectangle temprect : rectList) {
            // temprect.setFill(Color.PINK);
            // System.out.println("knaagdier");
            // double newPosX = temprect.getParent().getBoundsInParent().getMinX()+x;
            // double newPosY = temprect.getParent().getBoundsInParent().getMinY()+y;
            // System.out.println("xxx"+newPosX);
            // System.out.println("yyy"+newPosY);

            // if (temprect.contains(newPosX,newPosY)) {
            // canvas.getChildren().add(new Circle(x, y, 100));
            // System.out.println("knaa");
            // return;
            // }
            // }

            if (mState == mouseStates.select) {
                return;
            }

            if (e.getButton() == MouseButton.PRIMARY) {
                Group c = drawpoint.drawPoint(x, y);
                gateList.add(c);
                canvas.getChildren().add(c);

            }
            // else if (e.getButton() == MouseButton.SECONDARY) {
            // removePoint(x, y);

            // }

        });
    }
}