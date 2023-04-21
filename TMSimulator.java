import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TMSimulator {
    private int totalStates;
    private int numSymbols;
    private String inputString;
    private int currState;
    private LinkedList<Integer> tape;

    public TMSimulator(String inputFilePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            totalStates = Integer.parseInt(br.readLine());
            numSymbols = Integer.parseInt(br.readLine());
            currState = 0;
            tape = new LinkedList<>();
            String transitionLine;
            while ((transitionLine = br.readLine()) != null) {
                String[] transition = transitionLine.split(",");
                int nextState = Integer.parseInt(transition[0]);
                int writeSymbol = Integer.parseInt(transition[1]);
                char move = transition[2].charAt(0);
                transitions.put(new Transition(currState, tape.isEmpty() ? 0 : tape.getLast(), nextState),
                        new Operation(writeSymbol, move));
                currState++;
            }
            inputString = br.readLine();
            br.close();
            for (char c : inputString.toCharArray()) {
                tape.addLast(Character.getNumericValue(c));
            }
            tape.addFirst(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Transition currTransition = new Transition(0, tape.isEmpty() ? 0 : tape.getLast(), 0);
        while (transitions.containsKey(currTransition)) {
            Operation operation = transitions.get(currTransition);
            tape.removeLast();
            tape.addLast(operation.writeSymbol);
            if (operation.move == 'L') {
                if (tape.getFirst() == 0) {
                    tape.addFirst(0);
                }
                tape.removeFirst();
            } else if (operation.move == 'R') {
                if (tape.getLast() == 0) {
                    tape.addLast(0);
                }
                tape.addLast(tape.removeFirst());
            }
            currState = currTransition.nextState;
            currTransition = new Transition(currState, tape.getLast(), 0);
        }
        for (int i : tape) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TMSimulator simulator = new TMSimulator(args[0]);
        simulator.run();
    }

    private static class Transition {
        private int currState;
        private int inputSymbol;
        private int nextState;

        public Transition(int currState, int inputSymbol, int nextState) {
            this.currState = currState;
            this.inputSymbol = inputSymbol;
            this.nextState = nextState;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Transition that = (Transition) o;
            return currState == that.currState && inputSymbol == that.inputSymbol && nextState == that.nextState;
        }

        @Override
        public int hashCode() {
            return Objects.hash(currState, inputSymbol, nextState);
        }
    }

    private static class Operation {
        private int writeSymbol;
        private char move;

        public Operation(int writeSymbol, char move) {
            this.writeSymbol = writeSymbol;
            this.move = move;
        }
    }

    private Map<Transition, Operation> transitions = new HashMap<>();
}
