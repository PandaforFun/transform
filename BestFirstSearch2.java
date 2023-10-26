import java.util.*;

class Main {
    public static List<Character> bestFirstSearch(Map<Character, List<Character>> adjacencyList, char startNode, char goalNode, Map<Character, Integer> heuristic) {
        List<Character> path = new ArrayList<>();
        Set<Character> openList = new HashSet<>();
        Set<Character> closedList = new HashSet<>();
        Map<Character, Character> parent = new HashMap<>();

        openList.add(startNode);

        while (!openList.isEmpty()) {
            char currentNode = selectNodeWithLowestHeuristic(openList, heuristic);
            openList.remove(currentNode);
            closedList.add(currentNode);

            if (currentNode == goalNode) {
                char node = goalNode;
                while (node != startNode) {
                    path.add(node);
                    node = parent.get(node);
                }
                path.add(startNode);
                Collections.reverse(path);
                return path;
            }

            List<Character> neighbors = adjacencyList.getOrDefault(currentNode, Collections.emptyList());

            for (char neighbor : neighbors) {
                if (!closedList.contains(neighbor) && !openList.contains(neighbor)) {
                    parent.put(neighbor, currentNode);
                    openList.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }
    private static char selectNodeWithLowestHeuristic(Set<Character> openList, Map<Character, Integer> heuristic) {
        char selectedNode = openList.iterator().next();
        int lowestHeuristic = heuristic.get(selectedNode);

        for (char node : openList) {
            int nodeHeuristic = heuristic.get(node);
            if (nodeHeuristic < lowestHeuristic) {
                selectedNode = node;
                lowestHeuristic = nodeHeuristic;
            }
        }
        return selectedNode;
    }

    public static int calculatePathCost(List<Character> path, Map<Character, Integer> heuristicValues) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            char currentNode = path.get(i);
            char nextNode = path.get(i + 1);
            cost += heuristicValues.get(currentNode);
        }
        cost += heuristicValues.get(path.get(path.size() - 1));
        return cost;
    }

    public static void main(String[] args) {
        Map<Character, List<Character>> adjacencyList = new HashMap<>();
        adjacencyList.put('S', Arrays.asList('A', 'B', 'C'));
        adjacencyList.put('A', Arrays.asList('D', 'E'));
        adjacencyList.put('B', Arrays.asList('F', 'G'));
        adjacencyList.put('C', Collections.singletonList('H'));
        adjacencyList.put('D', Collections.singletonList('A'));
        adjacencyList.put('E', Collections.singletonList('A'));
        adjacencyList.put('F', Collections.singletonList('B'));
        adjacencyList.put('G', Collections.singletonList('B'));
        adjacencyList.put('H', Arrays.asList('I', 'J'));
        adjacencyList.put('I', Arrays.asList('K', 'L', 'M'));
        adjacencyList.put('J', Collections.singletonList('H'));
        adjacencyList.put('K', Collections.singletonList('I'));
        adjacencyList.put('L', Collections.singletonList('I'));
        adjacencyList.put('M', Collections.singletonList('I'));

        Map<Character, Integer> heuristicValues = new HashMap<>();
        heuristicValues.put('S', 11);
        heuristicValues.put('A', 7);
        heuristicValues.put('B', 6);
        heuristicValues.put('C', 5);
        heuristicValues.put('D', 7);
        heuristicValues.put('E', 6);
        heuristicValues.put('F', 6);
        heuristicValues.put('G', 6);
        heuristicValues.put('H', 5);
        heuristicValues.put('I', 4);
        heuristicValues.put('J', 5);
        heuristicValues.put('K', 3);
        heuristicValues.put('L', 3);
        heuristicValues.put('M', 3);

        char startNode = 'S';
        char goalNode = 'E';

        List<Character> path = bestFirstSearch(adjacencyList, startNode, goalNode, heuristicValues);

        if (!path.isEmpty()) {
            System.out.println("Path from " + startNode + " to " + goalNode + ": " + path);
            int cost = calculatePathCost(path, heuristicValues);
            System.out.println("Cost of the path: " + cost);
        } else {
            System.out.println("No path found from " + startNode + " to " + goalNode);
        }
    }
}
