import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class drawpoint {

    public static ArrayList<Circle> inputList = new ArrayList<Circle>();
    public static ArrayList<Circle> outputList = new ArrayList<Circle>();

    public static Group drawPoint(double x, double y) {
        
        GateHashmaps.count.put(App.gate, GateHashmaps.count.get(App.gate) + 1);

        int rectheight;
        if (GateHashmaps.inputs.get(App.gate) > GateHashmaps.outputs.get(App.gate)) {
            rectheight = GateHashmaps.inputs.get(App.gate) * 25;
        } else {
            rectheight = GateHashmaps.outputs.get(App.gate) * 25;
        }

        Rectangle temprect = new Rectangle(0, -12, 100, rectheight);
        temprect.setFill(GateHashmaps.colors.get(App.gate));

        Text gateText = new Text();
        gateText.setText(App.gate+GateHashmaps.count.get(App.gate));
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

        for (int i = 0; i < GateHashmaps.inputs.get(App.gate); i++) {
            Text fakeId = new Text();
            fakeId.setText(App.gate+GateHashmaps.count.get(App.gate)+"I"+i);
            fakeId.setFill(Color.TRANSPARENT);
            Circle circle = new Circle(0, 0, 10);
            circle.setTranslateY(25 * i);
            Group circleId = new Group(fakeId, circle);
            gateGroupIn.getChildren().add(circleId);
            inputList.add(circle);
            circle.setOnMousePressed(eventHandlers.circlepress);
            circle.setOnMouseDragged(eventHandlers.circledrag);
            circle.setOnMouseReleased(eventHandlers.circleEnd);
        }
        for (int i = 0; i < GateHashmaps.outputs.get(App.gate); i++) {
            Text fakeId = new Text();
            fakeId.setText(App.gate+GateHashmaps.count.get(App.gate)+"Q"+i);
            fakeId.setFill(Color.TRANSPARENT);
            Circle circle = new Circle(0, 0, 10);
            circle.setTranslateY(25 * i);
            Group circleId = new Group(fakeId, circle);
            gateGroupOut.getChildren().add(circleId);
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