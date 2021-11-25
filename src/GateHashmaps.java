import java.util.HashMap;
import javafx.scene.paint.Color;

public class GateHashmaps {
    static HashMap<String, Color> colors;
    static HashMap<String, Integer> inputs;
    static HashMap<String, Integer> outputs;
    static HashMap<String, Integer> count;

    public static void genHashmap() {
        colors = new HashMap<String, Color>();
        colors.put("AND", Color.RED);
        colors.put("OR", Color.YELLOW);
        colors.put("NOT", Color.PURPLE);
        colors.put("INPUT", Color.PINK);
        colors.put("OUTPUT", Color.LIGHTBLUE);
    
        inputs = new HashMap<String, Integer>();
        inputs.put("AND", 2);
        inputs.put("OR", 2);
        inputs.put("NOT", 1);
        inputs.put("INPUT", 0);
        inputs.put("OUTPUT", 1);
    
        outputs = new HashMap<String, Integer>();
        outputs.put("AND", 1);
        outputs.put("OR", 1);
        outputs.put("NOT", 1);
        outputs.put("INPUT", 1);
        outputs.put("OUTPUT", 0);
    
        count = new HashMap<String, Integer>();
        count.put("AND", 0);
        count.put("OR", 0);
        count.put("NOT", 0);
        count.put("INPUT", 0);
        count.put("OUTPUT", 0);
    }    
}