import java.util.Arrays;
import java.util.List;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

public class treeCreator {
    static List<Gates> gates = Arrays.<Gates>asList(
        new Gates("INPUT", "IO"),
        new Gates("OUTPUT", "IO"),
        new Gates("NOT", "BASIC"),
        new Gates("OR", "EXOTIC"),
        new Gates("AND", "BASIC"),
        new Gates("DATA_ADDER", "DATA"),
        new Gates("DATA_INPUT", "DATA"),
        new Gates("DATA_OUTPUT", "DATA")
        );
    static TreeItem<String> rootNode = new TreeItem<String>("GATES");

    public static TreeView<String> makeTree() {

        rootNode.setExpanded(true);
        for (Gates gate : gates) {
            TreeItem<String> empLeaf = new TreeItem<String>(gate.getName());
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(gate.getDepartment())) {
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> depNode = new TreeItem<String>(gate.getDepartment());
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }

        TreeView<String> treeView = new TreeView<String>(rootNode);

        treeView.setCellFactory(tv -> new TreeCell<String>() {
            {
                setOnDragDetected(e -> {
                    if (!isEmpty()) {
                        if (App.GateList.contains(getItem())) {
                            App.gate = getItem();
                            placeItems.mState = mouseStates.placeGate;
                            App.scene.setCursor(Cursor.CROSSHAIR);

                            Dragboard db = startDragAndDrop(TransferMode.COPY);
                            ClipboardContent cc = new ClipboardContent();
                            cc.putString(getItem());
                            db.setContent(cc);

                            Group dragImg = drawpoint.drawPoint(0, 0, false);
                            new Scene(dragImg).setFill(Color.TRANSPARENT);
                            db.setDragView(dragImg.snapshot(null, null));
                        } else {
                            placeItems.mState = mouseStates.select;
                            App.scene.setCursor(Cursor.DEFAULT);
                        }
                    }
                });

                setOnMouseClicked(e -> {
                    if (!isEmpty()) {
                        if (App.GateList.contains(getItem())) {
                            App.gate = getItem();
                            placeItems.mState = mouseStates.placeGate;
                            App.scene.setCursor(Cursor.CROSSHAIR);
                        } else {
                            placeItems.mState = mouseStates.select;
                            App.scene.setCursor(Cursor.DEFAULT);
                        }
                    }
                });
            }

            @Override
            public void updateItem(String value, boolean empty) {
                super.updateItem(value, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(getItem());
                }
            }
        });

        DragResizerXY.makeResizable(treeView);

        treeView.setShowRoot(false);

        return treeView;
    }

}