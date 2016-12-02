import java.util.Scanner;

public class DiGraphTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);        
        DiGraph graph; 
        char menuChoice;
        int from, to;

        System.out.println("Enter the number of vertices: ");
        graph = new DiGraph(scan.nextInt()); 

        System.out.println("Choose one of the following operations: ");
        System.out.println("- add edge (enter a)");
        System.out.println("- delete edge (enter d)");
        System.out.println("- edge count (enter e)");
        System.out.println("- vertex count (enter v)");
        System.out.println("- print graph (enter p)");
        System.out.println("- Quit (enter q)");

        menuChoice = scan.next().charAt(0); 
        String temp = scan.nextLine();

        if (temp.length() == 0) {
            switch (menuChoice) {
                case 'a':
                    System.out.println("Enter source and destination vertex: ");
                    from = scan.nextInt();
                    to = scan.nextInt();
                    graph.addEdge(from, to);
                    break;
                case 'd':
                    System.out.println("Enter source and destination vertex: ");
                    from = scan.nextInt();
                    to = scan.nextInt();
                    graph.deleteEdge(from, to);
                    break;
                case 'e':
                    System.out.println("Number of edges is: " + graph.edgeCount());
                    break;
                case 'v':
                    System.out.println("Number of vertices is: " + graph.vertexCount());
                    break;
                case 'p':
                    System.out.println("The graph is the following: ");
                    graph.print();
                    break;
                case 'q':
                    System.out.println("Good bye.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
        else {
            System.out.println("Invalid input");
        }

        while (menuChoice != 'q') {
            menuChoice = scan.next().charAt(0); 
            temp = scan.nextLine();

            if (temp.length() == 0) {
                switch (menuChoice) {
                    case 'a':
                        System.out.println("Enter source and destination vertex: ");
                        from = scan.nextInt();
                        to = scan.nextInt();
                        graph.addEdge(from, to);
                        break;
                    case 'd':
                        System.out.println("Enter source and destination vertex: ");
                        from = scan.nextInt();
                        to = scan.nextInt();
                        graph.deleteEdge(from, to);
                        break;
                    case 'e':
                        System.out.println("Number of edges is: " + graph.edgeCount());
                        break;
                    case 'v':
                        System.out.println("Number of vertices is: " + graph.vertexCount());
                        break;
                    case 'p':
                        System.out.println("The graph is the following: ");
                        graph.print();
                        break;
                    case 'q':
                        System.out.println("Good bye.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input!");
                        break;
                }
            }
            else
                System.out.println("Invalid input!");
        }
    }
}
