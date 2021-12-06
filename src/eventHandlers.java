import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class eventHandlers {
    static double orgSceneX;
    static double orgSceneY;
    static double orgTranslateX;
    static double orgTranslateY;

    static Line connectLine = new Line(0, 0, 0, 0);
    static Rectangle drawBox = new Rectangle();

    static EventHandler<MouseEvent> selectBoxPress = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (placeItems.mState == mouseStates.select) {
                App.scene.setCursor(Cursor.DEFAULT);
                double offsetX = t.getX();
                double offsetY = t.getY();
                App.canvas.getChildren().add(connectLine);
                connectLine.setStartX(offsetX);
                connectLine.setStartY(offsetY);
                connectLine.setEndX(offsetX);
                connectLine.setEndY(offsetY);
            }
        }
    };

    static EventHandler<MouseEvent> selectBoxDrag = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (placeItems.mState == mouseStates.select) {
                double offsetX = t.getX();
                double offsetY = t.getY();
                connectLine.setEndX(offsetX);
                connectLine.setEndY(offsetY);
            }
        }
    };
    static EventHandler<MouseEvent> selectBoxEnd = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (placeItems.mState == mouseStates.select) {
                Pane TempPane = (Pane) connectLine.getParent();
                TempPane.getChildren().remove(connectLine);
            }
        }
    };

    static EventHandler<MouseEvent> pressMouse = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            placeItems.mState = mouseStates.moveGate;
            App.scene.setCursor(Cursor.DEFAULT);
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Group) (t.getSource())).getParent().getTranslateX();
            orgTranslateY = ((Group) (t.getSource())).getParent().getTranslateY();
        }
    };
    
    static EventHandler<MouseEvent> dragMouse = new EventHandler<MouseEvent>() {
        
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            ((Group) (t.getSource())).getParent().setTranslateX(newTranslateX);
            ((Group) (t.getSource())).getParent().setTranslateY(newTranslateY);
        }
    };
    static EventHandler<MouseEvent> dropMouse = new EventHandler<MouseEvent>() {
        
        @Override
        public void handle(MouseEvent t) {
            DrawLines.update();
        }
    };
}
