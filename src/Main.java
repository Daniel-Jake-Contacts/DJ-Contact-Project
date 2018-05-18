import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Scanner scname = new Scanner(System.in);
        Scanner sclast = new Scanner(System.in);
        Scanner scnum = new Scanner(System.in);
        Scanner scsearch = new Scanner(System.in);
        Scanner scdel = new Scanner(System.in);
        Scanner scyn = new Scanner(System.in);
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
                String first;
                String last;
                String name;
                String number;
                System.out.println("What is the contacts first name");
                System.out.print(">");
                first = scname.nextLine();
                System.out.println("What is the contacts last name");
                System.out.print(">");
                last = sclast.nextLine();
                name = first + " " + last;
                Path filepath = Paths.get(directory,fileName);
                List<String> list = Files.readAllLines(filepath);

                for(String contact:list) {
                    String lowerCont = contact.toLowerCase();
                    if (lowerCont.contains(name.toLowerCase())) {
                        System.out.println("This name already exists, would you like to overwrite? [y/n]");
                        System.out.print(">");
                        String yn = scyn.nextLine();
                        if("y".equalsIgnoreCase(yn)) {
                            deleteContact(directory,fileName,name);
                        } else if ("n".equalsIgnoreCase(yn)){
                            System.out.println("Make a change to the name");
                            System.out.print(">");
                            name = scname.nextLine();
                        }
                    }
                }
                System.out.println("What is the contacts number");
                System.out.print(">");
                number = scnum.nextLine();
                if(number.length()<=7){
                    number = number.substring(0,3) + "-" + number.substring(3,number.length());
                } else if(number.length()>7 && number.length()<12){
                    number = "(" + number.substring(0,3) + ")-" + number.substring(3,6) + "-" +number.substring(6,number.length());
                } else if(number.length()>=12){
                    number = number;
                }
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
                displayFull(directory, fileName);
                System.out.println();
                System.out.println("Enter the name of the contact you want to delete EXACTLY as it is spelled above (nestor)");
                System.out.print(">");
                delete = scdel.nextLine();
                deleteContact(directory,fileName,delete);
            } else if (choice == 5) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Try again");
            }
        }while(choice != 5);
    }

    public static void addContact(ArrayList<String> list, String dir, String file) throws IOException {
        Path filepath = Paths.get(dir,file);
        Files.write(filepath,list, StandardOpenOption.APPEND);
    }

    private static void displayFull(String dir, String file) throws IOException{
        Path filepath = Paths.get(dir,file);
        List<String> list = Files.readAllLines(filepath);
        List<String> names = new ArrayList<>();
        String contactName = "";

        Collections.sort(list);
        for (String contact:list) {
            String[] parts  = contact.split(" - ");
            names.add(parts[0]);
            for (String name:names){
                contactName = name;
            }
            System.out.println(contactName);
        }
    }

    private static void readLines(String dir,String file) throws IOException {
        Path filepath = Paths.get(dir,file);
        List<String> list = Files.readAllLines(filepath);
        List<String> names = new ArrayList<>();
        List<String> phones = new ArrayList<>();
        List<String> firstNames = new ArrayList<>();
        List<String> lastNames = new ArrayList<>();
        String contactName = "";
        String contactNumber = "";
        String firstName = "";
        String lastName;
        String lastInitial = "";

        Collections.sort(list);
        for(String contact:list) {
            String[] parts  = contact.split(" - ");
            names.add(parts[0]);
            phones.add(parts[1]);
            for (String name:names){
                String [] fullNames = name.split(" ");
                firstNames.add(fullNames[0]);
                lastNames.add(fullNames[1]);

                for (String first:firstNames){
                    firstName = first;
                }
                for (String last:lastNames){
                    lastName = last;
                    lastInitial = lastName.substring(0, 1);
                }
                contactName = firstName + " " + lastInitial;
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
        Scanner scdelete = new Scanner (System.in);
        String yesno;
        List<String> names = new ArrayList<>();
        String contactName;
        String tryagain = "yes";

        Path filepath = Paths.get(dir, file);
        List<String> list = Files.readAllLines(filepath);

        do {

            for (String contact : list) {
                String[] parts = contact.split(" - ");
                names.add(parts[0]);
                for (String name : names) {
                    contactName = name;
                    if (name.equalsIgnoreCase(delete)) {
                        del = names.indexOf(contactName);
                    }
                }
            }

            if (names.indexOf(delete) == -1) {
                System.out.println("That name isn't here. Try again");
                break;
            }

            System.out.println("Are you sure you want to delete " + list.get(del) + " [y/n]");
            System.out.print(">");
            yesno = scdelete.nextLine();

            if ("y".equalsIgnoreCase(yesno) || "yes".equalsIgnoreCase(yesno)) {
                list.remove(del);
                Files.write(filepath, list);
                System.out.println("Contact Deleted.");
                System.out.println();
                tryagain = "no";
            } else {
                break;
            }
        } while (tryagain.equalsIgnoreCase("yes"));
    }
}
