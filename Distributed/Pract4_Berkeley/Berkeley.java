import java.util.Scanner;

// Main Berkeley Algorithm class
public class Berkeley {

    // Main method
    public static void main(String[] args) {

        // Scanner object for input
        Scanner sc = new Scanner(System.in);

        // Input total number of nodes
        System.out.print(
            "Enter number of nodes: "
        );

        int n = sc.nextInt();

        // Array to store clock times
        int[] clock = new int[n];

        // Input clock values of all nodes
        System.out.println(
            "\nEnter clock time of each node:"
        );

        // Loop to input clock values
        for (int i = 0; i < n; i++) {

            // Input node clock
            System.out.print(
                "Node " + (i + 1) + ": "
            );

            // Store clock value
            clock[i] = sc.nextInt();
        }

        // Display initial clock values
        System.out.println(
            "\nInitial Clock Values:"
        );

        // Traverse all clocks
        for (int i = 0; i < n; i++) {

            // Print clock value of each node
            System.out.println(

                "Node "
                + (i + 1)

                + " = "
                + clock[i]
            );
        }

        // Select master node
        int master = 1;

        // Display master node
        System.out.println(

            "\nMaster Node: Node "
            + master
        );

        // Variable to store total time
        int sum = 0;

        // Calculate total clock time
        for (int i = 0; i < n; i++) {

            // Add clock values
            sum += clock[i];
        }

        // Calculate average time
        int avg = sum / n;

        // Display average time
        System.out.println(

            "\nAverage Time = "
            + avg
        );

        // Start synchronization
        System.out.println(

            "\nClock Synchronization:"
        );

        // Synchronize all clocks
        for (int i = 0; i < n; i++) {

            // Calculate adjustment value
            int offset =
                avg - clock[i];

            // Display adjustment
            System.out.println(

                "Node "
                + (i + 1)

                + " adjusted by "
                + offset
            );

            // Update clock value
            clock[i] =
                clock[i] + offset;
        }

        // Display synchronized clocks
        System.out.println(

            "\nSynchronized Clock Values:"
        );

        // Traverse all synchronized clocks
        for (int i = 0; i < n; i++) {

            // Print synchronized clock
            System.out.println(

                "Node "
                + (i + 1)

                + " = "
                + clock[i]
            );
        }

        // Close scanner
        sc.close();
    }
}


