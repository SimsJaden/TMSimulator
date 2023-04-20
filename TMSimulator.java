import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TMSimulator {
    private static int numStates; // Total number of states in the TM
    private static int[][] transitionTable; // Transition function
    private static int[] tape; // The tape

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java TMSimulator <input_file>");
            System.exit(1);
        }

        String inputFile = args[0];
        try {
            readInput(inputFile);
            simulate();
            printTape();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void readInput(String inputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        // Read the total number of states in the TM
        numStates = Integer.parseInt(reader.readLine());

        // Read the number of symbols in Σ
        // Number of symbols in Σ
        int numSymbols = Integer.parseInt(reader.readLine());

        // Initialize the transition table
        transitionTable = new int[numStates][numSymbols + 1];

        // Read the transition function
        for (int i = 0; i < numStates * (numSymbols + 1); i++) {
            String[] tokens = reader.readLine().split(",");
            int state = Integer.parseInt(tokens[0]);
            int symbol = Integer.parseInt(tokens[1]);
            int nextState = Integer.parseInt(tokens[2]);
            int writeSymbol = Integer.parseInt(tokens[3]);
            int move = tokens[4].equals("L") ? -1 : 1;
            transitionTable[state][symbol] = nextState << 16 | writeSymbol << 8 | move;
        }

        // Read the input string
        String inputString = reader.readLine();
        tape = new int[inputString.length() + 2];
        for (int i = 0; i < inputString.length(); i++) {
            tape[i + 1] = inputString.charAt(i) - '0';
        }

        reader.close();
    }

    private static void simulate() {
        // The position of the tape head
        int headPosition = 1; // The tape head starts at position 1
        // The current state of the TM
        int currentState = 0; // The TM starts at state 0

        while (currentState != numStates - 1) { // Stop when the TM reaches the halting state
            int symbol = tape[headPosition];
            int transition = transitionTable[currentState][symbol];
            int nextState = transition >> 16;
            int writeSymbol = (transition >> 8) & 0xff;
            int move = transition & 0xff;

            tape[headPosition] = writeSymbol;
            headPosition += move;
            currentState = nextState;
        }
    }

    private static void printTape() {
        for (int i = 1; i < tape.length - 1; i++) {
            System.out.print(tape[i]);
        }
        System.out.println();
    }
}
