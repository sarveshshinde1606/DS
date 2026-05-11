import java.util.Scanner;

// Process class represents each process/node
class Process {

    // Process ID
    public int id;

    // Process status (active/inactive)
    public String status;

    // Constructor
    public Process(int id) {

        // Assign process ID
        this.id = id;

        // Initially all processes are active
        this.status = "active";
    }
}

// Main Bully Algorithm class
public class Bully {

    // Scanner object for input
    Scanner sc;

    // Array of processes
    Process[] processes;

    // Total number of processes
    int n;

    // Constructor
    public Bully() {

        // Initialize scanner
        sc = new Scanner(System.in);
    }

    // Create processes
    public void ring() {

        // Input total processes
        System.out.print(
            "Enter total number of processes: "
        );

        n = sc.nextInt();

        // Create process array
        processes = new Process[n];

        // Create all processes
        for (int i = 0; i < n; i++) {

            // Process IDs: 0,1,2...
            processes[i] = new Process(i);
        }
    }

    // Main election logic
    public void performElection() {

        try {

            // Delay for simulation
            Thread.sleep(1000);

        } catch (InterruptedException e) {

            // Print exception if error occurs
            e.printStackTrace();
        }

        // Highest active process fails
        System.out.println(
            "Process "
            + processes[getMaxValue()].id
            + " fails"
        );

        // Make coordinator inactive
        processes[getMaxValue()].status =
            "Inactive";

        // Election starts from Process 0
        int idOfInitiator = 0;

        // Election running status
        boolean overStatus = true;

        // Continue election until coordinator selected
        while (overStatus) {

            // Check if higher process exists
            boolean higherProcesses = false;

            System.out.println();

            // Send election message to higher processes
            for (int i = idOfInitiator + 1;
                 i < n;
                 i++) {

                // Check active process
                if (processes[i].status
                        == "active") {

                    System.out.println(

                        "Process "
                        + idOfInitiator

                        + " Passes Election("
                        + idOfInitiator

                        + ") message to Process "
                        + i
                    );

                    // Higher process found
                    higherProcesses = true;
                }
            }

            // If higher active process exists
            if (higherProcesses) {

                System.out.println();

                // Higher processes send OK message
                for (int i = idOfInitiator + 1;
                     i < n;
                     i++) {

                    // Check active process
                    if (processes[i].status
                            == "active") {

                        System.out.println(

                            "Process "
                            + i

                            + " passes Ok("
                            + i

                            + ") message to Process "
                            + idOfInitiator
                        );
                    }
                }

                // Next process becomes initiator
                idOfInitiator++;
            }

            // No higher process exists
            else {

                // Highest active process becomes coordinator
                int coord =
                    processes[getMaxValue()].id;

                System.out.println(

                    "Finally Process "
                    + coord
                    + " Becomes Coordinator"
                );

                // Coordinator sends coordinator message
                for (int i = coord - 1;
                     i >= 0;
                     i--) {

                    // Check active process
                    if (processes[i].status
                            == "active") {

                        System.out.println(

                            "Process "
                            + coord

                            + " passes Coordinator("
                            + coord

                            + ") message to Process "
                            + i
                        );
                    }
                }

                // Election completed
                System.out.println(
                    "\nEnd of Election"
                );

                // Stop loop
                overStatus = false;

                break;
            }
        }
    }

    // Find highest active process
    public int getMaxValue() {

        // Store maximum ID
        int mxId = -99;

        // Store index of maximum ID
        int mxIdIndex = 0;

        // Traverse all processes
        for (int i = 0;
             i < processes.length;
             i++) {

            // Check active process
            // and highest ID
            if (processes[i].status
                    == "active"

                    &&

                    processes[i].id > mxId) {

                // Update maximum ID
                mxId = processes[i].id;

                // Update index
                mxIdIndex = i;
            }
        }

        // Return highest process index
        return mxIdIndex;
    }

    // Main method
    public static void main(String[] args) {

        // Create Bully object
        Bully bully = new Bully();

        // Create processes
        bully.ring();

        // Start election
        bully.performElection();
    }
}