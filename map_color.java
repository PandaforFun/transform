import java.util.*;

class Main {
    public static void main(String[] args) {
        String[] cities = {"WA", "NT", "SA", "Q", "NSW", "V", "T"};
        int[][] adjacencyMatrix = {
                {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0, 0},
                {1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 0, 1, 0, 0},
                {0, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        Map<String, String> cityColors = new HashMap<>();
        for (String city : cities) {
            cityColors.put(city, "-*-");
        }
        String[] colors = {"Red", "Blue", "Green"};

        printState(cityColors);
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            Set<String> usedColors = new HashSet<>();

            for (int j = 0; j < cities.length; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    String neighborColor = cityColors.get(cities[j]);
                    if (neighborColor != null) {
                        usedColors.add(neighborColor);
                    }
                }
            }

            for (String color : colors) {
                if (!usedColors.contains(color)) {
                    cityColors.put(city, color);
                    break;
                }
            }

            printState(cityColors);
        }
    }

    private static void printState(Map<String, String> cityColors) {
        System.out.println("Current Coloring:");
        for (Map.Entry<String, String> entry : cityColors.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("------------------");
    }
}
