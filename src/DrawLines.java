import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.shape.Line;

public class DrawLines {

    public static ArrayList<String[]> LineList = new ArrayList<String[]>();
    private static Node LineInput;
    private static Node LineOutput;

    public static void update() {
        System.out.println("reloading lines");
        for (int i = 0; i < LineList.size(); i++) {
            String[] line = LineList.get(i);
            ArrayList<Node> AllList = getAllNodes(App.canvas);
            for (Node node : AllList) {
                if (node.getId() != null) {
                    if (line[0] == node.getId()) {
                        System.out.println("output");
                        System.out.println(node);
                        LineInput = node;
                    }
                    if (line[1] == node.getId()) {
                        System.out.println("input");
                        System.out.println(node);
                        LineOutput = node;
                    }
                }
            }
            DrawLine(LineInput, LineOutput);
            System.out.println();

        }
    }

    public static void DrawLine(Node start, Node end) {
        double startX = start.getParent().getParent().getTranslateX()
                + start.getParent().getTranslateX();
        double startY = start.getParent().getParent().getTranslateY()
                + start.getParent().getTranslateY()
                + start.getTranslateY();
        double endX = end.getParent().getParent().getTranslateX()
                + end.getParent().getTranslateX();
        double endY = end.getParent().getParent().getTranslateY()
                + end.getParent().getTranslateY()
                + end.getTranslateY();
        Line line = new Line(startX, startY, endX, endY);
        App.canvas.getChildren().add(line);
    }

    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            nodes.add(node);
            if (node instanceof Parent)
                addAllDescendents((Parent) node, nodes);
        }
    }
}