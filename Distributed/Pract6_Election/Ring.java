import java.util.Scanner;

// Class representing each process/node
class customProcess {
    int id;  // Process ID
    String status;     // Process status (active/inactive)

    // Constructor
    customProcess(int id) {
        this.id = id;  // Assign process ID
        status = "active"; // Initially all processes are active
    }
}

// Main Ring Election Algorithm class
public class Ring {
    Scanner sc;      // Scanner object for input
    customProcess[] processes;    // Array of processes
    int n;    // Total number of processes

    // Constructor
    public Ring() {
        sc = new Scanner(System.in);        // Initialize scanner
    }

    // Create all processes
    public void createProcesses() {

        // Input total processes
        System.out.print("Enter total number of processes: ");
        n = sc.nextInt();
        
        processes = new customProcess[n];         // Create process array

        // Create processes
        for (int i = 0; i < n; i++) {
            processes[i] = new customProcess(i);  // Process IDs: 0,1,2...
        }
    }

    // Main election logic
    public void performElection() {
        int failedProcess = getMaxProcess();      // Find highest active process

        // Display failed process
        System.out.println("\nProcess " + failedProcess + " fails");

        // Make coordinator inactive
        processes[failedProcess].status = "inactive";

        int initiator = 0;        // Election starts from Process 0

        String message = "";  // Election message string

        // Current process
        int current = initiator;

        // Circulate election message in ring
        do {

            // Add current process to message
            message += current + " ";

            // Find next process in ring
            int next =(current + 1) % n;

            // Skip inactive processes
            while ( processes[next].status.equals("inactive")
            ) { 
            	next = (next + 1) % n; 
            	}

            // Pass election message
            System.out.println( "\nProcess " + current + " passes Election(" + message.trim() + ") to Process "+ next );

            // Move to next process
            current = next;

        }

        // Continue until message returns
        // to initiator
        while (current != initiator);

        // Find highest active process
        int coordinator = getMaxProcess();

        // Display new coordinator
        System.out.println("\nProcess " + coordinator + " becomes the coordinator.");
    }

    // Find highest active process
    public int getMaxProcess() {

        // Store highest process ID
        int maxId = -1;

        // Traverse all processes
        for (int i = 0; i < n; i++) {

            // Check active process
            // and highest ID
            if (processes[i].status.equals("active") && processes[i].id > maxId) {

                // Update highest ID
                maxId = processes[i].id;
            }
        }
        return maxId;        // Return highest active process
    }

    // Main method
    public static void main(String[] args) {

        Ring ring = new Ring();        // Create Ring object

        ring.createProcesses();        // Create processes

        ring.performElection();        // Start election
    }
}