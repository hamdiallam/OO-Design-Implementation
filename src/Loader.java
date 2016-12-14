
/**
 * Class used to load text file
 * into the library system
 */

import Objects.Library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Loader {

    private static BufferedReader br;

    public static void loadFiles(Library lib){
        loadBooks(br, lib);
        loadcards(br, lib);
    }

    private static void loadcards(BufferedReader br, Library lib){
        String currline;
        try{
            br = new BufferedReader(new FileReader("cards.txt"));
            br.readLine();
            //load in the students
            while((currline = br.readLine()) != null){
                String arr[] = currline.split(",");
                String name = arr[0].trim();
                if(arr[1].trim().toLowerCase().equalsIgnoreCase("student"))
                    lib.createNewStudentCard(name);
                else
                    lib.createNewTeacherCard(name);
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    private static void loadBooks(BufferedReader br, Library lib){
        String currLine;
        try{
            br = new BufferedReader(new FileReader("books.txt"));
            br.readLine();
            while((currLine = br.readLine()) != null){
                String arr[] = currLine.split(",");
                int isbn = Integer.parseInt(arr[0]);
                boolean isRef = (arr[3].trim().toLowerCase().equalsIgnoreCase("true"));
                lib.createNewBook(isbn, arr[1].trim(), arr[2].trim(), isRef,
                        Integer.parseInt(arr[4].trim()), Integer.parseInt(arr[5].trim()) );

                for(int i = 0; i < Integer.parseInt(arr[6].trim()); i++)
                    lib.createCopyOfBook(isbn);
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
