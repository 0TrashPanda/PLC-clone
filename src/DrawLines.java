import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.shape.Line;

public class DrawLines {

    public static ArrayList<String[]> LineList = new ArrayList<String[]>();
    private static Node LineInput;
    private static Node LineOutput;

    public static void update() {
        App.LineGroup.getChildren().clear();
        for (int i = 0; i < LineList.size(); i++) {
            String[] line = LineList.get(i);
            ArrayList<Node> AllList = getAllNodes(App.GateGroup);
            for (Node node : AllList) {
                if (node.getId() != null) {
                    if (line[0] == node.getId()) {
                        LineInput = node;
                    }
                    if (line[1] == node.getId()) {
                        LineOutput = node;
                    }
                }
            }
            DrawLine(LineInput, LineOutput);

        }
    }
    public static Node getNodeFromId(String id) {
        ArrayList<Node> AllList = getAllNodes(App.GateGroup);
        for (Node node : AllList) {
            if (node.getId() != null) {
                if (id == node.getId()) {
                    return node;
                }
            }
        }
        return null;
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
        App.LineGroup.getChildren().add(line);
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