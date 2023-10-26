class Main {
    public static void bfs(int[][] graph, int startNode , int targetNode) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        int[] queue = new int[n];
        int front = 0, rear = 0;

        visited[startNode] = true;
        queue[rear++] = startNode;

        while (front != rear) {
            int currentNode = queue[front++];
            System.out.print((char) ('A' + currentNode));
            if (currentNode != targetNode) System.out.print("->");
            for (int i = 0; i < n; i++) {
                if (graph[currentNode][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue[rear++] = i;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] adjacencyMatrix = {
                {0, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0}
        };

        int startNode = 0;
        int targetNode = 4;
        System.out.println("Breadth-First Traversal [starting from node " + (char) ('A' + startNode) + "]:");
        bfs(adjacencyMatrix, startNode , targetNode);
    }
}
