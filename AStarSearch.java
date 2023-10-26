import java.util.*;

public class AStarSearch {
    public static void main(String[] args) {
        int[][] initialPuzzle = {
                {2, 8, 3},
                {1, 6, 4},
                {7, 0, 5}
        };

        int[][] finalPuzzle = {
                {1, 2, 3},
                {8, 0, 4},
                {7, 6, 5}
        };

        Node solution = solvePuzzle(initialPuzzle, finalPuzzle);

        if (solution == null) {
            System.out.println("No solution found.");
        } else {
            System.out.println("Solution steps:");
            printSolution(solution);
        }
    }
    public static int heuristic(int[][] initial , int[][] goal)
    {
        int h=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(initial[i][j]!=goal[i][j])h++;
            }
        }
        return h-1;
    }
    public static Node solvePuzzle(int[][] initial, int[][] goal) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        Node initialNode = new Node(initial, null, 0, heuristic(initial, goal));
        openList.add(initialNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.remove(0);
            closedList.add(currentNode);

            if (Arrays.deepEquals(currentNode.state, goal)) {
                return currentNode;
            }

            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

            for (int[] dir : directions) {
                int newX = currentNode.blankX + dir[0];
                int newY = currentNode.blankY + dir[1];

                if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                    int[][] newState = new int[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            newState[i][j] = currentNode.state[i][j];
                        }
                    }
                    newState[currentNode.blankX][currentNode.blankY] = newState[newX][newY];
                    newState[newX][newY] = 0;

                    Node newNode = new Node(newState, currentNode, currentNode.g + 1, heuristic(newState, goal));

                    if (!isNodeInList(newNode, closedList)) {
                        if (!isNodeInList(newNode, openList)) {
                            openList.add(newNode);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void printSolution(Node solution) {
        List<Node> path = new ArrayList<>();
        Node currentNode = solution;
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }
        Collections.reverse(path);

        for (Node node : path) {
            printState(node.state);
            System.out.println();
        }
    }

    public static void printState(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Node {
        int[][] state;
        Node parent;
        int g; // current node cost from origin
        int h; // Heuristic value
        int blankX; // X-coordinate
        int blankY; // Y-coordinate

        Node(int[][] state, Node parent, int g, int h) {
            this.state = state;
            this.parent = parent;
            this.g = g;
            this.h = h;
            findBlankTileCoordinates();
        }

        private void findBlankTileCoordinates() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (state[i][j] == 0) {
                        blankX = i;
                        blankY = j;
                        return;
                    }
                }
            }
        }
    }

    public static boolean isNodeInList(Node node, List<Node> nodeList) {
        for (Node n : nodeList) {
            if (Arrays.deepEquals(node.state, n.state)) {
                return true;
            }
        }
        return false;
    }
}
