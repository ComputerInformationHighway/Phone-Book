
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ComputerInformationHighway {

    public static void main(String[] args) {
        displayMenu();
    }
    
    public static void callContact(String name) {
        
        String s[] = findNumber(name);
        
        if(s != null) 
            System.out.println("Calling " + name + " at " + s[1]);
        else
            System.out.println("No person found named " + name);
    }
    
    public static void saveContact(String name, long number) {
        System.out.println("Saving contact " + name + " : " + number);
        
        try (PrintWriter pw = new PrintWriter(new FileWriter("file.txt", true))) {
            pw.println(name +":" + number);            
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static String[] findNumber(String name) {
        
        try(Scanner in = new Scanner(new File("file.txt"))) {
            String[] s = new String[0];
            
            boolean foundPerson = false;
            
            while(in.hasNextLine()) {
                s = in.nextLine().split(":");
                if(s[0].equals(name)) {
                    System.out.println("The number associated with " + name + " is " + s[1]);
                    foundPerson = true;
                    break;
                }
            }
            
            if(!foundPerson) {
                System.out.println("Could not find " + name);
                s = null;
            }
            
            return s;
            
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public static void displayMenu() {
        
        try (Scanner in = new Scanner(System.in)) {
            String name;
            
            do{
            
            System.out.println("\nWhat would you like to do? (1, 2, 3... etc)");
            System.out.println("1. Call Contact");
            System.out.println("2. Save Contact");
            System.out.println("3. Find Number");
            
            //Break this later
            int choice = in.nextInt();
            in.nextLine();
            
            switch(choice) {
                case 1:
                    System.out.println("\nWho would you like to call? (Firstname Lastname)");
                    name = in.nextLine();
                    
                    callContact(name);
                    break;
                    
                case 2:
                    System.out.println("\nWhat is the name of the person you would like to save? (Firstname Lastname)");
                    name = in.nextLine();
                    
                    System.out.println("\nWhat is the phone number of the person you are saving? (1112223456)");
                    long number = in.nextLong();
                    in.nextLine();
                    
                    saveContact(name, number);
                    break;
                    
                case 3:
                    System.out.println("\nWhat is the name of the person whose phone number you are searching? (Firstname Lastname) ");
                    findNumber(in.nextLine());
                    break;
                    
                default:
                    
                    break;
            }
            
            System.out.println("Do you wish to perform another action? (Y/N)");
            
            if(in.next().toLowerCase().charAt(0) != 'y')
                break;
            
            }while(true);
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}