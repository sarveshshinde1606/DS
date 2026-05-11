import java.util.*;

// Process class represents each process/node in ring
class Process extends Thread {

    int id;                 // Process ID
    TokenRing ring;         // Shared TokenRing object

    // Constructor
    Process(int id, TokenRing ring) {

        this.id = id;       // Assign process ID
        this.ring = ring;   // Assign shared ring
    }

    // Thread execution starts here
    public void run() {

        // Infinite loop for continuous token passing
        while (true) {

            // Only one process can access token at a time
            synchronized (ring) {

                // Wait until current process gets token
                while (ring.token != id) {

                    try {

                        // Process waits if token not available
                        ring.wait();

                    } catch (Exception e) {}
                }

                // Critical Section starts
                System.out.println(
                    "\nProcess " + id + " ENTERS CS"
                );

                try {

                    // Hold critical section for 2 seconds
                    Thread.sleep(2000);

                } catch (Exception e) {}

                // Process exits critical section
                System.out.println(
                    "Process " + id + " EXITS CS"
                );

                // Pass token to next process in ring
                ring.token = (id + 1) % ring.n;

                // Display token passing
                System.out.println(
                    "Token passed to Process "
                    + ring.token
                );

                // Wake all waiting processes
                ring.notifyAll();
            }
        }
    }
}

// Main TokenRing class
public class TokenRing {

    int n;          // Total number of processes

    int token = 0;  // Initially token with Process 0

    // Constructor
    TokenRing(int n) {

        // Assign total processes
        this.n = n;
    }

    // Main method
    public static void main(String[] args) {

        // Scanner for input
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print(
            "Enter number of processes: "
        );

        int n = sc.nextInt();

        // Create shared TokenRing object
        TokenRing ring = new TokenRing(n);

        // Create and start all processes
        for (int i = 0; i < n; i++) {

            new Process(i, ring).start();
        }
    }
}