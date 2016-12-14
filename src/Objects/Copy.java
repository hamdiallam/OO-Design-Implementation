package Objects;

/**
 *  A class that represents the
 *  "real life" copy of the book
 */
public class Copy {

    //current status of the copy
    private CopyStatus st;

    //simple trick to assign every copy a unique code number
    private static int codeCounter = 0;
    private int barcodeNum;

    private Book book; //The Book that this is a copy of
    private LibraryCard currCard = null; //The current Card that has the copy
    private int dayCount; //used to make sure a copy does not go overdue

    public Copy(Book book){
        this.book = book;
        barcodeNum = codeCounter;
        codeCounter++;
        st = CopyStatus.Available; //Default state of a copy
        dayCount = 0;
    }

    public void newDay(){
        if(st == CopyStatus.Available)
            return;
        dayCount++;
        if(dayCount > book.getMaxNumOfDays()) {
            currCard.addFines(book.getDailyFine());
            st = CopyStatus.Overdue;
        }
    }
    public void makeAvailable(){
        st = CopyStatus.Available;
        currCard = null;
        dayCount = 0;
    }

    public void checkoutWithCard(LibraryCard card){
        currCard = card;
        st = CopyStatus.CheckedOut;
    }

    public boolean canCheckOut(LibraryCard card){
        //Students cannot checkout Reference books.txt
        if(book.isReference() && card.getState() == CardState.Student)
            return false;
        return true;
    }

    public boolean isReference(){
        return book.isReference();
    }

    public String getDetailed(){
        String result="";
        result+="Barcode: " + barcodeNum;
        result+=", STATUS: " + st.name();
        result+=", currOwnerId: " + ((currCard == null) ? "Available" : currCard.getCardId());
        result+=", Days-CheckedOut: " + dayCount;
        result+=", isReference: " + isReference();
        return result;
    }

    @Override
    public String toString() {
        String result="";
        result+="Barcode: " + barcodeNum;
        result+=", STATUS: " + st.name();
        result+=", Days-CheckedOut: " + dayCount;
        result+=", isReference: " + isReference();
        return result;
    }

    public CopyStatus getCopyStatus(){
        return st;
    }
    public int getBarcodeNum(){
        return barcodeNum;
    }

}

/* Used for the State of a given copy
        A copy can have three different states
         -CheckedOut -> A borrower has the copy
         -OnHold -> Not going to be used! Reservations are automatically
                    fulfilled upon a copy return
         -Available -> A copy is available for anyone to checkout
*/
enum CopyStatus {
    CheckedOut, Available, Overdue
}
