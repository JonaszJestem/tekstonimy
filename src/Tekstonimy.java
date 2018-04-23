import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

class Tekstonimy {
    private final HashMap<Integer,String> digitsMap = new HashMap<>();
    private final String fileName = "./resources/slowa.txt";

    private Tekstonimy() {
        digitsMap.put(2,"AĄąBCĆć");
        digitsMap.put(3,"DEĘęF");
        digitsMap.put(4,"GHI");
        digitsMap.put(5,"JKLŁł");
        digitsMap.put(6,"MNŃńOÓó");
        digitsMap.put(7,"PQRSŚś");
        digitsMap.put(8,"TUV");
        digitsMap.put(9,"WXYZŻżŹź");
    }

    public static void main(String[] args) {
        Tekstonimy tekstonimy = new Tekstonimy();

        Scanner input = new Scanner(System.in);
        String digits = input.nextLine();

        String pattern = tekstonimy.createPattern(digits);

        try(Stream<String> words = Files.lines(Paths.get(tekstonimy.fileName))) {
            words.filter((word)->word.matches(pattern)).limit(5).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * For every digit it creates pattern like 2 -> [ABC]
     * @param digits digits from input
     * @return pattern that matches given digits
     */
    private String createPattern(String digits) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("(?i)");
        for(int i = 0; i < digits.length(); i++) {
            stringBuilder.append("[");
            int digit = Character.getNumericValue(digits.charAt(i));
            String mapped = this.digitsMap.get(digit);
            stringBuilder.append(mapped);
            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }
}
