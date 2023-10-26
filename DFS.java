class Main {
    public static void dfs(int[][] graph, int startNode) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        int[] stack = new int[n];
        int top = 0;

        visited[startNode] = true;
        stack[top++] = startNode;

        while (top > 0) {
            int currentNode = stack[--top];
            System.out.print((char) ('A' + currentNode) + " ");
            for (int i = 0; i < n; i++) {
                if (graph[currentNode][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    stack[top++] = i;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 8;
        int[][] adjacencyMatrix = {
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
        };

        int startNode = 0;
        System.out.println("Depth-First Traversal [starting from node " + (char) ('A' + startNode) + "]:");
        dfs(adjacencyMatrix, startNode);
    }
}
