//Name: Quentin Jennings
//UCID: 30089570
//Date: 2021-02-26

/*
   INSTRUCTIONS: Implement all the members for which stubs are provided.
   
   You may not modify (change arguments, change permissions, etc) any of the
   stubs provided. You may only add to the code.

   You may modify class BookList, which is included for testing purposes only.
   It will not be graded. Your file must contain a public class BookList and be
   able to be compiled on the command-line, just like your assignments. All code
   must be included in a single file named BookList.java

   Include your name and student ID at the top of the file, as comments or
   documentation. We will not otherwise evaluate the program for comments/
   documentation.

   Other tips:
   1. Output should match what is shown in the example. 
   2. You may assume that we will only test with valid data in the format
       shown in exampleData(), but we will not use the same data to test.
   3. You may add any additional private methods and data that you like.
   4. Do not include a local package declaration! (You may use import
      statements.)
   5. The code can be completed using concepts used already in class.
      You may need to include some libraries we have previously introduced.
   6. You may assume that there will never be more than 15 books.
   7. We will test the following methods: 
          formatString(String key, String value)        
          dashToDot()      
          OneBook(String[] array) 
          ratingToString()        
          ratingToString(int rating)               
          getDate()        
          getTitle()        
          getAuthor()        
          getRating()     
          setRating(String rating)      
          Books(String[][] books)   
          getBooks()           
          formatRating()
*/


abstract class PrettyPrinter {
    String date;
	// Returns a string in the format of:
    // key: value
    public String formatString(String key, String value) {
		return key + ": " + value;
    }

    // Change all '-' to '.' in date
    public void dashToDot() {
		date = date.replace('-', '.');
    }
}

class OneBook extends PrettyPrinter {
    String title;
	String author;
	int rating;
	
	// Constructor
    public OneBook(String[] array) {
		super();
		//{ "2020-11-12", "The First Fifteen Lives of Harry August", "Claire North", "****" }
		this.date = array[0];
		dashToDot();
		this.title = array[1];
		this.author = array[2];
		setRating(array[3]);
    }

    // This returns a String of the star rating of this.
    // E.g., if this.rating is 2, it returns "**"
    public String ratingToString() {
		return ratingToString(rating);
    }

    // Given a rating as an integer, this returns a String
    // of that number of stars. E.g., 3 returns "***"
    public static String ratingToString(int rating) {
		StringBuilder stars = new StringBuilder();
		for(int i = 0; i < rating; i++) {
			stars.append("*");
		}
		String returnStars = new String(stars);
		return returnStars;
    }

    // Getter
    public String getDate() {
		return date;
    }

    // Getter
    public String getTitle() {
		return title;
    }

    // Getter
    public String getAuthor() {
		return author;
    }

    // Getter
    public int getRating() {
		return rating;
    }

    // Setter
    public void setRating(String rating) {
		this.rating = rating.length();
    }
}


class Books extends PrettyPrinter {
    OneBook[] books;
	
	// Constructor
    public Books(String[][] books) { 
		super();
		for(int i = 0; i < books.length; i++) {
			OneBook book = new OneBook(books[i]);
		}
    }

    // Getter
    //Name: Quentin Jennings
	//UCID: 30089570
	//Date: 2021-02-26
	public OneBook[] getBooks() {
		return books;
    }

