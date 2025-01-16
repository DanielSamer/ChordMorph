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

        // Input the chords
        System.out.print("Enter the chords (separated by spaces, e.g., C G Am F): ");
        String[] chords = scanner.nextLine().split(" ");

        //Input the source and target scales
        System.out.print("Enter the source scale (e.g., C major): ");
        String sourceScale = scanner.nextLine().trim(); //trim() removes leading and trailing whitespaces
        
        System.out.print("Enter the target scale (e.g., D major): ");
        String targetScale = scanner.nextLine().trim();
        
        //Getting the root of the source and target scales
        String sourceRoot = sourceScale.split(" ")[0]; //split() splits the string into an array of substrings -- G Major -> [G, Major] -> G (E7na 3aizin el G)
        String targetRoot = targetScale.split(" ")[0];

        //Check if the source and target scales are the same
        if (sourceRoot.equals(targetRoot)) {
            System.out.println("The source and target scales are the same. No transposition needed.");
            System.out.println("Transposed chords: " + Arrays.toString(chords));
            return;
        }
        //Check if the source and target scales are valid
        if (!SCALE_POSITIONS.containsKey(sourceRoot) || !SCALE_POSITIONS.containsKey(targetRoot)) {
            System.out.println("Invalid scale name(s). Please enter a valid scale name.");
            return;
        }

        //Calculate the Shift 
        int sourceRootPosition = SCALE_POSITIONS.get(sourceRoot);
        int targetRootPosition = SCALE_POSITIONS.get(targetRoot);
        int shift = (targetRootPosition - sourceRootPosition + 12) % 12;

        //Converting the chords
        List<String> transposedChords = new ArrayList<>();
        for (int i = 0; i < chords.length; i++) {
            String chord = chords[i];
            String PreChord = extractRootNote(chord);
            String suffix = chord.substring(PreChord.length());
            if (SCALE_POSITIONS.containsKey(PreChord)) {
                int chordPosition = SCALE_POSITIONS.get(PreChord);
                int transposedPosition = (chordPosition + shift) % 12;
                transposedChords.add(CHROMATIC_SCALE[transposedPosition] + suffix );
            } else {
                System.out.println("Invalid chord: " + chord);
                return;
            }
        }
        System.out.println("Converted chords: " + String.join(" ", transposedChords));

        // Output the transposed chords 
    }

   //Helper method 
    private static String extractRootNote(String chord){
        if (chord.length() >=2 && (chord.charAt(1) == '#')){
            return chord.substring(0,2);
        }
        return chord.substring(0,1);
    }

}