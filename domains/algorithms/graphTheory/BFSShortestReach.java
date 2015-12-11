/**
*Given an undirected graph consisting of N nodes (labelled 1 to N) where a specific given node S represents the start position and an edge between any two nodes is of length 6 units in the graph.
*
*It is required to calculate the shortest distance from start position (Node S) to all of the other nodes in the graph.
*
*Note 1: If a node is unreachable , the distance is assumed as −1. 
*Note 2: The length of each edge in the graph is 6 units.
*
*Input Format
*
*The first line contains T, denoting the number of test cases. 
*First line of each test case has two integers N, denoting the number of nodes in the graph and M, denoting the number of edges in the graph. 
*The next M lines each consist of two space separated integers x y, where x and y denote the two nodes between which the edge exists. 
*The last line of a testcase has an integer S, denoting the starting position.
*
*Constraints 
*1≤T≤10 
*2≤N≤1000 
*1≤M≤N×(N−1)2 
*1≤x,y,S≤N
*
*Output Format
*
*For each of T test cases, print a single line consisting of N−1 space-separated integers, denoting the shortest distances of the N-1 nodes from starting position S. This will be done for all nodes same as in the order of input 1 to N.
*
*For unreachable nodes, print −1.
*/
import java.util.*;

/**
 * Created by Raphael.Campos on 08/09/2015.
 */
public class SolvedMeSecond {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Node> allNodes = new ArrayList<>();
        Node root;
        int testCases = scan.nextInt();

        for (int i = 0; i < testCases; i++) {
            int numNodes = scan.nextInt();
            int numEdges = scan.nextInt();

            for (int j = 1; j <= numNodes; j++) {
                Node n = new Node(j);
                allNodes.add(n);
            }

            for (int k = 0; k < numEdges; k++) {
                int a = scan.nextInt();
                int b = scan.nextInt();

                allNodes.get(a - 1).edges.add(allNodes.get(b - 1));
                allNodes.get(b - 1).edges.add(allNodes.get(a - 1));
            }

            root = allNodes.get(scan.nextInt()-1);
            StringBuilder distancias = new StringBuilder();
            bfs(root, allNodes);
            for (Node node : allNodes) {
                if(node.distance == Integer.MAX_VALUE)
                    distancias.append(-1+" ");
                else if(node.distance != 0)
                    distancias.append(node.distance * 6 + " ");
            }
            System.out.println(distancias);
            allNodes.clear();
        }


    }

    /**
     * Adiciona a distancia de cada {@link Node} referente a um inicial
     *
     * @param root     inicio
     * @param allNodes todos os nodes do grafo
     */
    public static void bfs(Node root, ArrayList<Node> allNodes) {
        Queue<Node> q = new LinkedList<>();
        root.distance = 0;
        q.add(root);

        while (q.size() > 0) {
            Node u = q.poll();
            for (Node n : u.edges) {
                if (n.distance == Integer.MAX_VALUE) { // Nao foi visitado ainda
                    n.distance = u.distance + 1;
                    q.add(n);
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        public int label;
        public ArrayList<Node> edges = new ArrayList<>();
        public int distance = Integer.MAX_VALUE; //aka infinito

        public Node(int label) {
            this.label = label;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return label == node.label;

        }

        @Override
        public int hashCode() {
            return label;
        }

        public int compareTo(Node o) {
            return (distance < o.distance) ? -1 : ((distance == o.distance) ? 0 : 1);
        }
    }
}
