/**
 * @author Quentin Jennings <a
 *  href="mailto:quentin.jennings@ucalgary.ca">quentin.jennings@ucalgary.ca</a>
 * @version 1.8
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.sql.*;

/**
 * The type Registration.
 */
public class Registration{

	public final String DBURL; //store the database url information
	public final String USERNAME; //store the user's account username
	public final String PASSWORD; //store the user's account password
	public Connection dbConnect = null; //store the connection to close it later
    
    
	// Some example test data is shown here.  This is not a full list of all possible tests (i.e. competitors under the age of 5, over 18, etc.)
	// Tests will be done on a database with the same table names/attributes, but different data records.

	/**
	 * Main function, works as intended.
	 */
	public static void main(String[] args) {

        Registration myJDBC = new Registration("jdbc:mysql://localhost/competition","root","admin");
        myJDBC.initializeConnection();
        
        System.out.println(myJDBC.selectAllNames("competitor") + "\n");
        /*
        Example:
        Williams, Sophie
        Warren, Harper
        */
                
        System.out.println(myJDBC.selectAllNames("teacher") + "\n");
        /*
        Example:
        Estrada, Ethan
        Lee, Jasmine
        */

        System.out.println(myJDBC.showStudios() + "\n");
        /*
        Example:
        Harmony Inc.
        Melody Time
        Music Mastery
        */
        
        myJDBC.insertNewCompetitor("123", "Smyth", "Ali", 15, "Oboe", "0023");
        myJDBC.registerNewTeacher("0987", "Marasco", "Emily", "403-222-5656", "Marasco Music", "587-222-5656", "123 Main Street NW"); 

		System.out.println("_______\n");
		System.out.println(myJDBC.selectAllNames("competitor") + "\n");
		System.out.println(myJDBC.selectAllNames("teacher") + "\n");
		System.out.println(myJDBC.showStudios() + "\n");

        myJDBC.deleteCompetitor("123");
        myJDBC.deleteTeacher("0987");
		myJDBC.close();
        
    }

	/**
	 * Instantiates a new Registration.
	 *
	 * @param DBURL    the dburl
	 * @param USERNAME the username
	 * @param PASSWORD the password
	 */
	public Registration(String DBURL, String USERNAME, String PASSWORD) {
		this.DBURL = DBURL;
		this.USERNAME = USERNAME;
		this.PASSWORD = PASSWORD;
	}

