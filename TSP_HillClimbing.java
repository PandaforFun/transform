import java.util.Arrays;
import java.util.Random;

public class TSP_HillClimbing {

    public static void main(String[] args) {
        int[][] graph = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        int[] tour = hillClimbingTSP(graph);
        System.out.println("Optimal Tour: " + Arrays.toString(tour));
        System.out.println("Optimal Tour Length: " + calculateTourLength(graph, tour));
    }

    public static int[] hillClimbingTSP(int[][] graph) {
        int n = graph.length;
        int[] currentTour = generateRandomTour(n);
        int currentTourLength = calculateTourLength(graph, currentTour);

        boolean improved;
        do {
            improved = false;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int[] newTour = twoOptSwap(currentTour, i, j);
                    int newTourLength = calculateTourLength(graph, newTour);

                    if (newTourLength < currentTourLength) {
                        currentTour = newTour;
                        currentTourLength = newTourLength;
                        improved = true;
                    }
                }
            }
        } while (improved);
        return currentTour;
    }

    public static int[] generateRandomTour(int n) {
        int[] tour = new int[n];
        for (int i = 0; i < n; i++) {
            tour[i] = i;
        }
        shuffleArray(tour);
        return tour;
    }

    public static void shuffleArray(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static int[] twoOptSwap(int[] tour, int i, int j) {
        int[] newTour = Arrays.copyOf(tour, tour.length);
        while (i < j) {
            int temp = newTour[i];
            newTour[i] = newTour[j];
            newTour[j] = temp;
            i++;
            j--;
        }
        return newTour;
    }

    public static int calculateTourLength(int[][] graph, int[] tour) {
        int length = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            length += graph[tour[i]][tour[i + 1]];
        }
        length += graph[tour[tour.length - 1]][tour[0]]; // Return to the starting city
        return length;
    }
}
