package Objects;

import java.util.Hashtable;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main Library Object that encapsulates
 * everything within the library.
 *  - The client will have to interact with
 *    the library object to start retrieve objects
 *    to start an instruction(checkout, etc.)
 *
 *  - The Library is also the only object that
 *  creates books.txt, copies, and library cards.txt.
 */
public class Library {

    private Hashtable<Integer, Book> bookTable =
                new Hashtable<>();
    private Hashtable<Integer, Copy> copyTable =
                new Hashtable<>();
    private Hashtable<Integer, LibraryCard> borrowers =
                new Hashtable<>();
    private static int oneDay;

    /*
    NEW DAY EVENT:
       The timer and timer task is used to
       invoke the new day event
        - all the books.txt are told to update fines
        and relevant tasks are checked
     */
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        public void run(){
            //new day tasks go here
            Set<Integer> keys = copyTable.keySet();
            for(Integer key: keys){
                Copy cp = copyTable.get(key);
                cp.newDay();
            }
        }
    };

    //Constructors. Period is the amount of time for one day
    public Library(int period){
        oneDay = period;
        timer.schedule(task, 0,  oneDay*60000); //conversion to minutes
    }



    public int getDayInMinutes(){
        return oneDay;
    }
    //retrieving a book through the isbn numbers
    public Book getBook(int ISBN){
        Book bk = bookTable.get(ISBN);
        return bk;
    }

    //retrieving a card through the card id
    public LibraryCard getBorrower(int id){
        return borrowers.get(id);
    }

    //retrieving a copy with the barcode number
    public Copy getCopy(int barcode){
        Copy cp = copyTable.get(barcode);
        return cp;
    }

    public void createCopyOfBook(int isbn){
        Book book = bookTable.get(isbn);
        Copy cp = book.createCopy();
        copyTable.put(cp.getBarcodeNum(), cp);
    }

    public void createNewBook(int ISBN, String title, String publisher,
                               boolean isReference, int fine, int maxDays ){
        Book bk = new Book(ISBN, title, publisher, isReference, fine
                                , maxDays);

        bookTable.put(ISBN, bk);
    }


    public void createNewStudentCard(String name){
        LibraryCard borrower = new LibraryCard(name, CardState.Student);
        borrowers.put(borrower.getCardId(), borrower);
    }

    public void createNewTeacherCard(String name){
        LibraryCard teacher = new LibraryCard(name, CardState.Teacher);
        borrowers.put(teacher.getCardId(), teacher);;
    }

    @Override
    public String toString() {
        String result = "";
        Set<Integer> bookSet = bookTable.keySet();
        Set<Integer> cardSet = borrowers.keySet();

        result += "\nAll the borrowers in the system: \n";
        for(int cardID: cardSet){
            LibraryCard card = borrowers.get(cardID);
            result+= "\t" + card.toString() + "\n";
        }
        result += "All the books in the system: \n";
        for(int isbn: bookSet){
            Book book = bookTable.get(isbn);
            result +="\t" + book.toString() + "\n";
        }
        return result;
    }
}
