import java.util.*;

public class ChordScaleConverter {

    // Chromatic scale for reference
    private static final String[] CHROMATIC_SCALE = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    // Map to store scale roots and their positions in the chromatic scale
    private static final Map<String, Integer> SCALE_POSITIONS = new HashMap<>();

    static {
        // Initialize scale positions
        for (int i = 0; i < CHROMATIC_SCALE.length; i++) {
            SCALE_POSITIONS.put(CHROMATIC_SCALE[i], i);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //scanner used to read input from the user

        // Step 1: Input the chords
        System.out.print("Enter the chords (separated by spaces, e.g., C G Am F): ");
        String[] chords = scanner.nextLine().split(" ");

        // Step 2: Input the source and target scales
        System.out.print("Enter the source scale (e.g., C major): ");
        String sourceScale = scanner.nextLine().trim(); //trim() removes leading and trailing whitespaces

        System.out.print("Enter the target scale (e.g., D major): ");
        String targetScale = scanner.nextLine().trim();
        
        String sourceRoot = sourceScale.split(" ")[0]; //split() splits the string into an array of substrings -- G Major -> [G, Major] -> G (E7na 3aizin el G)
        String targetRoot = targetScale.split(" ")[0];

        if (sourceRoot.equals(targetRoot)) {
            System.out.println("The source and target scales are the same. No transposition needed.");
            System.out.println("Transposed chords: " + Arrays.toString(chords));
            return;
        }

        if (!SCALE_POSITIONS.containsKey(sourceRoot) || !SCALE_POSITIONS.containsKey(targetRoot)) {
            System.out.println("Invalid scale name(s). Please enter a valid scale name.");
            return;
        }
            
        
    }
}