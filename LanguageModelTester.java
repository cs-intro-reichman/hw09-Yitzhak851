import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class LanguageModelTester {
        public static void main(String[] args) {
        String methodName = args[0];
        boolean result = false;
        switch (methodName) {
            case "calculateProbabilities":
                result = testCalculateProbabilities();
                break;
            case "getRandomChar":
                result = testGetRandomChar();
                break;
            case "train":
                result = testTrain();
                break;
            case "generate":
                result = testGenerate();
                break;
            case "all":
                result = testCalculateProbabilities();
                result = result && testGetRandomChar();
                result = result && testTrain();
                result = result && testGenerate();
                break;
            default:
                break;
        }
        System.out.println("Test " + methodName + " result: " + (result ? "PASSED" : "FAILED"));
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
        if (!res){
            System.out.println("Expected: " + resString);
            System.out.println("Actual: " + list.toString());
        }
        return res;
    }


    // Test method for the getRandomChar() method
    public static boolean testGetRandomChar() {
        boolean result = true;
        LanguageModel model = new LanguageModel(3, 20);
        String [] words = {"home","worker","william_shakespeare"};
        char [][] expected = {
                {'m','h','e','h'},
                {'w','o','k','e','w','r'},
                {'r','s','h','i','a','m','k','i','r','h','s','a','a','i','a','i','l','l','r'}
        };
        for (int i = 0; i < words.length; i++) {
            List list = new List();
            for (int j = 0; j < words[i].length(); j++) {
                list.update(words[i].charAt(words[i].length() - 1 - j));
            }
            boolean res = true;
            model.calculateProbabilities(list);
            for (int j = 0; j < words[i].length(); j++) {
                char actual = model.getRandomChar(list);
                boolean temp = actual == expected[i][j];
                if (!temp) {
                    System.out.println("Expected: " + expected[i][j]);
                    System.out.println("Actual: " + actual);
                }
                res = res && temp;
            }
            result = result && res;
        } 
        if (!result){
            System.out.println("GetRandomChar Test failed");
        }
        return result;
    }

    // Test method for the train() method
    public static boolean testTrain() {
        boolean result = true;
        String [] content = {"commitee_","elephant_have_big_ears","linked_lists_are_fun","you_cannot_teach_a_man_anything;_you_can_only_help_him_find_it_within_himself"};
        String [] expected = {
            "mm : ((i 1 1.0 1.0))\nee : ((_ 1 1.0 1.0))\nte : ((e 1 1.0 1.0))\nit : ((e 1 1.0 1.0))\nco : ((m 1 1.0 1.0))\nmi : ((t 1 1.0 1.0))\nom : ((m 1 1.0 1.0))\n",
            "_b : ((i 1 1.0 1.0))\nnt : ((_ 1 1.0 1.0))\n_e : ((a 1 1.0 1.0))\nel : ((e 1 1.0 1.0))\nbi : ((g 1 1.0 1.0))\n_h : ((a 1 1.0 1.0))\nep : ((h 1 1.0 1.0))\nt_ : ((h 1 1.0 1.0))\nan : ((t 1 1.0 1.0))\nve : ((_ 1 1.0 1.0))\nar : ((s 1 1.0 1.0))\nav : ((e 1 1.0  1.0))\nph : ((a 1 1.0 1.0))\ng_ : ((e 1 1.0 1.0))\nle : ((p 1 1.0 1.0))\nha : ((v 1 0.5 0.5) (n 1 0.5 1.0))\ne_ : ((b 1 1.0 1.0))\nea : ((r 1 1.0 1.0))\nig : ((_ 1 1.0 1.0))\n",
            "st : ((s 1 1.0 1.0))\n" + //
                                "_a : ((r 1 1.0 1.0))\n" + //
                                "in : ((k 1 1.0 1.0))\n" + //
                                "_f : ((u 1 1.0 1.0))\n" + //
                                "is : ((t 1 1.0 1.0))\n" + //
                                "s_ : ((a 1 1.0 1.0))\n" + //
                                "_l : ((i 1 1.0 1.0))\n" + //
                                "fu : ((n 1 1.0 1.0))\n" + //
                                "ar : ((e 1 1.0 1.0))\n" + //
                                "re : ((_ 1 1.0 1.0))\n" + //
                                "ke : ((d 1 1.0 1.0))\n" + //
                                "e_ : ((f 1 1.0 1.0))\n" + //
                                "d_ : ((l 1 1.0 1.0))\n" + //
                                "li : ((s 1 0.5 0.5) (n 1 0.5 1.0))\n" + //
                                "nk : ((e 1 1.0 1.0))\n" + //
                                "ed : ((_ 1 1.0 1.0))\n" + //
                                "ts : ((_ 1 1.0 1.0))\n",
                
            "hi : ((m 2 0.5 0.5)\n(n 2 0.5 1.0))\n;_ : ((y 1 1.0 1.0))\nlp : ((_ 1 1.0 1.0))\ny_ : ((h 1 1.0 1.0))\nu_ : ((c 2 1.0 1.0))\nly : ((_ 1 1.0 1.0))\nm_ : ((f 1 1.0 1.0))\nma : ((n 1 1.0 1.0))\nyo : ((u 2 1.0 1.0))\nyt : ((h 1 1.0 1.0))\nea : ((c 1 1.0 1.0))\na_ : ((m 1 1.0 1.0))\nac : ((h 1 1.0 1.0))\nim : ((s 1 0.5 0.5)\n(_ 1 0.5 1.0))\nin : ((_ 1 0.3333333333333333 0.3333333333333333)\n(d 1 0.3333333333333333 0.6666666666666666)\n(g 1 0.3333333333333333 1.0))\nms : ((e 1 1.0 1.0))\nel : ((f 1 0.5 0.5)\n(p 1 0.5 1.0))\nit : ((h 1 0.5 0.5)\n(_ 1 0.5 1.0))\nan : ((y 1 0.25 0.25)\n(_ 2 0.5 0.75)\n(n 1 0.25 1.0))\nn_ : ((h 1 0.3333333333333333 0.3333333333333333)\n(o 1 0.3333333333333333 0.6666666666666666)\n(a 1 0.3333333333333333 1.0))\ng; : ((_ 1 1.0 1.0))\nnd : ((_ 1 1.0 1.0))\nng : ((; 1 1.0 1.0))\nnl : ((y 1 1.0 1.0))\nnn : ((o 1 1.0 1.0))\nno : ((t 1 1.0 1.0))\nfi : ((n 1 1.0 1.0))\nny : ((t 1 1.0 1.0))\nwi : ((t 1 1.0 1.0))\nse : ((l 1 1.0 1.0))\nca : ((n 2 1.0 1.0))\non : ((l 1 1.0 1.0))\n_a : ((n 1 0.5 0.5)\n(_ 1 0.5 1.0))\n_c : ((a 2 1.0 1.0))\not : ((_ 1 1.0 1.0))\nch : ((_ 1 1.0 1.0))\nou : ((_ 2 1.0 1.0))\n_f : ((i 1 1.0 1.0))\n_h : ((i 2 0.6666666666666666 0.6666666666666666)\n(e 1 0.3333333333333333 1.0))\n_i : ((t 1 1.0 1.0))\nt_ : ((w 1 0.5 0.5)\n(t 1 0.5 1.0))\n_m : ((a 1 1.0 1.0))\np_ : ((h 1 1.0 1.0))\n_o : ((n 1 1.0 1.0))\nte : ((a 1 1.0 1.0))\nth : ((i 2 1.0 1.0))\n_t : ((e 1 1.0 1.0))\nh_ : ((a 1 1.0 1.0))\n_w : ((i 1 1.0 1.0))\n_y : ((o 1 1.0 1.0))\nd_ : ((i 1 1.0 1.0))\nhe : ((l 1 1.0 1.0))\n",        
        };
        

        for (int i = 0; i < content.length; i++) {
            LanguageModel languageModel = new LanguageModel(2,20);
            boolean res = true;
            try {
                // SETUP -> DONT CHANGE
                File file = File.createTempFile("test"+(i+1), ".txt");
                file.setWritable(true);

                FileWriter fileWriter = new FileWriter(file.getName(), true);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                bw.write(content[i]);
                bw.close();   
                // ACTUAL TEST
                languageModel.train(file.getName());
                res = stringEqualsNoSpaces(languageModel.toString(), expected[i]);
                // ACTUAL TEST ENDS

                // DONT CHANGE <- SETUP
                file.deleteOnExit();
            } catch (Exception e) {
                res = false;
                
            } finally {
                if (!res){
                    System.out.println("Train Test " + i + " failed");
                }
                result = result && res;
            }
            
        }    
        return result;  
    }


    // Test method for the generate() method
    public static boolean testGenerate() {
        LanguageModel languageModel = new LanguageModel(7,20);
        languageModel.train("originofspecies.txt");
        String generatedText = languageModel.generate("Natural", 172);
        String expectedGeneratedText = "Natural selection, how is it possible, generally much changed\n"+
        "simultaneous rotation, when the importance of Batrachians, 393.\n"+
        "  Batrachians (frogs, toads, newts) have to modified ";

        boolean res = stringEqualsNoSpaces(generatedText, expectedGeneratedText);
        if (!res){
            System.out.println("Expected: " + expectedGeneratedText);
            System.out.println("Actual: " + generatedText);
            System.out.println("FAIL with windowLength = 7, seed = 20, initialText = Natural, textLength = 172");
        }
        return res;
    }

    private static boolean stringEqualsNoSpaces(String s1, String s2) {
        s1 = s1.replaceAll("\\s+", "");
        s2 = s2.replaceAll("\\s+", "");
        return s1.equals(s2);
    }
}
