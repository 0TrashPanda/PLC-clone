import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class drawpoint {

    public static Group drawPoint(double x, double y) {

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

        
        int rectheight;
        if (inputs.get(App.gate) > outputs.get(App.gate)) {
            rectheight = inputs.get(App.gate) * 25;
        } else {
            rectheight = outputs.get(App.gate) * 25;
        }
        Rectangle temprect = new Rectangle(0, -12, 100, rectheight);
        temprect.setFill(colors.get(App.gate));
        Text gateText = new Text();
        gateText.setText(App.gate);
        gateText.setX(25);
        gateText.setY(5);
        Group gateGroupIn = new Group();
        Group gateGroupOut = new Group();
        for (int i = 0; i < inputs.get(App.gate); i++) {
            gateGroupIn.getChildren().add(new Circle(0, 25 * i, 10));
        }
        for (int i = 0; i < outputs.get(App.gate); i++) {
            gateGroupOut.getChildren().add(new Circle(100, 25 * i, 10));
        }
        
        Group gateGroup = new Group(temprect,gateText,gateGroupIn,gateGroupOut);
        gateGroup.setStyle("-fx-background-color:#ebaec6");
        gateGroup.setTranslateX(x);
        gateGroup.setTranslateY(y);
        gateGroup.setOnMouseDragged(dragMouse);
        gateGroup.setOnMousePressed(pressMouse);
        return gateGroup;
    }

    static double orgSceneX;
    static double orgSceneY;
    static double orgTranslateX;
    static double orgTranslateY;

    static EventHandler<MouseEvent> pressMouse = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            placeItems.mState = mouseStates.select;
            App.scene.setCursor(Cursor.DEFAULT);          
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Group) (t.getSource())).getTranslateX();
            orgTranslateY = ((Group) (t.getSource())).getTranslateY();
        }
    };

    static EventHandler<MouseEvent> dragMouse = new EventHandler<MouseEvent>() {

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