/**
 * @author Quentin Jennings
 * @version 1.7
 * @since 1.0
 */
public class MyBook{
	/*public static void main(String[] args) {
    }*/
}

/**
 * The type Book that defines the isbn, publication year, and number of pages.
 */
abstract class Book{
    private String isbn;
    private int publicationYear;
    private int pages;

    /**
     * Instantiates a new Book.
     *
     * @param isbn  the isbn
     * @param pages the pages
     */
    public Book(String isbn, int pages){
        this.isbn = new String(isbn);
        this.pages = pages;
    }

    /**
     * Instantiates a new Book.
     */
    public Book(){
	}
	
    /**
     * Gets publication year.
     *
     * @return the publication year
     */
    public int getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets publication year.
     *
     * @param publicationYear the publication year
     */
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Gets pages.
     *
     * @return the pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * Sets pages.
     *
     * @param pages the pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Gets isbn.
     *
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets isbn.
     *
     * @param isbn the isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

/**
 * The type Hardcover is a subclass of Book.
 */
abstract class Hardcover extends Book{
    public Hardcover(String isbn, int pages){
		super(isbn, pages);
	}
	public Hardcover(){
		super();
	}
	
	/**
     * Binding.
     */
    public abstract void binding();
}

/**
 * The type Classic that defines the original pub. year, author, and publisher.
 * A type of hardcover book.
 * Bidirectional association with Classic.
 */
class Classic extends Hardcover{
    private int origPubYear = 1860;
    private Author[] theAuthor = new Author[10];
    private Publisher[] bookPublisher = new Publisher[10];

	public Classic(String isbn, int pages){
		super(isbn, pages);
	}
	public Classic(){
		super();
	}
	
	public void binding() {
	}
	
    /**
     * Create notes string.
     *
     * @return the string
     */
    public String createNotes(){
        return "Method createNotes called from Classic";
    }

    /**
     * Gets orig pub year.
     *
     * @return the orig pub year
     */
    public int getOrigPubYear() {
        return origPubYear;
    }

