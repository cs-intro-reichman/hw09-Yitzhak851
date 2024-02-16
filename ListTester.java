import java.util.Arrays;
import java.util.LinkedList;

public class ListTester {
    public static void main(String[] args) {
        String methodName = args[0];
        boolean result = false;
        switch (methodName) {
            case "addFirst":
                result = testAddFirst();
                break;
            case "toString":
                result = testToString();
                break;
            case "indexOf":
                result = testIndexOf();
                break;  
            case "get":
                result = testGet();
                break;
            case "update":
                result = testUpdate();
                break;
            case "remove":
                result = testRemove();
                break;
            
            case "all":
                result = testAddFirst();
                result = result && testToString();
                result = result && testIndexOf();
                result = result && testGet();
                result = result && testUpdate();
                result = result && testRemove();
                break; 
            default:
                break;
        }
        System.out.println("Test " + methodName + " result: " + (result ? "PASSED" : "FAILED"));
    }

    public static boolean testAddFirst() {
        boolean result = true;
        String [] testWords = {"word","first","buzz"};
        for (int i = 0; i < testWords.length; i++) {
            result = result && testAddFirst(testWords[i]);
        }
        if (!result){
            System.out.println("AddFirst Test failed");
        }
        return result;
    }
    private static boolean testAddFirst (String word) {
        boolean result = true;
        LinkedList<CharData> solution = new LinkedList<CharData>();
        List yourSolution = new List();
        for (int i = 0; i < word.length() ; i++) {
            
            solution.addFirst(new CharData(word.charAt(i)));
            yourSolution.addFirst(word.charAt(i));
            boolean res = testAddFirstCase(solution,yourSolution);
            if (!res){
                System.out.println("Word: " + word + ", Char: " + word.charAt(i) + ", Index: " + i);
                System.out.println("Expected: size:" + solution.size() + ", first: " + solution.getFirst());
                System.out.println("Actual: " + yourSolution.getSize() + ", first: " + yourSolution.getFirst().chr);
            }
            result = result && res;
        }
        if (!result){
            System.out.println("AddFirst for word: " + word + " Test failed");
        }
        return result;
        
    }
    private static boolean testAddFirstCase (LinkedList<CharData> solution, List yourSolution) {
        return solution.size() == yourSolution.getSize() && solution.get(0).equals(yourSolution.getFirst().chr);
    }
    public static boolean testToString() {
        boolean result = true;
        String [] testWords = {"word","first","list"};
        String [] solutions = {
            "((w 1 0.0 0.0) (o 1 0.0 0.0) (r 1 0.0 0.0) (d 1 0.0 0.0))",
            "((f 1 0.0 0.0) (i 1 0.0 0.0) (r 1 0.0 0.0) (s 1 0.0 0.0) (t 1 0.0 0.0))",
            "((l 1 0.0 0.0) (i 1 0.0 0.0) (s 1 0.0 0.0) (t 1 0.0 0.0))"
        };
        for (int i = 0; i < testWords.length; i++) {
            result = result && testToString(testWords[i],solutions[i]);
        }
        if (!result){
            System.out.println("ToString Test failed");
        }
        return result;
    }
    
    private static boolean testToString (String word, String solution) {
        List yourSolution = new List();
        for (int i = 0; i < word.length(); i++) {
            yourSolution.addFirst(word.charAt(word.length() - 1 - i));
        }
        return yourSolution.toString().equals(solution);
    }

    public static boolean testIndexOf() {
        boolean result = true;
        String [] testWords = {"Hello_world", "JavA", "dictionary", "lexicographic"};
        List yourSolution = new List();
        for (int i = 0; i < testWords.length; i++) {
            String w = testWords[i];
            for (int j = 0; j < w.length(); j++) {
                yourSolution.addFirst(w.charAt(w.length() - 1 - j));                
            }
            boolean res = true;
            for (int j = 0; j < w.length(); j++) {
                boolean temp = testIndexOfCase(yourSolution,w,w.charAt(j));
                res = res && temp;
                if (!temp){
                    System.out.println("Word: " + w + ", Char: " + w.charAt(j));
                    System.out.println("Expected: " + w.indexOf(w.charAt(j)));
                    System.out.println("Actual: " + yourSolution.indexOf(w.charAt(j)));
                }
                
            }
            result = result && res;
        }
        if (!result){
            System.out.println("IndexOf Test failed");
        }
        return result;
    }
    private static boolean testIndexOfCase (List yourSolution, String sol, char ch) {
        return sol.indexOf(ch) == yourSolution.indexOf(ch);
    }

