import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Scanner scname = new Scanner(System.in);
        Scanner scnum = new Scanner(System.in);

        String directory = "data";
        String fileName = "contacts.txt";




        int choice;
        do {
            System.out.println("What would you like to do?");
            System.out.println();
            System.out.println("1. View Contacts");
            System.out.println("2. Add Contacts");
            System.out.println("3. Search Contacts");
            System.out.println("4. Delete Contacts");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print(">");
            choice = sc.nextInt();
            System.out.println();
            if (choice == 1) {
                readLines(directory, fileName);
            } else if (choice == 2) {
                ArrayList<String> num = new ArrayList<>();
                String name;
                String number;
                System.out.println("What is the contacts name");
                System.out.print(">");
                name = scname.nextLine();
                System.out.println("What is the contacts number");
                System.out.print(">");
                number = scnum.nextLine();
                num.add(name + " - " + number);

                addContact(num, directory, fileName);
                System.out.println("Contact added");
            } else if (choice == 3) {

            } else if (choice == 4) {

            } else if (choice == 5) {

            } else {
                System.out.println("try again");
            }
        }while(choice != 5);

    }

    public static void addContact(ArrayList<String> list, String dir, String file) throws IOException {
        Path filepath = Paths.get(dir,file);
        Files.write(filepath,list, StandardOpenOption.APPEND);

    }

    private static void readLines(String dir,String file) throws IOException {
        Path filepath = Paths.get(dir,file);
        List<String> list = Files.readAllLines(filepath);

        for(String contact:list) {
            System.out.println(contact);
        }
    }
}
