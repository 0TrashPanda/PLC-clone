import java.lang.reflect.Array;
import java.util.ArrayList;

public class DrawLines {
    
    public static ArrayList<String[]> LineList = new ArrayList<String[]>();

    public static void update() {
        System.out.println("reloading lines");
        for (int i = 0; i < LineList.size(); i++) {
            String[] line = LineList.get(i);
            ArrayList<String[]> ComboList = new ArrayList<String[]>();
            for (int j = 0; j < line.length; j++) {
                String[] gate = line[j].split("⌂",-2);
                ComboList.add(gate);
            }
            for (int j = 0; j < ComboList.size(); j++) {
                String[] item = ComboList.get(j);
                for (String a : item)
                    System.out.println(a);
                System.out.println();
            }
            
        }
    }
}
