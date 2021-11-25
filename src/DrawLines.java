import java.util.ArrayList;
import java.util.Arrays;

public class DrawLines {
    
    public static ArrayList<String[]> LineList = new ArrayList<String[]>();

    public static void update() {
        for (int i = 0; i < LineList.size(); i++) {
            System.out.println(Arrays.toString(LineList.get(i)));
        }
    }
}