    public static boolean testGet() {
        boolean result = true;
        String [] testWords = {"apple", "banana", "orange", "grape", "kiwi"};
        List yourSolution = new List();
        for (int i = 0; i < testWords.length; i++) {
            String w = testWords[i];
            for (int j = 0; j < w.length(); j++) {
                yourSolution.addFirst(w.charAt(w.length() - 1 - j));                
            }
            boolean res = true;
            for (int j = 0; j < w.length(); j++) {
                boolean temp = testGetCase(yourSolution, w, j);
                res = res && temp;
                if (!temp){
                    System.out.println("Word: " + w + ", Index: " + j);
                    System.out.println("Expected: " + w.charAt(j));
                    System.out.println("Actual: " + yourSolution.get(j).chr);
                }
            }
            result = result && res;
            
        }
        if (!result){
            System.out.println("Get Test failed");
        }
        return result;
    }
    private static boolean testGetCase (List yourSolution, String sol, int index) {
        return yourSolution.get(index).equals(sol.charAt(index));
    }


    public static boolean testUpdate () {
        boolean result = true;
        String [] testWords = {"commitee_","Hello_World", "Java_", "linked_lists_are_fun", "lexicographic_order"};
        for (int i = 0; i < testWords.length; i++) {
            List yourSolution = new List();
            String w = testWords[i];

            boolean res = true;
            for (int j = 0; j < w.length(); j++) {
                boolean temp = testUpdateCase(yourSolution, w, j);
                res = res && temp;
                if (!temp){
                    System.out.println("Word: " + w + ", Index: " + j);
                    System.out.println("Expected: " + w.charAt(j));
                    System.out.println("Actual: " + yourSolution.get(j).chr);
                }
            }
            result = result && res;
        }
        if (!result){
            System.out.println("Update Test failed");
        }
        return result;
    }

    private static boolean testUpdateCase (List yourSolution, String sol, int index) {
        char c = sol.charAt(index);
        int count = countCharUpToIndex(sol,c,index);
        yourSolution.update(c);
        for (int i = 0; i < yourSolution.getSize(); i++) {
            if (yourSolution.get(i).equals(c)) {
                return yourSolution.get(i).count == count + 1;
            }
        }
        return false;
        
    }

    private static int countCharUpToIndex (String s, char c, int index) {
        int count = 0;
        for (int i = 0; i < index; i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    public static boolean testRemove() {
        boolean result = true;
        String [] testWords = {"commitee_","Hello_World", "Java_", "linked_lists_are_fun", "lexicographic_order"};
        char [][] removeChars = {
            {'m','e','_','y'},
            {'H','l','_','r'},
            {'a','v','_', 'J'},
            {'l','i','_','r'},
            {'o','r','_','g'},
        };

        
        for (int i = 0; i < testWords.length; i++) {
            List yourSolution = new List();
            String w = testWords[i];
            for (int j = 0; j < w.length(); j++) {
                yourSolution.addFirst(w.charAt(w.length() - 1 - j));                
            }
            boolean res = testRemoveCase(yourSolution, w, removeChars[i]);
            if (!res){
                System.out.println("Word: " + w + ", chars attempted to remove: " + Arrays.toString(removeChars[i]));
                System.out.println("Actual: " + yourSolution.toString());
            }
            result = result && res;   
        }
        if (!result){
            System.out.println("Remove Test failed");
        }
        return result;
    }

    private static boolean testRemoveCase (List yourSolution, String sol, char [] removeChars) {
        boolean result = true;
        for (int i = 0; i < removeChars.length; i++) {
            char c = removeChars[i];
            int size = yourSolution.getSize();
            boolean removeResult = yourSolution.remove(c);
            if (removeResult) {
                result = result && (size - 1 == yourSolution.getSize());
            } else {
                result = result && (size == yourSolution.getSize());
            }
        }
        return true;
    }
    
}
