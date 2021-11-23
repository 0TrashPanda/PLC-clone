import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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

        HashMap<String, Integer> count = new HashMap<String, Integer>();
        count.put("AND", 0);
        count.put("OR", 0);
        count.put("NOT", 0);
        count.put("INPUT", 0);
        count.put("OUTPUT", 0);
        
        // count.get(App.gate).;

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
        gateCore.setOnMouseDragged(eventHandlers.dragMouse);
        gateCore.setOnMousePressed(eventHandlers.pressMouse);

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
            circle.setOnMousePressed(eventHandlers.circlepress);
            circle.setOnMouseDragged(eventHandlers.circledrag);
            circle.setOnMouseReleased(eventHandlers.circleEnd);

        }
        for (int i = 0; i < outputs.get(App.gate); i++) {
            Circle circle = new Circle(0, 0, 10);
            circle.setTranslateY(25 * i);
            gateGroupOut.getChildren().add(circle);
            outputList.add(circle);
            circle.setOnMousePressed(eventHandlers.circlepress);
            circle.setOnMouseDragged(eventHandlers.circledrag);
            circle.setOnMouseReleased(eventHandlers.circleEnd);

        }

        Group gateGroup = new Group(gateCore, gateGroupIn, gateGroupOut);
        gateGroup.setStyle("-fx-background-color:#ebaec6");
        gateGroup.setTranslateX(x);
        gateGroup.setTranslateY(y);
        return gateGroup;
    }
}