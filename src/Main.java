import Exceptions.CheckoutException;
import Objects.*;
import java.util.Scanner;

/**
 * Driver for the Library system
 * All invocations start here and
 * interacts with the Library class
 */
public class Main {

    public static void main(String[] args){
        System.out.println("CIS-28 Library System Prototype!\n" +
                "\t-All changes are not persistent and will be erased on exit.\n" +
                "\t-Create new records in the text files to add permanent changes.");

        Scanner kb = new Scanner(System.in);
        //IMPORTANT. Length of one day
        System.out.print("Enter length of 1 day in minutes: ");
        int day = kb.nextInt();

        //load files from the txt
        Library lib = new Library(day);
        Loader.loadFiles(lib);

        printInventory(lib);
        int x = 0;
        while(x == 0){
            printMenu(lib);
            x = getChoiceAndExecute(kb, lib);
        }

        System.out.println("\nGoodbye! Changes are not persistent!");
    }

    private static int getChoiceAndExecute(Scanner kb, Library lib){
        int choice = kb.nextInt();
        int id;
        int isbn;
        int barcode;
        LibraryCard card;
        Book book;
        Copy cp;
        switch(choice){
            //print inventory
            case 1:
                printInventory(lib);
                break;
            //Checkout a book.
            //- Exceptions will be thrown if
            //a precondition is not met
            case 2:
                System.out.print("Enter cardID: ");
                id = kb.nextInt();
                System.out.print("Enter book ISBN: ");
                isbn = kb.nextInt();
                try{
                    card = lib.getBorrower(id);
                    book = lib.getBook(isbn);
                    if(card == null){
                        System.out.println("Invalid ID");
                        return 0;
                    }
                    card.checkout(book);
                    System.out.println("Checkout successful!\n" +
                            "Checkout duration: " + lib.getDayInMinutes()*book.getMaxNumOfDays()
                            + " min");
                } catch(CheckoutException e){
                   System.out.println(e.getMessage());
                }
                break;
            //returning a book
            case 3:
                System.out.print("Enter card id: ");
                id = kb.nextInt();
                card = lib.getBorrower(id);
                if(card == null) {
                    System.out.println("Card does not exist!");
                    return 0;
                }
                System.out.println(card.getCheckedOutBooks());
                System.out.print("Enter copy Barcode: ");
                barcode = kb.nextInt();
                cp = lib.getCopy(barcode);
                if(cp == null){
                    System.out.println("Copy does not exist!");
                    return 0;
                }
                if(!card.returnCopy(cp)) {
                    System.out.println("Return unsuccessful");
                }
                else
                    System.out.println("Return successful");
                break;
            //Detailed report for a copy
            case 4:
                System.out.print("Enter cardID: ");
                id = kb.nextInt();
                card = lib.getBorrower(id);
                if(card == null){
                    System.out.println("Invalid ID");
                    return 0;
                }
                System.out.println(card.getDetailedReport());
                break;
            //Detailed report for a book
            case 5:
                System.out.print("Enter book ISBN: ");
                isbn = kb.nextInt();
                book = lib.getBook(isbn);
                System.out.print(book.getDetailedReport());
                break;
            //Borrower can pay fines
            case 6:
                System.out.print("Enter cardID: ");
                id = kb.nextInt();
                card = lib.getBorrower(id);
                if(card == null){
                    System.out.println("Card does not exist!");
                    return 0;
                }
                System.out.print("Enter amount: ");
                int amt = kb.nextInt();
                card.addFunds(amt);
                break;
            case 0:
                return 1;
            default:
                System.out.println("Please make a valid choice");
                break;
        }
        return 0;
    }

    private static void printMenu(Library lib){
        System.out.print("Menu: \n" +
                "\t1.)Print Inventory\n" +
                "\t2.)Checkout a book\n" +
                "\t3.)Return a book\n" +
                "\t4.)Detailed Card report\n" +
                "\t5.)Detailed Book report\n" +
                "\t6.)Pay Fines\n" +
                "\t0.)Exit the Program\n" +
                "Select a choice: ");
    }

    private static void printInventory(Library lib){
        System.out.println("Library Inventory: ");
        String str = lib.toString();
        System.out.println(str);
    }
}
