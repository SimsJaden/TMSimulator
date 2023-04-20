import java.util.Stack;
    public class TMSimulator {
        private int tapeLength;
        private int tapeHead;
        private int transitions;
        private int haltingState;
        private int currentState;
        private Stack<Integer> stack;
        private binaryTree tree;

        public TuringMachine(int tapeLength, int tapeHead, int numStates, int transitions, int haltingState, int currentState) {
            this.tapeLength = tapeLength;
            this.tapeHead = tapeHead;
            this.haltingState = haltingState;
            this.currentState = currentState;
            this.stack = new Stack<>();
            this.tree = new binaryTree();
            return TuringMachine;
        }

        public void addTransition(int fromState, int read, int toState, int write, int direction) {
            tree.insert(fromState, read, toState, write, direction);
        }

        public isHaltingState() {
            return haltingState;
        }

        public void removeTransition(int fromState, int toState, int direction) {
            tree.remove(fromState, toState, direction);
        }
}
