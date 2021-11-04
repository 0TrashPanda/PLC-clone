import javafx.scene.Group;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import javafx.event.EventHandler;

public class placeItems {

    static ArrayList<Group> gateList = new ArrayList<>();

    public static void placeItem(Pane canvas) {

        // mouseStates mState = mouseStates.select;

        canvas.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
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

            // if (mState == mouseStates.select) {
            // return;
            // }

            for (Group c : gateList) {
                if (c.contains(x, y))
                    return;
            }

            if (e.getButton() == MouseButton.PRIMARY) {
                Group c = drawpoint.drawPoint(x, y, canvas);
                gateList.add(c);
                canvas.getChildren().add(c);

            }
            // else if (e.getButton() == MouseButton.SECONDARY) {
            // removePoint(x, y);

            // }

        });
    }

}
