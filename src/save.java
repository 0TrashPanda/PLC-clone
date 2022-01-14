import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class save {

    public static String filePath = "/home/jonah/OneDrive/Documenten/vs code/test projects/test.csv";

    public static void writeDataAtOnce(String filePath ,List<String[]> data ) {

        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            // List<String[]> data = new ArrayList<String[]>();
            // data.add(new String[] { "Name", "Class 55", "Marks" });
            // data.add(new String[] { "Aman", "10", "620" });
            // data.add(new String[] { "Suraj", "10", "630" });
            
            writer.writeAll(data);
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
