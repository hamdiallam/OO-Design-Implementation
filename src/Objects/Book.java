package Objects;

import java.util.ArrayList;

/**
 * This class outlines the layout of a book
 *  - A copy will represent the physical book
 *  - Static members will be used to share common
 *    information of a copy(Stuff in a BOOK)
 */
public class Book {

    //private members
    private String title;
    private String publisher;
    private int ISBN;
    private ArrayList<Copy> copyList;
    private boolean isReference;
    private int dailyFine;
    private int maxNumOfDays;

    public Book(int ISBN, String title, String publisher,
                boolean isReference, int fine, int maxNumOfDays){
        this.ISBN = ISBN;
        this.title = title;
        this.publisher = publisher;
        this.isReference = isReference;
        this.dailyFine = fine;
        this.maxNumOfDays = maxNumOfDays;

        copyList = new ArrayList<>();
    }

    //Used to add a new physical copy for a Book
    public Copy createCopy(){
        Copy cp = new Copy(this);
        copyList.add(cp);
        return cp;
    }

    public Copy getNextAvailableCopy(){
        for(Copy cp : copyList){
            if(cp.getCopyStatus().equals(CopyStatus.Available))
                return cp;
        }

        return null;
    }
    public int numOfAvailableCopies(){
        int count = 0;
        for(Copy cp: copyList){
            if(cp.getCopyStatus().equals(CopyStatus.Available))
                count++;
        }
        return count;
    }
    public boolean isReference(){
        return isReference;
    }
    public int getISBN(){
        return ISBN;
    }
    public int getDailyFine(){ return dailyFine; }
    public int getMaxNumOfDays(){ return maxNumOfDays; }
    @Override
    public String toString() {
        String result = "";
        result+="ISBN: " + getISBN();
        result+=", Title: " + title;
        result+=", Publisher: " + publisher;
        result+=", numAvailableCopies: " + numOfAvailableCopies();
        result+=", isReference: " + isReference();
        return result;
    }
    public String getDetailedReport(){
        String result = "";
        result+="ISBN: " + getISBN();
        result+=", Title: " + title;
        result+=", Publisher: " + publisher;
        result+=", numAvailableCopies: " + numOfAvailableCopies();
        result+=", isReference: " + isReference();
        result+=", MaxDays: " + maxNumOfDays;
        result+="\nCopies:\n";
        for(Copy cp: copyList){
            result+="\t"+cp.getDetailed()+"\n";
        }
        return result;
    }

}
