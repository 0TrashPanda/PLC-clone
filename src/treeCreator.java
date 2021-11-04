import java.util.Arrays;
import java.util.List;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class treeCreator {

    private static int cellCount = 0;

    static List<Gates> gates = Arrays.<Gates>asList(new Gates("NOT", "BASIC"), new Gates("OR", "EXOTIC"),
            new Gates("AND", "BASIC"));
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
                System.out.println("Cells created: " + (++cellCount));

                setOnDragDetected(e -> {
                    if (!isEmpty()) {
                        placeItems.mState = mouseStates.placeGate;
                        App.scene.setCursor(Cursor.CROSSHAIR);           
                        Dragboard db = startDragAndDrop(TransferMode.COPY);
                        ClipboardContent cc = new ClipboardContent();
                        cc.putString(getItem());
                        db.setContent(cc);
                        Label label = new Label(getItem());
                        Rectangle rect = new Rectangle(100, 50, 100, 50);
                        rect.setFill(Color.RED);
                        Group dragImg = new Group();
                        dragImg.getChildren().add(rect);
                        dragImg.getChildren().add(label);
                        new Scene(dragImg);
                        db.setDragView(dragImg.snapshot(null, null));
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