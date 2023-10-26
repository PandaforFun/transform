import java.util.Scanner;

public class jugAutomated
{
    static int[] jugs_start = new int[2];
    static int[] jugs = jugs_start;
    static int[] jugs_end = new int[2];
    static int jug1_capacity,jug2_capacity;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the capacity of jug 1: ");
        jug1_capacity = sc.nextInt();
        System.out.print("Enter the capacity of jug 2: ");
        jug2_capacity = sc.nextInt();
        System.out.print("Enter the starting filled state of jugs (jug1 jug2): ");
        jugs_start[0] = sc.nextInt();
        jugs_start[1] = sc.nextInt();
        System.out.print("Enter the ending filled state of jugs (jug1 jug 2): ");
        jugs_end[0] = sc.nextInt();
        jugs_end[1] = sc.nextInt();
        rules();
    }
    public static void rules()
    {
        Scanner sc1 = new Scanner(System.in);
        int rule;
        do {
            System.out.print("Enter which rule you wish to choose: ");
            rule = sc1.nextInt();
            if(jugs[0] == jugs_end[0] && jugs[1] == jugs_end[1]){
                System.out.printf("Congratulations the water in jug 2 is %d gallons\n",jugs[1]);
                System.exit(0);}
            switch (rule) {
                case 1: {
                    jugs[0] = jug1_capacity;
                    System.out.printf("Current water in jugs are %d and %d gallons\n", jugs[0], jugs[1]);
                    break;
                }
                case 2: {
                    jugs[1] = jug2_capacity;
                    System.out.printf("Current water in jugs are %d and %d gallons\n", jugs[0], jugs[1]);
                    break;
                }
                case 3: {
                    jugs[0] = 0;
                    System.out.printf("Current water in jugs are %d and %d gallons\n", jugs[0], jugs[1]);
                    break;
                }
                case 4: {
                    jugs[1] = 0;
                    System.out.printf("Current water in jugs are %d and %d gallons\n", jugs[0], jugs[1]);
                    break;
                }
                case 5: {
                    if (jugs[0] + jugs[1] >= jug1_capacity && jugs[1] > 0) {
                        jugs[1] = jugs[1] - (jug1_capacity - jugs[0]);
                        jugs[0] = jug1_capacity;
                        System.out.printf("Current water in jugs are %d and %d gallons\n", jugs[0], jugs[1]);
                    } else System.out.println("Invalid use of rule");
                    break;
                }
                case 6: {
                    if (jugs[0] + jugs[1] >= jug2_capacity && jugs[0] > 0) {
                        jugs[0] = jugs[0] - (jug2_capacity - jugs[1]);
                        jugs[1] = jug2_capacity;
                        System.out.printf("Current water in jugs are %d and %d gallons\n", jugs[0], jugs[1]);
                    } else System.out.println("Invalid use of rule");
                    break;
                }
                case 7: {
                    if (jugs[0] + jugs[1] <= jug1_capacity && jugs[1] > 0) {
                        jugs[0] = jugs[0] + jugs[1];
                        jugs[1] = 0;
                        System.out.printf("Current water in jugs are %d and %d gallons\n", jugs[0], jugs[1]);
                    } else System.out.println("Invalid use of rule");
                    break;
                }
                case 8: {
                    if (jugs[0] + jugs[1] <= jug2_capacity && jugs[0] > 0) {
                        jugs[1] = jugs[1] + jugs[0];
                        jugs[0] = 0;
                        System.out.printf("Current water in jugs are %d and %d gallons\n", jugs[0], jugs[1]);
                    } else System.out.println("Invalid use of rule");
                    break;
                }
                case 9: {
                    int remainingCapacityJug1 = jug1_capacity - jugs[0];
                    if (jugs[1] >= remainingCapacityJug1 && jugs[0] < jug1_capacity) {
                        jugs[1] -= remainingCapacityJug1;
                        jugs[0] = jug1_capacity;
                        System.out.printf("Current water in jugs are %d and %d gallons\n", jugs[0], jugs[1]);
                    } else if (jugs[1] < remainingCapacityJug1 && jugs[0] < jug1_capacity) {
                        jugs[0] += jugs[1];
                        jugs[1] = 0;
                        System.out.printf("Current water in jugs are %d and %d gallons\n", jugs[0], jugs[1]);
                    } else {
                        System.out.println("Invalid use of rule");
                    }

                    if (jugs[0] == jugs_end[0] && jugs[1] == jugs_end[1]) {
                        System.out.printf("Congratulations the water in jug 1 is %d gallons and jug 2 is %d gallons\n", jugs[0],jugs[1]);
                        System.exit(0);
                    }
                    break;
                }
            }
        }while (rule != 10) ;
    }
}