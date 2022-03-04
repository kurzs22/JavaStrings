public class MainUTF8 {

    public static void main (String[] args) {
        // String with special characters
        // Found at https://docs.oracle.com/javase/tutorial/java/data/characters.html
        String special = "\t\b\n\r\f\'\"\\";

        decode(special);

        // UTF-8 (encoding of the editor)
        // To display german umlaut correctly it must be compiled like this:
        //      javac -encoding UTF-8 MainUTF8.java
        decode("ÄäÖöÜüẞß");

        // german umlaut with unicode notation
        decode("\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u1e9e\u00df");
        
        // some emojis
        // each built up with more than one unicode characters
        decode("👍😊😂🤣❤😍😒👌");

        // Upper/Lower case on german umlaut. Upper case of small "ß" is "SS"!
        decode("ÄäÖöÜüẞß".toUpperCase());
        decode("\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u1e9e\u00df".toLowerCase());
        
        // Decode the arguments from command line
        for (String arg : args) decode(arg);

        // Write a method that splits a string with a sentence into words and reverses the order of the words.

        reverseWordsInSentence("This is a test!");

        for (String arg : args) reverseWordsInSentence(arg);

        // Write a method that splits a string with a sentence into words and reverses the order of the characters
        // in each word while maintaining the order of the words in the sentence.

        reverseLettersInWords("This is a test!");

        for (String arg : args) reverseLettersInWords(arg);

        // Combination

        reverseLettersInWords(reverseWordsInSentence("This is a test!"));
        reverseWordsInSentence(reverseLettersInWords("This is a test!"));
    }

    public static void decode(String s) {
        int i;
        char c;
        char cPrintable;

        System.out.println("\nDecoding String: " + s);
        System.out.println("String has length: " + s.length());
        for (i=0; i < s.length(); i++) {
            c = s.charAt(i);
            cPrintable = (c<32) ? ' ' : c;
            System.out.format("%d: \\u%04x %c \t", i, (int)c, cPrintable);
        }
        System.out.println();
    }

    public static String reverseWordsInSentence(String sentence) {
        String result = "";
        int endOfWord = sentence.length();
        int i = sentence.length();
        char c = 0;

        System.out.println("\nOriginal sentence: " + sentence);
        System.out.print("Sentence with reversed words: ");

        while (i > 0) {
            do {
                i--;
                if (i >= 0) c = sentence.charAt(i);
            } while (i >= 0 && Character.isLetter(c));
            
            result += sentence.substring(i + 1, endOfWord);
            endOfWord = i + 1;

            while (i > 0 && !Character.isLetter(c)) {
                result += sentence.substring(i, endOfWord);
                endOfWord = i;

                i--;
                c = sentence.charAt(i);
            }
        }

        System.out.println(result);
        return result;
    }

    public static String reverseLettersInWords(String sentence) {
        String result = "";
        int length = sentence.length();
        int startOfWord = 0;
        int i = 0;
        char c = 0;

        System.out.println("\nOriginal sentence: " + sentence);
        System.out.print("Sentence with reversed letters in words: ");

        while (i < length) {
            do {
                if (i < length) c = sentence.charAt(i);
                i++;
            } while (i < length && Character.isLetter(c));

            for (int j = i - 1; j >= startOfWord; j--) {
                result += sentence.charAt(j);
            }
            
            startOfWord = i - 1;

            do {
                result += c;
                startOfWord = i;

                if (i < length) c = sentence.charAt(i);
                i++;
            } while (i <= length && !Character.isLetter(c));
        }

        System.out.println(result);
        return result;
    }
}