	/**
	 * Initialize connection to database with MySQL using the URL, Username,
	 * and Password from the constructor arguments.
	 */
	public void initializeConnection() {
		try {
			dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Creates and executes a mySQL query to access every element of a
	 * user-defined table and returns their name in LName, Fname format.
	 *
	 * @param name the name of the table accessed
	 * @return string list of names
	 */
	public String selectAllNames(String name) {
		StringBuilder string = new StringBuilder();
		try {
			Statement selectNames = dbConnect.createStatement();
			ResultSet results = selectNames.executeQuery("SELECT * FROM " + name);
			while(results.next()) {
				string.append(results.getString("LName") + ", " + results.getString("FName") + "\n");
			}
			selectNames.close();
			results.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return string.toString().strip();
	}

	/**
	 * Creates and executes a mySQL query to access every element of the
	 * studio table and returns a list of their names.
	 *
	 * @return string list of studio names
	 */
	public String showStudios() {
		StringBuilder string = new StringBuilder();
		try {
			Statement selectStudios = dbConnect.createStatement();
			ResultSet results = selectStudios.executeQuery("SELECT * FROM studio");
			while(results.next()) {
				string.append(results.getString("Name") + "\n");
			}
			selectStudios.close();
			results.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return string.toString().strip();
	}

	/**
	 * Creates and executes multiple mySQL queries to insert a new competitor successfully.
	 * The first query checks if the teacher exists.
	 * The second query inserts the competitor's info into the competitor table.
	 *
	 * @param comp id    the competitor id
	 * @param lname      the last name
	 * @param fname      the first name
	 * @param age        the age
	 * @param instrument the instrument
	 * @param teacherid  the teacher id
	 */
	public void insertNewCompetitor(String compid, String lname, String fname, int age, String instrument, String teacherid) {
		if(age < 5 || age > 18) {
				throw new IllegalArgumentException("Competitor must be a child! (age 5-18)");
		}
		try {
			PreparedStatement tExistsCheck = dbConnect.prepareStatement("SELECT * FROM teacher WHERE TeacherID = ?");
			tExistsCheck.setString(1, teacherid);
			ResultSet results = tExistsCheck.executeQuery();
			if(!(results.next())) {
				throw new IllegalArgumentException("Teacher ID does not exist!");
			}
			tExistsCheck.close();
			results.close();
			
			PreparedStatement insertCompetitor = dbConnect.prepareStatement("INSERT INTO competitor (CompetitorID, LName, FName, Age, Instrument, TeacherID) VALUES (?,?,?,?,?,?)");
			insertCompetitor.setString(1, compid);
			insertCompetitor.setString(2, lname);
			insertCompetitor.setString(3, fname);
			insertCompetitor.setInt(4, age);
			insertCompetitor.setString(5, instrument);
			insertCompetitor.setString(6, teacherid);
			
			int rowsChanged = insertCompetitor.executeUpdate();
			insertCompetitor.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Creates and executes multiple mySQL queries to register a teacher successfully.
	 * The first query checks to see if the teacher already exists.
	 * The second query checks if the teacher's studio already exists.
	 * The third query inserts the studio's info into the studio table.
	 * The fourth query inserts the teacher's info into the teacher table.
	 *
	 * @param teacherid  the teacher id
	 * @param lname      the last name
	 * @param fname      the first name
	 * @param tphone     the teacher phone number
	 * @param studioname the studio name
	 * @param sphone     the studio phone number
	 * @param address    the address
	 */
	public void registerNewTeacher(String teacherid, String lname, String fname, String tphone, String studioname, String sphone, String address) {
		try {
			PreparedStatement tDuplicateCheck = dbConnect.prepareStatement("SELECT * FROM teacher WHERE TeacherID = ?");
			tDuplicateCheck.setString(1, teacherid);
			ResultSet results = tDuplicateCheck.executeQuery();
			if(results.next()) {
				throw new IllegalArgumentException("Teacher ID already exists!");
			}
			tDuplicateCheck.close();
			
			PreparedStatement studioCheck = dbConnect.prepareStatement("SELECT * FROM studio WHERE Name = ?");
			studioCheck.setString(1, studioname);
			results = studioCheck.executeQuery();
			if(!(results.next())) {
				PreparedStatement sInsert = dbConnect.prepareStatement("INSERT INTO studio (Name, Phone, Address) VALUES (?,?,?)");
				sInsert.setString(1, studioname);
				sInsert.setString(2, sphone);
				sInsert.setString(3, address);
				
				sInsert.executeUpdate();
				sInsert.close();
			}
			studioCheck.close();
			
			PreparedStatement tInsert = dbConnect.prepareStatement("INSERT INTO teacher (TeacherID, LName, FName, Phone, StudioName) VALUES (?,?,?,?,?)");
			tInsert.setString(1, teacherid);
			tInsert.setString(2, lname);
			tInsert.setString(3, fname);
			tInsert.setString(4, tphone);
			tInsert.setString(5, studioname);
			
			tInsert.executeUpdate();
			tInsert.close();
		}
		catch(SQLException ex) {                 
			ex.printStackTrace();
		}
	}

	/**
	 * Creates and executes a mySQL query to delete a competitor.
	 *
	 * @param id the id of the competitor to be deleted.
	 */
	public void deleteCompetitor(String id) {
		try {
			PreparedStatement deleteComp = dbConnect.prepareStatement("DELETE FROM competitor WHERE CompetitorID = ?");
			deleteComp.setString(1, id);
			
			deleteComp.executeUpdate();
			deleteComp.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Creates and executes a mySQL query to delete a teacher.
	 *
	 * @param id the id of the teacher to be deleted.
	 */
	public void deleteTeacher(String id) {
		try {
			PreparedStatement myStatement = dbConnect.prepareStatement("DELETE FROM teacher WHERE TeacherID = ?");
			myStatement.setString(1, id);
			
			myStatement.executeUpdate();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Gets dburl.
	 *
	 * @return the dburl
	 */
	public String getDburl() {
		return this.DBURL;
	}

	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.USERNAME;
	}

	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.PASSWORD;
	}

	/**
	 * Closes the database connection.
	 */
	public void close() {
		try {
			dbConnect.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}
