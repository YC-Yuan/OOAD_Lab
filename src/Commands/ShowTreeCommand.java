package Commands;

import BookMarkTree.*;
import Utils.TreePrinter;

import java.util.LinkedList;
import java.util.List;

public class ShowTreeCommand extends Command {
    @Override
    public boolean execute() {
        BookMarkTree bmt = BookMarkTree.getInstance();
        TreePrinter tp = new TreePrinter();
        List<Node> nodes = new LinkedList<>();
        for (Folder f : bmt.getRoots()
        ) {
            nodes.addAll(f.getAll());
        }
        for (Node node : nodes) {
            tp.addNode(node.getLevel(), node.getTreeName());
        }
        System.out.print(tp);
        return true;
    }
}
