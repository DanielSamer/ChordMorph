import java.util.*;

public class ChordScaleConverter {

    // Chromatic scale for reference
    private static final String[] CHROMATIC_SCALE = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    // Map to store scale roots and their positions in the chromatic scale
    private static final Map<String, Integer> SCALE_POSITIONS = new HashMap<>();
    //List of Valid Sclaes
    private static final Set<String> VALID_SCALES = Set.of(
        "C Major", "C# Major", "D Major", "D# Major", "E Major", "F Major", "F# Major", "G Major", "G# Major", "A Major", "A# Major", "B Major",
        "C Minor", "C# Minor", "D Minor", "D# Minor", "E Minor", "F Minor", "F# Minor", "G Minor", "G# Minor", "A Minor", "A# Minor", "B Minor"
    );

    static {
        // Initialize scale positions
        for (int i = 0; i < CHROMATIC_SCALE.length; i++) {
            SCALE_POSITIONS.put(CHROMATIC_SCALE[i], i);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //scanner used to read input from the user
        String[] chords;
        // Input the chords
        do{
            System.out.print("Enter the chords (separated by spaces, e.g., C G Am F): ");
            chords = scanner.nextLine().split(" ");
        } while (!areChordsValid(chords)); //helper method 2  

        //Input the source and target scales
        String sourceScale;
        do {
            System.out.print("Enter the source scale (e.g., C Major): ");
            sourceScale = scanner.nextLine().trim(); //trim() removes leading and trailing whitespaces
        } while (!isScaleValid(sourceScale)); //helper method 3
        
        String targetScale;
        do {
            System.out.print("Enter the target scale (e.g., D Major): ");
            targetScale = scanner.nextLine().trim();
        } while (!isScaleValid(targetScale)); //helper method 3
        
        
        //Getting the root of the source and target scales
        String sourceRoot = sourceScale.split(" ")[0]; //split() splits the string into an array of substrings -- G Major -> [G, Major] -> G (E7na 3aizin el G)
        String targetRoot = targetScale.split(" ")[0];

        //Check if the source and target scales are the same
        if (sourceRoot.equals(targetRoot)) {
            System.out.println("The source and target scales are the same. No transposition needed.");
            System.out.println("Transposed chords: " + Arrays.toString(chords));
            scanner.close();
            return;
        }
        //Check if the source and target scales are valid
        if (!SCALE_POSITIONS.containsKey(sourceRoot) || !SCALE_POSITIONS.containsKey(targetRoot)) {
            System.out.println("Invalid scale name(s). Please enter a valid scale name.");
            scanner.close();
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
                scanner.close();
                return;
            }

        }
        // Output the transposed chords 
        System.out.println("Converted chords: " + String.join(" ", transposedChords));
        scanner.close();
        
    }

   //Helper method, gets the Root note from the chord
    private static String extractRootNote(String chord){
        if (chord.length() >=2 && (chord.charAt(1) == '#')){
            return chord.substring(0,2);
        }
        return chord.substring(0,1);
    }

    //Helper method 2, to check if chords arve valid 
    private static boolean areChordsValid(String[] chords) {
        for (int i = 0; i < chords.length; i++) {
            String chord = chords[i];
            if (!SCALE_POSITIONS.containsKey(extractRootNote(chord))) {
                System.out.println("Invalid chord: " + chord);
                return false;
            }
            if (chord.length() > 1 && chord.charAt(1)!= 'm' && chord.charAt(1) != '#') {  
                System.out.println("Invalid chord: " + chord);
                return false;
            }
        }
        return true;
    }

    //Helper method 3, to check if the scale is valid
    private static boolean isScaleValid(String scale) {
        if (!VALID_SCALES.contains(scale)) {
            System.out.println("Invalid scale: " + scale);
            return false;
        }
        return true; 
    }
}