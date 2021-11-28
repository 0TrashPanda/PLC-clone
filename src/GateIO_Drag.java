import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;

public class GateIO_Drag {

    public static void DrawLine(Circle circle) {

        circle.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != circle && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
                event.consume();
            }
        });

        circle.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                placeItems.mState = mouseStates.select;
                App.scene.setCursor(Cursor.DEFAULT);
            }
        });

        circle.setOnDragDetected(e -> {
            Dragboard db = circle.startDragAndDrop(TransferMode.COPY);
            ClipboardContent cc = new ClipboardContent();
            cc.putString(circle.getId());
            db.setContent(cc);
        });

        circle.setOnDragDropped((

        DragEvent event)->
        {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                Node start = null;
                Node end = null;
                if (drawpoint.outputList.contains(db.getString()) && drawpoint.inputList.contains(circle.getId())) {
                    String output = circle.getId();
                    String input = db.getString();
                    String[] io = {input, output};
                    DrawLines.LineList.add(io);
                    start = DrawLines.getNodeFromId(input);
                    end = DrawLines.getNodeFromId(output);
                }
                if (drawpoint.inputList.contains(db.getString()) && drawpoint.outputList.contains(circle.getId())) {
                    String input = circle.getId();
                    String output = db.getString();
                    String[] io = {input, output};
                    DrawLines.LineList.add(io);
                    start = DrawLines.getNodeFromId(input);
                    end = DrawLines.getNodeFromId(output);
                }
                DrawLines.DrawLine(start, end);
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }
}