    /**
     * Sets orig pub year.
     *
     * @param origPubYear the orig pub year
     */
    public void setOrigPubYear(int origPubYear) {
        this.origPubYear = origPubYear;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public Author[] getTheAuthor() {
        return theAuthor;
    }

    /**
     * Sets the author.
     *
     * @param theAuthor the author
     */
    public void setTheAuthor(Author[] theAuthor) {
        this.theAuthor = theAuthor;
    }

    /**
     * Get book publisher publisher [ ].
     *
     * @return the publisher [ ]
     */
    public Publisher[] getBookPublisher() {
        return bookPublisher;
    }

    /**
     * Sets book publisher.
     *
     * @param bookPublisher the book publisher
     */
    public void setBookPublisher(Publisher[] bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
}

/**
 * The type Publisher with a defined name, address, and catalogue of
 * their Classics.
 */
class Publisher{
    private String name;
    private String address;
    private Classic[] classicsCatalog = new Classic[10];
	
    /**
     * Instantiates a new Publisher.
     *
     * @param name    the name
     * @param address the address
     */
    public Publisher(String name, String address){
        this.name = name;
        this.address = address;
    }

    /**
     * Print letterhead string.
     *
     * @return the string
     */
    public String printLetterhead(){
        return "Method printLetterhead called from Publisher";
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get classics catalog classic [ ].
     *
     * @return the classic [ ]
     */
    public Classic[] getClassicsCatalog() {
        return classicsCatalog;
    }

    /**
     * Sets classics catalog.
     *
     * @param classicsCatalog the classics catalog
     */
    public void setClassicsCatalog(Classic[] classicsCatalog) {
        this.classicsCatalog = classicsCatalog;
    }
}

/**
 * The type Author with a name, address, and age. Name defaults "Unknown".
 */
class Author{
    private String name = "Unknown";
    private String address;
    private int age;

    /**
     * Instantiates a new Author.
     *
     * @param name    the name
     * @param address the address
     * @param age     the age
     */
    public Author(String name, String address, int age){
        this.name = name;
        this.address = address;
        this.age = age;
    }

    /**
     * Instantiates a new Author.
     */
    public Author(){
    }

    /**
     * Write string.
     *
     * @return the string
     */
    public String write(){
        return "Method write called from Author";
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }
}

/**
 * The type Contract is a dependancy of Author and Publisher.
 * Stores the date, publisher, and up to 10 authors
 */
class Contract{
    private String date;
    private Publisher publisherInfo;
    private Author[] authorInfo = new Author[10];

    /**
     * Instantiates a new Contract.
     *
     * @param date          the date
     * @param publisherInfo the publisher info
     * @param authorInfo    the author info
     */
    public Contract(String date, Publisher publisherInfo, Author[] authorInfo){
        this.date = date;
        this.publisherInfo = publisherInfo;
        this.authorInfo = authorInfo;
    }

    /**
     * Print contract string.
     *
     * @return the string
     */
    public String printContract(){
        return "Method printContract called from Contract";
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets publisher info.
     *
     * @return the publisher info
     */
    public Publisher getPublisherInfo() {
        return publisherInfo;
    }

    /**
     * Sets publisher info.
     *
     * @param publisherInfo the publisher info
     */
    public void setPublisherInfo(Publisher publisherInfo) {
        this.publisherInfo = publisherInfo;
    }

    /**
     * Gets author info.
     *
     * @return the author info
     */
    public Author[] getAuthorInfo() {
        return authorInfo;
    }

    /**
     * Sets author info.
     *
     * @param authorInfo the author info
     */
    public void setAuthorInfo(Author[] authorInfo) {
        this.authorInfo = authorInfo;
    }
}

/**
 * The type Paperback is a subclass of Book.
 */
abstract class Paperback extends Book{
    
	public Paperback(String isbn, int pages){
		super(isbn, pages);
	}
	public Paperback(){
		super();
	}
	
	/**
     * Cover art string.
     *
     * @return the string
     */
    public String coverArt(){
        return "Method coverArt called from Paperback";
    }
}

/**
 * The type Nonfiction is a subclass of Paperback that
 * contains its dewey classification category.
 */
class Nonfiction extends Paperback{
    private Category deweyClassification;
	
	public Nonfiction(String isbn, int pages){
		super(isbn, pages);
	}
	public Nonfiction(){
		super();
	}
	
	public String coverArt(){
        return "Method coverArt called from Nonfiction";
    }
	
    /**
     * Topic string.
     *
     * @return the string
     */
    public String topic(){
        return "Method topic called from Nonfiction";
    }

    /**
     * Gets dewey classification.
     *
     * @return the dewey classification
     */
    public Category getDeweyClassification() {
        return deweyClassification;
    }

    /**
     * Sets dewey classification.
     *
     * @param deweyClassification the dewey classification
     */
    public void setDeweyClassification(Category deweyClassification) {
        this.deweyClassification = deweyClassification;
    }
}

/**
 * The type Category has a symmetric reflexive association "refines".
 * Contains the category name and its sub/supercategory.
 */
class Category{
    private Category subCategory;
    private Category superCategory;
    private String category;

    /**
     * Sort string.
     *
     * @return the string
     */
    public String sort(){
        return "Method sort called from Category";
    }

    /**
     * Gets sub category.
     *
     * @return the sub category
     */
    public Category getSubCategory() {
        return subCategory;
    }

    /**
     * Sets sub category.
     *
     * @param subCategory the sub category
     */
    public void setSubCategory(Category subCategory) {
        this.subCategory = subCategory;
    }

    /**
     * Gets super category.
     *
     * @return the super category
     */
    public Category getSuperCategory() {
        return superCategory;
    }

    /**
     * Sets super category.
     *
     * @param superCategory the super category
     */
    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}

/**
 * The type Fiction is a subclass of Paperback.
 */
abstract class Fiction extends Paperback{
    public Fiction(String isbn, int pages){
		super(isbn, pages);
	}
	public Fiction(){
		super();
	}
	
	public abstract String coverArt();

    /**
     * Genre string.
     *
     * @return the string
     */
    public String genre(){
        return "Method genre called from Fiction";
    }
}

/**
 * The type Anthology that contains 5 to 10 stories.
 */
class Anthology extends Fiction{
    private Story[] story = new Story[10];
	
	public Anthology(String isbn, int pages){
		super(isbn, pages);
	}
	public Anthology(){
		super();
	}
	
	public String coverArt(){
        return "Method coverArt called from Anthology";
    }
	
    /**
     * Story order string.
     *
     * @return the string
     */
    public String storyOrder(){
        return "Method storyOrder called from Anthology";
    }

    /**
     * Gets story.
     *
     * @return the story
     */
    public Story[] getStory() {
        return story;
    }

    /**
     * Sets story.
     *
     * @param story the story
     */
    public void setStory(Story[] story) {
        if(story.length < 5) {
			throw new IllegalArgumentException("An anthology must contain at" 
														+ " least 5 stories.");
		}
		else {
			this.story = story;
		}
    }
}

/**
 * The type Story that has an author.
 */
class Story{
    private Author theAuthor[] = new Author[10];

    /**
     * Plot string.
     *
     * @return the string
     */
    public String plot(){
        return "Method plot called from Story";
    }

    /**
     * Gets the author.
     *
     * @return the the author
     */
    public Author[] getTheAuthor() {
        return theAuthor;
    }

    /**
     * Sets the author.
     *
     * @param theAuthor the the author
     */
    public void setTheAuthor(Author[] theAuthor) {
        this.theAuthor = theAuthor;
    }
}

/**
 * The type Novel that contains the author and series.
 */
class Novel extends Fiction{
    private Author[] myAuthor = new Author[10];
    private Series[] mySeries = new Series[10];
	
	public Novel(String isbn, int pages){
		super(isbn, pages);
	}
	public Novel(){
		super();
	}
	
	public String coverArt(){
        return "Method coverArt called from Novel";
    }
	
    /**
     * Theme string.
     *
     * @return the string
     */
    public String theme(){
        return "Method theme called from Novel";
    }

    /**
     * Gets my author.
     *
     * @return the my author
     */
    public Author[] getMyAuthor() {
        return myAuthor;
    }

    /**
     * Sets my author.
     *
     * @param myAuthor the my author
     */
    public void setMyAuthor(Author[] myAuthor) {
        this.myAuthor = myAuthor;
    }

    /**
     * Gets series.
     *
     * @return the series
     */
    public Series[] getSeries() {
        return mySeries;
    }

    /**
     * Sets series.
     *
     * @param series the series
     */
    public void setSeries(Series[] mySeries) {
        this.mySeries = mySeries;
    }
}

/**
 * The type Series that contains the series name.
 */
class Series{
    private String seriesName;

    /**
     * Theme string.
     *
     * @return the string
     */
    public String theme() {
        return "Method theme called from Series";
    }

    /**
     * Gets series name.
     *
     * @return the series name
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * Sets series name.
     *
     * @param seriesName the series name
     */
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
}