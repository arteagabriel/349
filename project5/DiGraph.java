import java.util.*;

public class DiGraph {
    private List<LinkedList<Integer>> list;
    
    DiGraph(int n) {
        list = new ArrayList<LinkedList<Integer>>();

        for (int i = 0; i <= n; i++)
            list.add(new LinkedList<Integer>());
    }

    public void addEdge(int from, int to) {
        int n = list.size();
        
        if (from <= n && from > 0 && to <= n && to > 0) {
            LinkedList<Integer> vertex = list.get(from);

            if (!vertex.contains(to)) {
                vertex.add(to);
                System.out.println("Edge (" + from + ", " + to + ") was added.");
            }
        }
        else
            System.out.println("Vertex doesn't exist");
    }

    public void deleteEdge(int from, int to) {
        int n = list.size();

        if (from <= n && from > 0 && to <= n && to > 0) {
            LinkedList<Integer> vertex = list.get(from);

            if (vertex.contains(to)) {
                vertex.remove(Integer.valueOf(to));
                System.out.println("Edge (" + from + ", " + to + ") was deleted.");
            }
         }
        else
            System.out.println("Vertex doesn't exist");
    }

    public int edgeCount() {
        int size = 0;
        for (LinkedList<Integer> vertex : list)
            size += vertex.size();

        return size;
    }

    // subtract 1 since we don't use 0 index
    public int vertexCount() { return list.size() - 1; }

    public void print() {
        boolean first;

        for (int i = 1; i < list.size(); i++) {
            System.out.print(i + " is connected to: ");
            first = true;

            for (int v : list.get(i)) {
                if (first) {
                    System.out.print(v);
                    first = false;
                } else
                    System.out.print(", " + v);
            }

            System.out.println();
        }
    }
}
