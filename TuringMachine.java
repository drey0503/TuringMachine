import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TuringMachine {
    private Map<String, Transition> transitions;
    private String currentState;
    private StringBuilder tape;
    private int headPosition;

    public TuringMachine(String initialState, String input) {
        transitions = new HashMap<>();
        currentState = initialState;
        tape = new StringBuilder(input);
        headPosition = 0;
    }

    public void addTransition(String state, char readSymbol, String nextState, char writeSymbol, char direction) {
        transitions.put(state + readSymbol, new Transition(nextState, writeSymbol, direction));
    }

    public void run() {
        while (transitions.containsKey(currentState + tape.charAt(headPosition))) {
            Transition transition = transitions.get(currentState + tape.charAt(headPosition));
            currentState = transition.getNextState();
            tape.setCharAt(headPosition, transition.getWriteSymbol());

            if (transition.getDirection() == 'R') {
                headPosition++;
                if (headPosition == tape.length()) {
                    tape.append('_');
                }
            } else if (transition.getDirection() == 'L') {
                if (headPosition > 0) {
                    headPosition--;
                } else {
                    tape.insert(0, '_');
                }
            }
        }
    }

    public String getTape() {
        return tape.toString();
    }

    private static class Transition {
        private String nextState;
        private char writeSymbol;
        private char direction;

        public Transition(String nextState, char writeSymbol, char direction) {
            this.nextState = nextState;
            this.writeSymbol = writeSymbol;
            this.direction = direction;
        }

        public String getNextState() {
            return nextState;
        }

        public char getWriteSymbol() {
            return writeSymbol;
        }

        public char getDirection() {
            return direction;
        }
    }

    public static void main(String[] args) {
        //Enter in text file you want to parse through
        String inputFile = "wwr_input.txt";
        //this is where the results will be printed out to
        String outputFile = "output.txt";

        // Read file content
        StringBuilder input = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                input.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a Turing machine
       TuringMachine tm = new TuringMachine("q0", input.toString());

        // Define transitions
        tm.addTransition("q0", 'A', "q1", 'A', 'R');
        tm.addTransition("q1", 'B', "q2", 'C', 'R');
        tm.addTransition("q1", 'A', "q0", 'A', 'R');
        tm.addTransition("q2", 'A', "q0", 'A', 'L');
        tm.addTransition("q2", 'B', "q0", 'B', 'L');
        tm.addTransition("q2", '_', "qHalt", '_', 'N');

        // Run the Turing machine
        tm.run();

        // Write output to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(tm.getTape());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File parsing and transformation complete.");
    }
}
