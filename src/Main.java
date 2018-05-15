import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String directory = "data";
        String fileName = "contacts.txt";
        readLines(directory,fileName);

    }

    public static void addContact(ArrayList<String> list, String dir, String file) throws IOException {
        Path filepath = Paths.get(dir,file);
        Files.write(filepath,list, StandardOpenOption.APPEND);

    }

    public static void readLines(String dir,String file) throws IOException {
        Path filepath = Paths.get(dir,file);
        List<String> list = Files.readAllLines(filepath);

        for(String contact:list) {
            System.out.println(contact);
        }
    }
}
