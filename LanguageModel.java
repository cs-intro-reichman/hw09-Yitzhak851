import java.util.HashMap;
import java.util.Random;

public class LanguageModel {
    // The map of this model, Maps windows to lists of charachter data objects.
    HashMap<String, List> CharDataMap;
    int windowLength; // The window length used in this model.
    public Random randomGenerator; // The random number generator used by this model.

    /**
     * Constructs a language model with the given window length and a given
     * seed value. Generating texts from this model multiple times with the
     * same seed value will produce the same random texts. Good for debugging.
     */
    public LanguageModel(int windowLength, int seed) {
        this.windowLength = windowLength;
        randomGenerator = new Random(seed);
        CharDataMap = new HashMap<String, List>();
    }

    /**
     * Constructs a language model with the given window length.
     * Generating texts from this model multiple times will produce
     * different random texts. Good for production.
     */
    public LanguageModel(int windowLength) {
        this.windowLength = windowLength;
        randomGenerator = new Random();
        CharDataMap = new HashMap<String, List>();
    }

    /** Builds a language model from the text in the given file (the corpus). */
    public void train(String fileName) {
        String window = "";
        char c;
        In in = new In(fileName);
        // Read the first windowLength characters from the file.
        while (!in.isEmpty()) {
            c = in.readChar(); // Read a character from the file.
            // If the window is not long enough, add the character to the window.
            if (window.length() < windowLength) {
                window += c; // Add the character to the window.
                // If the window is long enough, add the character to the list of the window in
                // the map.
            } else {
                // If the window is not a key in the map, add it as a key with an empty list as
                // a value.
                if (!CharDataMap.containsKey(window)) {
                    // Add the window as a key to the map with an empty list as a value.
                    CharDataMap.put(window, new List());
                }
                // Add the character to the list of the window in the map.
                CharDataMap.get(window).addFirst(c);
                // Update the window by removing the first character and adding the new
                // character.
                window = window.substring(1) + c;
            } // Read the next character from the file.
        }
        // Close the file.
    }

    /**
     * This void method receives a List object as a parameter, and computes and sets
     * the (p and cp fields) characters in the given list of all the list elements.
     */
    public void calculateProbabilities(List probs) {
        double sum = 0;
        Node current = probs.first;
        while (current != null) {
            sum += current.cd.count;
            current = current.next;
        }
        current = probs.first;
        while (current != null) {
            current.cd.p = current.cd.count / sum;
            current.cd.cp = current.cd.p + (current.next != null ? current.next.cd.cp : 0);
            current = current.next;
        }
    }

    // Returns a random character from the given probabilities list.
    public char getRandomChar(List probs) {
        double r = randomGenerator.nextDouble();
        Node current = probs.first;
        while (current != null) {
            if (r < current.cd.cp)
                return current.cd.chr;
            current = current.next;
        }
        return probs.first.cd.chr;
    }

    /**
     * Generates a random text, based on the probabilities that were learned during
     * training.
     * 
     * @param initialText - text to start with. If initialText's last substring
     *                    of size numberOfLetters doesn't appear as a key in
     *                    Map, we generate no text
     *                    and return only the initial text.
     * @param textLength  - the size of text to generate
     * @return - the generated text
     */
    public String generate(String initialText, int textLength) {
        String window = initialText.substring(initialText.length() - windowLength);
        StringBuilder str = new StringBuilder(initialText);
        for (int i = 0; i < textLength; i++) {
            if (!CharDataMap.containsKey(window))
                return str.toString();
            List probs = CharDataMap.get(window);
            calculateProbabilities(probs);
            char c = getRandomChar(probs);
            str.append(c);
            window = window.substring(1) + c;
        }
        return str.toString();
    }

    /** Returns a string representing the map of this language model. */
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String key : CharDataMap.keySet()) {
            List keyProbs = CharDataMap.get(key);
            str.append(key + " : " + keyProbs + "\n");
        }
        return str.toString();
    }

}
