import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Scanner scname = new Scanner(System.in);
        Scanner scnum = new Scanner(System.in);
        Scanner scsearch = new Scanner(System.in);
        Scanner scdel = new Scanner(System.in);

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
                System.out.println("Name         |  Number");
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                readLines(directory, fileName);
                System.out.println("----------------------------");
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
                System.out.println("----------------------------");
            } else if (choice == 3) {
                String search;
                System.out.println("Enter the name you would like to search");
                System.out.println(">");
                search = scsearch.nextLine();
                searchContacts(directory, fileName, search);
                System.out.println("----------------------------");
            } else if (choice == 4) {
                String delete;
                System.out.println("Enter name of contact you would like to delete");
                System.out.print(">");
                delete = scdel.nextLine();
                deleteContact(directory,fileName,delete);

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
        List<String> names = new ArrayList<>();
        List<String> phones = new ArrayList<>();
        String contactName = "";
        String contactNumber = "";

        for(String contact:list) {
            String[] parts  = contact.split(" - ");
            names.add(parts[0]);
            phones.add(parts[1]);
            for (String name:names){
                contactName = name;
            }
            for (String phone:phones){
                contactNumber = phone;
            }
            if (contactName.length() < 8){
                System.out.println(contactName + "\t\t" + " | " + "\t" + contactNumber);
            } else {
                System.out.println(contactName + "\t" + " | " + "\t" + contactNumber);
            }
        }


    }

    private static void searchContacts(String dir, String file, String search) throws IOException {
        Path filepath = Paths.get(dir,file);
        List<String> list = Files.readAllLines(filepath);

        for(String contact:list) {
            String lowerCont = contact.toLowerCase();
            if (lowerCont.contains(search.toLowerCase())) {
                System.out.println(contact);
            }
        }
    }

    private static void deleteContact(String dir, String file, String delete) throws IOException {
        int del = -1;

        Path filepath = Paths.get(dir, file);
        List<String> list = Files.readAllLines(filepath);

        for(String contact:list) {
            String lowerCont = contact.toLowerCase();
            if (lowerCont.contains(delete.toLowerCase())){
                del = list.indexOf(contact);
            }
        }

        list.remove(del);
        Files.write(filepath,list);
        System.out.println("Contact Deleted.");

    }

}
