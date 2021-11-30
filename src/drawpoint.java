import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class drawpoint {

    public static ArrayList<String> inputList = new ArrayList<String>();
    public static ArrayList<String> outputList = new ArrayList<String>();
    public static ArrayList<String> DataInputList = new ArrayList<String>();
    public static ArrayList<String> DataOutputList = new ArrayList<String>();

    public static Group drawPoint(double x, double y, boolean increment) {

        if (increment) {
            GateHashmaps.count.put(App.gate, GateHashmaps.count.get(App.gate) + 1);
        }

        int rectheight;
        int inputHeight = GateHashmaps.inputs.get(App.gate)+GateHashmaps.DataInputs.get(App.gate);
        int outputHeight = GateHashmaps.outputs.get(App.gate)+GateHashmaps.DataOutputs.get(App.gate);

        if (inputHeight > outputHeight) {
            rectheight = inputHeight * 25;
        } else {
            rectheight = outputHeight * 25;
        }

        Text gateText = new Text();
        gateText.setText(App.gate + GateHashmaps.count.get(App.gate));
        gateText.setX(25);
        gateText.setY(5);
        double rectWidth = gateText.getLayoutBounds().getWidth() + 50;
        Rectangle temprect = new Rectangle(0, -12, rectWidth, rectheight);
        temprect.setFill(GateHashmaps.colors.get(App.gate));


        Group gateCore = new Group();
        gateCore.setOnMouseDragged(eventHandlers.dragMouse);
        gateCore.setOnMousePressed(eventHandlers.pressMouse);

        gateCore.getChildren().addAll(temprect, gateText);

        Group gateGroupIn = new Group();
        Group gateGroupOut = new Group();
        gateGroupOut.setTranslateX(rectWidth);

        for (int i = 0; i < GateHashmaps.inputs.get(App.gate); i++) {
            Circle circle = new Circle(0, 0, 10);
            circle.setTranslateY(25 * i);
            circle.setId(App.gate+ "⌂" + GateHashmaps.count.get(App.gate)+ "⌂" + "I"+ "⌂" + i);
            gateGroupIn.getChildren().add(circle);
            inputList.add(circle.getId());
            GateIO_Drag.DrawLine(circle); //* adds line connecting capabilities
        }
        for (int i = 0; i < GateHashmaps.outputs.get(App.gate); i++) {
            Circle circle = new Circle(0, 0, 10);
            circle.setId(App.gate+ "⌂" + GateHashmaps.count.get(App.gate)+ "⌂" + "Q"+ "⌂" + i);
            circle.setTranslateY(25 * i);
            gateGroupOut.getChildren().add(circle);
            outputList.add(circle.getId());
            GateIO_Drag.DrawLine(circle); //* adds line connecting capabilities
        }

        for (int i = 0; i < GateHashmaps.DataInputs.get(App.gate); i++) {
            Rectangle dataRect = new Rectangle(0, 0, 15, 15);
            dataRect.setId(App.gate+ "⌂" + GateHashmaps.count.get(App.gate)+ "⌂" + "DI"+ "⌂" + i);
            dataRect.setTranslateY(25 * i - 8);
            dataRect.setTranslateX(-8);
            gateGroupIn.getChildren().add(dataRect);
            DataInputList.add(dataRect.getId());
            GateIO_Drag.DrawDataLine(dataRect); //* adds line connecting capabilities
        }
        for (int i = 0; i < GateHashmaps.DataOutputs.get(App.gate); i++) {
            Rectangle dataRect = new Rectangle(0, 0, 15, 15);
            dataRect.setId(App.gate+ "⌂" + GateHashmaps.count.get(App.gate)+ "⌂" + "DQ"+ "⌂" + i);
            dataRect.setTranslateY(25 * i - 8);
            dataRect.setTranslateX(-8);
            gateGroupOut.getChildren().add(dataRect);
            DataOutputList.add(dataRect.getId());
            GateIO_Drag.DrawDataLine(dataRect); //* adds line connecting capabilities
        }

        Group gateGroup = new Group(gateCore, gateGroupIn, gateGroupOut);
        gateGroup.setTranslateX(x);
        gateGroup.setTranslateY(y);
        return gateGroup;
    }
}