import java.util.HashMap;
import java.util.Random;

public class LanguageModel {
    // The map of this model, Maps windows to lists of charachter data objects.
    HashMap<String, List> CharDataMap;
    int windowLength; // The window length used in this model.
    private Random randomGenerator; // The random number generator used by this model.

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
        //
        for (int i = 0; i < windowLength; i++) {
            char tempChar = in.readChar();
            window += tempChar;
        }
        // start the processes
        while (!in.isEmpty()) {
            c = in.readChar();
            List probs = CharDataMap.get(window);
            if (probs == null) {
                probs = new List();
                CharDataMap.put(window, probs);
            }
            probs.update(c);
            window = (window + c).substring(1);
        }
        for (List probs : CharDataMap.values()) {
            calculateProbabilities(probs);
        }
    }

    /**
     * This void method receives a List object as a parameter, and computes and sets
     * the (p and cp fields) characters in the given list of all the list elements.
     */
    public void calculateProbabilities(List probs) {
        int total = 0;
        for (int i = 0; i < probs.getSize(); i++) {
            total += probs.listIterator(i).current.cd.count;
        }
        for (int i = 0; i < probs.getSize(); i++) {
            CharData currentChar = probs.listIterator(i).current.cd;
            CharData previousChar = (i == 0) ? null : probs.listIterator(i - 1).current.cd;
            currentChar.p = (double) currentChar.count / total;
            if (i == 0) {
                currentChar.cp = currentChar.p;
            } else {
                currentChar.cp = currentChar.p + previousChar.cp;
            }
        }
    }

    // Returns a random character from the given probabilities list.
    public char getRandomChar(List probs) {
        double r = randomGenerator.nextDouble();
        int index = 0;

        while (probs.listIterator(index).current.cd.cp < r) {
            index++;
        }
        return probs.get(index).chr;
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
        if (initialText.length() < windowLength) {
            return initialText;
        }
        String window = initialText.substring(initialText.length() - windowLength);
        String generatedText = window;

        int numberOfLetters = textLength + windowLength;
        while ((generatedText.length() < numberOfLetters)) {
            List currList = CharDataMap.get(window);

            if (currList == null) {
                break;
            }
            generatedText += getRandomChar(currList);

            window = generatedText.substring(generatedText.length() - windowLength);
        }
        return generatedText;
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
