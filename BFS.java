class Main
{
    public static void main(String[] args) {
        int n = 7;
        int[][] adjacencyMatrix = {
            {0, 1, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        int startNode = 0;
        int targetNode = n-1;
        boolean visited[] = new boolean[n];
        int[] queue = new int[n];
        int front = 0, rear = 0;
        visited[startNode] = true;
        queue[rear++] = startNode;
        int[] bfs = new int[n];
        while (front != rear) {
            int currentNode = queue[front++];
            System.out.print((char) ('A' + currentNode));
            if (currentNode != targetNode) System.out.print("->");
            for (int i = 0; i < n; i++) {  
                if (adjacencyMatrix[currentNode][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue[rear++] = i;
                }
            }
        }
    }
}
