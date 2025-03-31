import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TM {
   
    //DISCLAIMER WHEN RUNNING PROGRAM
    /*
     * javac TM.java to generate executable file
     * then 
     * java TM tm_definition.txt tm_input.txt to generate input tm_input is suppose to be the name of the text file you want to pass through
     */
   

    private static String readMachineType( String tm_input) throws IOException {
        // Read the first line of tm_input exectuable file to determine machine type 
        try (BufferedReader br = new BufferedReader(new FileReader(tm_input))) {
            return br.readLine().trim();
        }
        
    }

    private static void performRecognizer(String tm_input) throws IOException {
        // Implement recognizer logic based on the TM definition and input
        // Read tm_Input and perform recognition
        // Output 'accept' or 'reject' for each input
        // This part needs to handle state transitions, tape operations, etc.
        // ...



        //this just prints out recognizer to make sure the pathway to this method works
        String recognizer = "Recognizer";
       
        System.out.println(recognizer);

    }

    private static void performTransducer(String tm_input) throws IOException {
        // Implement transducer logic based on the TM definition and input
        // Read tm_Input and perform transduction
        // Output the transducer result based on the specified convention
        // This part needs to handle state transitions, tape operations, etc.
        // ...



    
        //this just prints out recognizer to make sure the pathway to this method works

        String transducer = "Transducer";
        
        System.out.println(transducer);

    }
    
     public static void main(String[] args) {
        //only allows one file to be passed through
        if (args.length != 2) {
            System.err.println("There is more than two arguments (tm_definition tm_input)");
            return;
        }
        String tm_definition = args[0];
        String tm_input = args[1];  //tm_input input for regular text files so string reader and writer will be required, will need to output to a makefile
 
 
        //machineType should read either a recognizer or transducer so 2 types of machines essentially
        try {
            String machineType = readMachineType(tm_input);

            if (machineType.equalsIgnoreCase("Recognizer")) {
                performRecognizer(tm_input);
            } else if (machineType.equalsIgnoreCase("Transducer")) {
                performTransducer(tm_input);
            } else {
                System.err.println("Invalid machine type"); 
                //if the machine type isn't recognizer or transducer as in the first lines of the text files, throw error
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
       
       //printing out if its a recognizer or a transducer
        
        
    
    }
}
