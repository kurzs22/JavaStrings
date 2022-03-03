public class MainWin1252 {

    public static void main (String[] args) {
        // String with special characters
        // Found at https://docs.oracle.com/javase/tutorial/java/data/characters.html
        String special = "\t\b\n\r\f\'\"\\";

        decode(special);

        // Windows-1252 (encoding of the editor)
        decode("ƒд÷ц№ья");
        
        // Decode the arguments from command line
        for (String arg : args) decode(arg);
    }

    public static void decode(String s) {
        int i;
        char c;
        char cPrintable;

        System.out.println("Decoding String: " + s);
        for (i=0; i < s.length(); i++) {
            c = s.charAt(i);
            cPrintable = (c<32) ? ' ' : c;
            System.out.format("%d: U+%04x %c\n", i, (int)c, cPrintable);
        }
    }
}