    // Creates an legible String of all books, ordered by rating.
    // All ratings are included, even if no books have that rating.
    // Format is rating (as *) followed by a newline (the String "\n") 
    // Each book is listed on its own line, with two spaces before it.
    // (Format of each line is demonstrated in example output.)
    // There is a newline between the last book in a rating and the next
    // rating. There is no newline at the end of the String.
    public String formatRating() {
		StringBuilder listString = new StringBuilder();
		
		listString.append("*");	
		for(int i = 0; i < books.length; i++) {
			if(books[i].getRating() == 1) {
				listString.append("  Date: " + books[i].getDate());
				listString.append(" | Title: " + books[i].getTitle());
				listString.append(" | Author: " + books[i].getAuthor() + "\n");
			}
		}
		
		listString.append("\n**");
		for(int i = 0; i < books.length; i++) {
			if(books[i].getRating() == 2) {
				listString.append("  Date: " + books[i].getDate());
				listString.append(" | Title: " + books[i].getTitle());
				listString.append(" | Author: " + books[i].getAuthor() + "\n");
			}
		}
		
		listString.append("\n***");
		for(int i = 0; i < books.length; i++) {
			if(books[i].getRating() == 3) {
				listString.append("  Date: " + books[i].getDate());
				listString.append(" | Title: " + books[i].getTitle());
				listString.append(" | Author: " + books[i].getAuthor() + "\n");
			}
		}
		
		listString.append("\n****");
		for(int i = 0; i < books.length; i++) {
			if(books[i].getRating() == 4) {
				listString.append("  Date: " + books[i].getDate());
				listString.append(" | Title: " + books[i].getTitle());
				listString.append(" | Author: " + books[i].getAuthor() + "\n");
			}
		}
		
		listString.append("\n*****");
		for(int i = 0; i < books.length; i++) {
			if(books[i].getRating() == 5) {
				listString.append("  Date: " + books[i].getDate());
				listString.append(" | Title: " + books[i].getTitle());
				listString.append(" | Author: " + books[i].getAuthor() + "\n");
			}
		}
		String returnString = new String(listString);
		return returnString;
    }
}



// For testing purposes; given this code below, your program should produce the example 
// output.
public class BookList {

	public static void main(String args[]) {
        // Retrieve example data
        var data = exampleData();

        // Create a Books object. Order of books is the
        // same as in the input data
        var books = new Books(data);

        // Demonstrate formatRating(). Order of books is
        // by rating, then by the order in the original data.
        System.out.println(books.formatRating());
        // Add a blank line before the next output - formatRating()
        // does not end with a newline character.
        System.out.println();

        // Retrieve the array of OneBook objects from Books
        var array = books.getBooks();

        // Demonstrate the formatString method
        System.out.println(array[0].formatString("Author", array[0].getAuthor()));
        System.out.println(books.formatString("Key", "Value"));

        // Demonstrate the OneBook constructor
        String[] aBook = {"2018-10-29", "The Name of the Rose", "Umberto Eco", "****"};
        var myBook = new OneBook(aBook);

        // Demonstrate ratingToString, getRating
        System.out.println(myBook.getRating() + " " + myBook.ratingToString());
        System.out.println(OneBook.ratingToString(2));

        // Demonstrate internal storage of date: dashToDot is applied before storing
        System.out.println(myBook.getDate());

        // Demonstrate all other methods
        System.out.println("--" + array[4].getDate() + "--");
        System.out.println("--" + array[4].getTitle() + "--");
        System.out.println("--" + array[4].getAuthor() + "--");
        array[4].setRating("*");
        System.out.println("--" + array[4].getRating() + "--");
    }

    public static String[][] exampleData() {
	// Each line is date read, title, author, rating
        String[][] example = { 
                { "2020-11-12", "The First Fifteen Lives of Harry August", "Claire North", "****" },
                { "2020-11-16", "The Last Report of Miracles at Little No Horse", "Louise Erdrich", "****" },
                { "2020-11-19", "Sapiens", "Yuval Noah Harari", "**" },
                { "2020-12-01", "Fingersmith", "Sarah Waters", "*****" },
                { "2020-12-08", "The Unwomanly Face of War", "Svetlana Alexievich", "***" },
                { "2020-12-26", "The White Tiger", "Aravind Adiga", "***" },
                { "2020-01-02", "Recursion", "Blake Crouch", "***" },
                { "2021-01-18", "Mostly Dead Things", "Kristen Arnett", "***" },
                { "2021-01-20", "Kindred", "Octavia E. Butler", "****" },
                { "2021-01-26", "The Ministry of Utmost Happiness", "Arundhati Roy", "***" },
                { "2021-02-15", "Killing Commendatore", "Haruki Murakami", "****" },
                { "2021-02-17", "Here for It", "R. Eric Thomas", "****" },
                { "2021-02-21", "The Marriage of Opposites", "Alice Hoffman", "***" },
                { "2021-02-23", "The Outcasts of Time", "Ian Mortimer", "***" } };
        return example;
    }
}
