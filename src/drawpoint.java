import java.util.ArrayList;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class drawpoint {

    public static ArrayList<Circle> inputList = new ArrayList<Circle>();
    public static ArrayList<Circle> outputList = new ArrayList<Circle>();

    public static Group drawPoint(double x, double y) {

        HashMap<String, Color> colors = new HashMap<String, Color>();
        colors.put("AND", Color.RED);
        colors.put("OR", Color.YELLOW);
        colors.put("NOT", Color.PURPLE);
        colors.put("INPUT", Color.PINK);
        colors.put("OUTPUT", Color.LIGHTBLUE);

        HashMap<String, Integer> inputs = new HashMap<String, Integer>();
        inputs.put("AND", 2);
        inputs.put("OR", 2);
        inputs.put("NOT", 1);
        inputs.put("INPUT", 0);
        inputs.put("OUTPUT", 1);

        HashMap<String, Integer> outputs = new HashMap<String, Integer>();
        outputs.put("AND", 1);
        outputs.put("OR", 1);
        outputs.put("NOT", 1);
        outputs.put("INPUT", 1);
        outputs.put("OUTPUT", 0);

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

        Group gateCore = new Group();
        gateCore.setOnMouseDragged(dragMouse);
        gateCore.setOnMousePressed(pressMouse);

        gateCore.getChildren().addAll(temprect, gateText);

        Group gateGroupIn = new Group();
        Group gateGroupOut = new Group();
        gateGroupOut.setTranslateX(100);

        gateGroupIn.setStyle("-fx-background-color:#ebaec6");

        for (int i = 0; i < inputs.get(App.gate); i++) {
            Circle circle = new Circle(0, 0, 10);
            circle.setTranslateY(25 * i);
            gateGroupIn.getChildren().add(circle);
            inputList.add(circle);
            circle.setOnMousePressed(circlepress);
            circle.setOnMouseDragged(circledrag);
            circle.setOnMouseReleased(circleEnd);

        }
        for (int i = 0; i < outputs.get(App.gate); i++) {
            Circle circle = new Circle(0, 0, 10);
            circle.setTranslateY(25 * i);
            gateGroupOut.getChildren().add(circle);
            outputList.add(circle);
            circle.setOnMousePressed(circlepress);
            circle.setOnMouseDragged(circledrag);
            circle.setOnMouseReleased(circleEnd);

        }

        Group gateGroup = new Group(gateCore, gateGroupIn, gateGroupOut);
        gateGroup.setStyle("-fx-background-color:#ebaec6");
        gateGroup.setTranslateX(x);
        gateGroup.setTranslateY(y);
        return gateGroup;
    }

    static double orgSceneX;
    static double orgSceneY;
    static double orgTranslateX;
    static double orgTranslateY;

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

    public static Line connectLine = new Line(0, 0, 0, 0);

    static EventHandler<MouseEvent> circlepress = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            placeItems.mState = mouseStates.select;
            App.scene.setCursor(Cursor.DEFAULT);
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Circle) (t.getSource())).getParent().getParent().getTranslateX()
                    + ((Circle) (t.getSource())).getParent().getTranslateX();
            orgTranslateY = ((Circle) (t.getSource())).getParent().getParent().getTranslateY()
                    + ((Circle) (t.getSource())).getParent().getTranslateY()
                    + ((Circle) (t.getSource())).getTranslateY();
            App.canvas.getChildren().add(connectLine);
            connectLine.setStartX(orgTranslateX);
            connectLine.setStartY(orgTranslateY);
            connectLine.setEndX(orgTranslateX);
            connectLine.setEndY(orgTranslateY);

        }
    };

    static EventHandler<MouseEvent> circledrag = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            connectLine.setEndX(newTranslateX);
            connectLine.setEndY(newTranslateY);
        }
    };
    static EventHandler<MouseEvent> circleEnd = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            Pane ee = (Pane) connectLine.getParent();
            ee.getChildren().remove(connectLine);
        }
    };

}