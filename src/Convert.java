import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Convert {
    public static void main(String[] arg) throws IOException {
        Double koers = null;
        Scanner keyboardScanner = new Scanner(System.in);
        System.out.println("Bron: ");
        String bron = keyboardScanner.nextLine();
        System.out.println("Bestemming: ");
        String bestemming = keyboardScanner.next();
        System.out.println("Koers: ");
        try {
            koers = Double.parseDouble(keyboardScanner.next());
        } catch (NumberFormatException ignore) {
            System.out.println("Koers moet een nummer zijn");
        }


        Path p = Path.of(bron);
        Path n = Path.of(bestemming);
        if (Files.exists(p) && (Files.isRegularFile(p))) {


            List<String> alleRegels = Files.readAllLines(p.toAbsolutePath());
            List<String> nieuwWaardes = new ArrayList<String>();

            for (String regel : alleRegels) {
                String[] split = regel.split(": ");
                double price = Double.parseDouble(split[1]) * koers;
                nieuwWaardes.add(split[0] + ": " + price);
            }

            BufferedWriter out = Files.newBufferedWriter(n);
            for (String regel : nieuwWaardes) {
                out.write(regel);
                out.write("\n");
            }
            out.close();
        } else {
            System.out.println("Niet bestaand bronbestand");
        }


    }
}

