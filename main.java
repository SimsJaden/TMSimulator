import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]); //TODO change this
        Scanner scan = new Scanner(file);
        String[] fileString = scan.nextLine().split(" ");

        int tapeLength = Integer.parseInt(fileString[0]);
        int tapeHead = Integer.parseInt(fileString[1]);
        int numStates = Integer.parseInt(fileString[2]);
        int numTransitions = Integer.parseInt(fileString[3]);
        int acceptState = Integer.parseInt(fileString[4]);
        int rejectState = Integer.parseInt(fileString[5]);
        int currentState = 0;

//        TuringMachine turing = new TuringMachine(tapeLength, tapeHead, haltingState, currentState);
//        while (scan.hasNextLine()) {
//            String[] line = scan.nextLine().split(" ");
//            int fromState = Integer.parseInt(line[0]);
//            int read = Integer.parseInt(line[1]);
//            int toState = Integer.parseInt(line[2]);
//            int write = Integer.parseInt(line[3]);
//            int direction = Integer.parseInt(line[4]);
//            turing.addTransition(fromState, read, toState, write, direction);
//        }
//        turing.run();
//        sc.close();
//    }

    }
