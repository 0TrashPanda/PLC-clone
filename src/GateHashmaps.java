import java.util.HashMap;
import javafx.scene.paint.Color;

public class GateHashmaps {
    static HashMap<String, Color> colors;
    static HashMap<String, Integer> inputs;
    static HashMap<String, Integer> outputs;
    static HashMap<String, Integer> count;
    static HashMap<String, Integer> DataInputs;
    static HashMap<String, Integer> DataOutputs;

    public static void genHashmap() {
        colors = new HashMap<String, Color>();
        colors.put("AND", Color.TOMATO);
        colors.put("OR", Color.KHAKI);
        colors.put("NOT", Color.BLUEVIOLET);
        colors.put("INPUT", Color.PINK);
        colors.put("OUTPUT", Color.LIGHTBLUE);
        colors.put("DATA_ADDER", Color.CRIMSON);
        colors.put("DATA_INPUT", Color.DODGERBLUE);
        colors.put("DATA_OUTPUT", Color.MEDIUMSPRINGGREEN);
    
        inputs = new HashMap<String, Integer>();
        inputs.put("AND", 2);
        inputs.put("OR", 2);
        inputs.put("NOT", 1);
        inputs.put("INPUT", 0);
        inputs.put("OUTPUT", 1);
        inputs.put("DATA_ADDER", 0);
        inputs.put("DATA_INPUT", 0);
        inputs.put("DATA_OUTPUT", 0);
    
        outputs = new HashMap<String, Integer>();
        outputs.put("AND", 1);
        outputs.put("OR", 1);
        outputs.put("NOT", 1);
        outputs.put("INPUT", 1);
        outputs.put("OUTPUT", 0);
        outputs.put("DATA_ADDER", 0);
        outputs.put("DATA_INPUT", 0);
        outputs.put("DATA_OUTPUT", 0);

        count = new HashMap<String, Integer>();
        count.put("AND", 0);
        count.put("OR", 0);
        count.put("NOT", 0);
        count.put("INPUT", 0);
        count.put("OUTPUT", 0);
        count.put("DATA_ADDER", 0);
        count.put("DATA_INPUT", 0);
        count.put("DATA_OUTPUT", 0);

        DataInputs = new HashMap<String, Integer>();
        DataInputs.put("AND", 0);
        DataInputs.put("OR", 0);
        DataInputs.put("NOT", 0);
        DataInputs.put("INPUT", 0);
        DataInputs.put("OUTPUT", 0);
        DataInputs.put("DATA_ADDER", 2);
        DataInputs.put("DATA_INPUT", 0);
        DataInputs.put("DATA_OUTPUT", 1);
    
        DataOutputs = new HashMap<String, Integer>();
        DataOutputs.put("AND", 0);
        DataOutputs.put("OR", 0);
        DataOutputs.put("NOT", 0);
        DataOutputs.put("INPUT", 0);
        DataOutputs.put("OUTPUT", 0);
        DataOutputs.put("DATA_ADDER", 1);
        DataOutputs.put("DATA_INPUT", 1);
        DataOutputs.put("DATA_OUTPUT", 0);
    }    
}