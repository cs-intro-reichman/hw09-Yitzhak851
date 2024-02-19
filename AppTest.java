public class AppTest {
    public static void main(String[] args) {
        String word = "computer_science";
        List list = new List();
        for (int i = 0; i < word.length(); i++) {
            list.update(word.charAt(word.length() - 1 - i));
        }
        System.out.println(list.toString());
    }

    // Test method for the calculateProbabilities() method
    public static boolean testCalculateProbabilities() {
        LanguageModel model = new LanguageModel(3);
        String word = "computer_science";
        List list = new List();
        for (int i = 0; i < word.length(); i++) {
            list.update(word.charAt(word.length() - 1 - i));
        }
        model.calculateProbabilities(list);

        String resString = "((o 1 0.0625 0.0625) (m 1 0.0625 0.125) (p 1 0.0625 0.1875) (u 1 0.0625 0.25) (t 1 0.0625 0.3125) (r 1 0.0625 0.375) (_ 1 0.0625 0.4375) (s 1 0.0625 0.5) (i 1 0.0625 0.5625) (n 1 0.0625 0.625) (c 3 0.1875 0.8125) (e 3 0.1875 1.0))";
        boolean res = list.toString().equals(resString);
        if (!res) {
            System.out.println("Expected: " + resString);
            System.out.println("Actual: " + list.toString());
        }
        return res;
    }

}

