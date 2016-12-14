package Objects;

import java.util.ArrayList;
import Exceptions.*;

/**
 *  This class is what uniquely
 *  identifies a borrower.
 *      - Every borrower will be assigned
 *        a library card
 */
public class LibraryCard {

    //Easy way to enforce a unique id
    private static int idCounter = 0;
    private CardState state;

    private int cardId;
    private String name;
    private int currentFines;

    private ArrayList<Copy> checkedOut;

    public LibraryCard(String name, CardState st){
        this.name = name;
        this.state = st;
        currentFines = 0;
        cardId = idCounter;
        idCounter++; //ensures a new id.(Sleazy way of doing this)

        checkedOut = new ArrayList<>();
    }

    public int getCardId(){
        return cardId;
    }

    //invoked on a newDay event
    public void addFines(int fine){
        currentFines+=fine;
        return;
    }

    public void addFunds(int money){ currentFines-=money; }

    public boolean checkout(Book book) throws CheckoutException{
        //if any of these conditions are true, this library card
        //is ineligible to checkout this book. Exceptions are thrown

        if(book == null){
            throw new CheckoutException("Book does not Exist!");
        }
        Copy copy = book.getNextAvailableCopy();
        if(copy == null)
            throw new CheckoutException("No Copies to Check Out!");
        if(isOverdue())
            throw new CheckoutException("Cannot checkout: Card Has Existing Overdue Copies");
        if(currentFines != 0)
            throw new CheckoutException("Cannot checkout: Must Pay fines");
        if(!copy.canCheckOut(this))
            throw new CheckoutException("Cannot checkout: Insufficient Permissions");

        copy.checkoutWithCard(this);
        checkedOut.add(copy);
        return true;
    }

    public boolean returnCopy(Copy cp){
        if(!checkedOut.contains(cp))
            return false;
        checkedOut.remove(cp);
        cp.makeAvailable();
        return true;
    }

    public CardState getState(){
        return state;
    }

    private boolean isOverdue(){
        for(Copy copy: checkedOut){
            if(copy.getCopyStatus().equals(CopyStatus.Overdue))
                return true;
        }
        return false;
    }

    public String getCheckedOutBooks(){
        String result = "";
        result+="Copies:\n";
        for(Copy cp: checkedOut){
            result+="\t"+cp.toString() + "\n";
        }
        return result;
    }
    public String getDetailedReport(){
        String result = "";
        result+="ID: " + cardId;
        result+=", Name: " + name;
        result+=", currentFines: " + currentFines;
        result+=", numBooks: " + checkedOut.size();
        result+="\nChecked out Copies:\n";
        for(Copy cp: checkedOut){
            result+= "\t" + cp.toString() + "\n";
        }

        return result;
    }

    @Override
    public String toString() {
        String result = "";
        result+="ID: " + cardId;
        result+=", Name: " + name;
        result+=", currentFines: " + currentFines;
        result+=", numBooks: " + checkedOut.size();
        return result;
    }
}

//STATES OF A STUDENT

enum CardState{
    Teacher, Student
}
