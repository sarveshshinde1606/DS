// Import MPI package
import mpi.*;

// Main class
public class DistributedSum {

    // Main method
    public static void main(String[] args)
            throws MPIException
    {

        // Initialize MPI environment
        MPI.Init(args);

        // Get process rank (process ID)
        int rank = MPI.COMM_WORLD.Rank();

        // Get total number of processes
        int size = MPI.COMM_WORLD.Size();

        // Input array
        int[] array = {1, 2, 3, 4, 5,
            6, 7, 8, 9, 10
        };

        // Total array size
        int n = array.length;

        // Number of elements for each process
        int local_n = n / size;

        // Remaining elements after division
        int remainder = n % size;

        // Create local array for each process
        int[] local_array =
            new int[
                local_n
                + (rank < remainder ? 1 : 0)
            ];

        // Calculate starting index
        int offset =
            rank * local_n
            + Math.min(rank, remainder);

        // Distribute array elements
        for (int i = 0;
             i < local_array.length;
             i++)
        {

            local_array[i] =
                array[offset + i];
        }

        // Store local sum
        int local_sum = 0;

        // Calculate local sum
        for (int i = 0;
             i < local_array.length;
             i++)
        {

            local_sum +=
                local_array[i];
        }

        // Array to store sums from all processes
        int[] global_sums =
            new int[size];

        // Collect all local sums
        MPI.COMM_WORLD.Allgather(

            // Send local sum
            new int[]{local_sum},

            // Send offset
            0,

            // Send count
            1,

            // Send datatype
            MPI.INT,

            // Receive array
            global_sums,

            // Receive offset
            0,

            // Receive count
            1,

            // Receive datatype
            MPI.INT
        );

        // Only process 0 prints output
        if (rank == 0)
        {

            System.out.println(
                "Number of Processes Entered: "
                + size
            );

            System.out.println(
                "\nIntermediate Sums:"
            );

            // Store final sum
            int sum = 0;

            // Display local sums
            for (int i = 0;
                 i < size;
                 i++)
            {

                // Add local sums
                sum += global_sums[i];

                System.out.println(
                    "PROCESS RANK: " + i
                );

                System.out.println(
                    "Process "
                    + i
                    + ": "
                    + global_sums[i]
                );
            }

            // Display final sum
            System.out.println(
                "\nTotal Sum: "
                + sum
            );
        }

        // End MPI environment
        MPI.Finalize();
    }
}