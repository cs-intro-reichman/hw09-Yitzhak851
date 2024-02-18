
import java.util.Random;

public class AppTest {
    public static void main(String[] args) {
        System.out
                .println("===============================\n      Testing the classes\n===============================");
        // CharData
        System.out.println("================================\nTesting CharData.java\n================================");
        CharData cd1 = new CharData('c');
        System.out.println("command: CharData cd1 = new CharData('c');\noutput: " + cd1); // (c 1 0.0 0.0)
        CharData cd2 = new CharData('a');
        System.out.println("command: CharData cd2 = new CharData('a');\noutput: " + cd2); // (a 1 0.0 0.0)
        System.out.println("coomand: cd1.equals('c');\noutput: " + cd1.equals('c')); // logic test
        System.out.println("command: cd1.equals('a');\noutput: " + cd1.equals('a')); // logic test
        // Node
        System.out.println("================================\nTesting Node.java\n================================");
        Node n1 = new Node(cd1);
        System.out.println("command: Node n1 = new Node(cd1);\noutput: " + n1); // (c 1 0.0 0.0)
        Node n2 = new Node(cd2);
        System.out.println("command: Node n2 = new Node(cd2);\noutput: " + n2); // (a 1 0.0 0.0)
        // ListIterator
        System.out.println("===============================\nTesting ListIterator\n===============================");
        ListIterator listIt = new ListIterator(n1); // n1 is the first node in the list
        System.out.println("command: ListIterator listIt = new ListIterator(n1);");
        System.out.println("// n1 (is-a-Node) equals to object type CharData (c 1 0.0 0.0)");
        listIt.hasNext(); // true
        System.out.println("command: listIt.hasNext();\noutput: " + listIt.hasNext()); // true
        // List
        System.out.println("===============================\nTesting List\n===============================");
        System.out.println("====== List l1 ======");
        // L1
        List l1 = new List();
        System.out.println("command: List l1 = new List();\noutput: " + l1); // ()
        System.out.println("====== List.addFirst(char chr) ======");
        l1.addFirst('a');
        System.out.println("command: l1.addFirst('a');\noutput: " + l1); // (a 1 0.0 0.0)
        l1.addFirst('b');
        System.out.println("command: l1.addFirst('b');\noutput: " + l1); // (b 1 0.0 0.0) (a 1 0.0 0.0)
        l1.addFirst('c');
        System.out.println("command: l1.addFirst('c');\noutput: " + l1); // (c 1 0.0 0.0) (b 1 0.0 0.0) (a 1 0.0 0.0)
        // L2
        System.out.println("====== List l2 ======");
        List l2 = new List();
        System.out.println("command: List l2 = new List();\noutput: " + l2); // ()
        l2.addFirst('c');
        System.out.println("command: l2.addFirst('c');\noutput: " + l2); // (c 1 0.0 0.0)
        l2.addFirst('o');
        System.out.println("command: l2.addFirst('o');\noutput: " + l2); // (o 1 0.0 0.0) (c 1 0.0 0.0)
        l2.addFirst('m');
        System.out.println("command: l2.addFirst('m');\noutput: " + l2); // (m 1 0.0 0.0) (o 1 0.0 0.0) (c 1 0.0 0.0)
        l2.addFirst('m');
        System.out.println("command: l2.addFirst('m');\noutput: " + l2); // (m 1 0.0 0.0) (m 1 0.0 0.0) (o 1 0.0 0.0) (c
                                                                         // 1 0.0 0.0)
        l2.addFirst('i');
        System.out.println("command: l2.addFirst('i');\noutput: " + l2); // (i 1 0.0 0.0) (m 1 0.0 0.0) (m 1 0.0 0.0) (o
                                                                         // 1 0.0 0.0) (c 1 0.0 0.0)
        l2.addFirst('t');
        System.out.println("command: l2.addFirst('t');\noutput: " + l2); // (t 1 0.0 0.0) (i 1 0.0 0.0) (m 1 0.0 0.0) (m
                                                                         // 1 0.0 0.0) (o 1 0.0 0.0) (c 1 0.0 0.0)
        l2.addFirst('t');
        System.out.println("command: l2.addFirst('t');\noutput: " + l2); // (t 1 0.0 0.0) (t 1 0.0 0.0) (i 1 0.0 0.0) (m
                                                                         // 1 0.0 0.0) (m 1 0.0 0.0) (o 1 0.0 0.0) (c 1
                                                                         // 0.0 0.0)
        l2.addFirst('e');
        System.out.println("command: l2.addFirst('e');\noutput: " + l2); // (e 1 0.0 0.0) (t 1 0.0 0.0) (t 1 0.0 0.0) (i
                                                                         // 1 0.0 0.0) (m 1 0.0 0.0) (m 1 0.0 0.0) (o 1
                                                                         // 0.0 0.0) (c 1 0.0 0.0)
        l2.addFirst('_');
        System.out.println("command: l2.addFirst('_');\noutput: " + l2); // (_ 1 0.0 0.0) (e 1 0.0 0.0) (t 1 0.0 0.0) (t
                                                                         // 1 0.0 0.0) (i 1 0.0 0.0) (m 1 0.0 0.0) (m 1
                                                                         // 0.0 0.0) (o 1 0.0 0.0) (c 1 0.0 0.0)
        // equals
        System.out.println("====== List.equals(List list) ======");
        System.out.println("command: l1.equals(l2);\noutput: " + l1.equals(l2)); // false
        // List.indexOf
        System.out.println("====== List.indexOf(char chr) ======");
        System.out.println("command: l1.indexOf('c');\noutput: " + l1.indexOf('c')); // 0
        System.out.println("command: l1.indexOf('a');\noutput: " + l1.indexOf('a')); // 2
        // List.remove
        System.out.println("====== List.remove(char chr) ======");
        l1.remove('c');
        System.out.println("command: l1.remove('c');\noutput: " + l1); // (b 1 0.0 0.0) (a 1 0.0 0.0)
        l1.remove('a');
        System.out.println("command: l1.remove('a');\noutput: " + l1); // (b 1 0.0 0.0)
        // List.update
        System.out.println("====== List.update(char chr) ======");
        // print l1 before update
        System.out.println("command: l1;\noutput: " + l1); // (b 1 0.0 0.0)
        l1.update('b');
        System.out.println("command: l1.update('b');\noutput: " + l1); // (b 2 0.0 0.0)
        l1.update('a');
        System.out.println("command: l1.update('a');\noutput: " + l1); // (a 1 0.0 0.0) (b 2 0.0 0.0)
        // List.listIterator
        ListIterator listIt2 = l1.listIterator(1);
        System.out.println("command: ListIterator listIt2 = l1.listIterator(1);\noutput: " + listIt2); // (a 1 0.0 0.0)
                                                                                                       // (b 2 0.0 0.0)
        // LanguageModel
        System.out.println("===============================\nTesting LanguageModel\n===============================");
        LanguageModel lm = new LanguageModel(3);
        System.out.println("command: LanguageModel lm = new LanguageModel(3);\noutput: " + lm); // LanguageModel@15db9742
        // LanguageModel.calculateProbabilities
        System.out.println("====== LanguageModel.calculateProbabilities(List probs) ======");
        List l3 = new List();
        l3.addFirst('a');
        l3.addFirst('b');
        l3.addFirst('c');
        lm.calculateProbabilities(l3);
        System.out.println("command: lm.calculateProbabilities(l3);\noutput: " + l3); // (c 1 0.0 0.0) (b 1 0.0 0.0) (a
                                                                                      // 1 0.0 0.0)
        // LanguageModel.getRandomChar
        System.out.println("====== LanguageModel.getRandomChar(List probs) ======");
        // LanguageModel.randomGenerator
        Random r = new Random(1);
        System.out.println("command: Random r = new Random(1);\n");
        System.out.println("command: lm.getRandomChar(l3);\noutput: " + lm.getRandomChar(l3)); // c
        System.out.println("command: lm.getRandomChar(l3);\noutput: " + lm.getRandomChar(l3)); // c
        // LanguageModel.train
        System.out.println("====== LanguageModel.train(String fileName) ======");
        lm.train("test.txt");
        System.out.println("command: lm.train(\"test.txt\");"); // no output
        // LanguageModel.generate
        System.out.println("====== LanguageModel.generateText(int length) ======");
        // // LanguageModel.generate
        // int windowLength = Integer.parseInt(args[0]);
        // String initialText = args[1];
        // int generatedTextLength = Integer.parseInt(args[2]);
        // boolean randomGeneration = args[3].equals("random");
        // String fileName = args[4];
        // // Create a language model object with the given window length.
        // LanguageModel lm;
        // if (randomGeneration)
        //     lm = new LanguageModel(windowLength);
        // else
        //     lm = new LanguageModel(windowLength, 20);
        // // Train the language model with the given file.
        // lm.train(fileName);
        // // Generate a random text using the language model and print it.
        // System.out.println(lm.generate(initialText, generatedTextLength));
    }
}
