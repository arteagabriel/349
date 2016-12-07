/*
 * Gabriel Arteaga garteaga@calpoly.edu
 * Alexandra Deany adeany@calpoly.edu
 *
 * Project 1
 * 12/06/2016
 */

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
            } else 
                System.out.println("Edge (" + from + ", " + to + ") already added.");
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
            } else 
                System.out.println("Edge (" + from + ", " + to + ") already deleted.");
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

    private class VertexInfo {
        public int distance;
        public int parent;

        VertexInfo() {
            this.distance = -1;
            this.parent = -1;
        }
    }

    private List<VertexInfo> BFS(int s) {
        int n = vertexCount();
        List<VertexInfo> vertices = new ArrayList<VertexInfo>(); 
        Queue<Integer> q = new LinkedList<Integer>();

        for (int i = 0; i <= n; i++) {
            vertices.add(new VertexInfo());
        }

        vertices.get(s).distance = 0;
        q.add(s);

        int u;
        LinkedList<Integer> temp;
        while (!q.isEmpty()) {
            u = q.remove();
            temp = list.get(u);

            for (int v : temp) {
                if (vertices.get(v).distance == -1) {
                    vertices.get(v).distance = vertices.get(u).distance + 1;
                    vertices.get(v).parent = u;
                    q.add(v);
                }
            }
        }

        return vertices;
    }

    public boolean isTherePath(int from, int to) {
        List<VertexInfo> paths = BFS(from);
        VertexInfo i = paths.get(to);
        int next = i.parent;

        while (next != -1) {
            if (from == next)
                return true;

            i = paths.get(next);
            next = i.parent;
        }

        return false;
    }

    public int lengthOfPath(int from, int to) {
        List<VertexInfo> paths = BFS(from);
        VertexInfo i = paths.get(to);
        int length = i.distance;

        return length >= 0 ? length : 0;
    }

    public void printPath(int from, int to) {
        List<VertexInfo> va = BFS(from);
        String output;

        if (va.get(to).distance == -1)
            System.out.println("There is no path");
        else {
            output = "";
            VertexInfo i = va.get(to);
            int next = i.parent;

            if (next == from)
                output = "->" + to + output;
            else 
                while (next != -1 && next != from) {
                    if (va.get(to).equals(i)) {
                        output = "->" + to + output;
                    }

                    output = "->" + next + output;

                    i = va.get(next);
                    next = va.get(next).parent;
                }

            output = from + output;
            System.out.println(output);
        }
    }

    private class TreeNode {
        public int vertex;
        public List<TreeNode> children;
        
        TreeNode() {
        }

        TreeNode(int v) {
            this.vertex = v;
            this.children = new LinkedList<TreeNode>();
        }
    }

    private TreeNode buildTree(int s) {
        List<VertexInfo> vertices = BFS(s);
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        TreeNode root = null;

        for (int i = 1; i < vertices.size(); i++) {
            // System.out.println(i);
            // System.out.println(vertices.get(i).parent);
        }
        for (int i = 0; i <= list.size(); i++) {
            nodes.add(new TreeNode(i));
        }
        
        int parent;
        TreeNode temp;
        for (int i = 1; i < vertices.size(); i++) {
            parent = vertices.get(i).parent;
            // System.out.println("i " + i + " : parent " + parent);

            if (parent != -1)
                nodes.get(parent).children.add(nodes.get(i));

            // System.out.println("i " + i + " : root vertex " + root.vertex);
        }

        return nodes.get(s);
    }

    public void printTree(int s) {
        TreeNode root = buildTree(s);

        // System.out.println(root.vertex);
        printTreeAux(root, 0);
    }

    private void printTreeAux(TreeNode node, int tabs) {
        for (int i = 0; i < tabs; i++)
            System.out.print("\t");
        System.out.println(node.vertex);

        for (TreeNode n : node.children)
            printTreeAux(n, tabs + 1);
    }
}
