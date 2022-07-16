package edu.ucalgary.ensf409;

import java.io.*;
import java.util.*;

public class QuizThree implements Serializable {
  private TreeMap <Integer, String> balancedTree;
  private String fileName;
  private final static int PADDING = 10;
  private static final String DIR = "data";
  static final long serialVersionUID = 20210324;

  // Default constructor
  public QuizThree() { 
    this.balancedTree = new TreeMap<Integer, String>();
  }

  // One argument constructor
  public QuizThree(String fileName) {
    setFileName(fileName);
    this.balancedTree = new TreeMap<Integer, String>();
  }
	
  // Return the fileName, which is a relative path
  public String getFileName() {
      return this.fileName;
  }
  
  public TreeMap <Integer, String> getBalancedTree() {
	  return this.balancedTree;
  }
  
  // Set the fileName
  public void setFileName(String fileName) {
    this.fileName = getRelativePath(fileName);
  }

  // Return the balancedTree String elements as a left-first
  // traversal put into a string array
  public String[] asStringArray() {
    ArrayList<String> tmp = new ArrayList<String>();

    if (balancedTree.isEmpty() == false) {
       for (Map.Entry<Integer, String> e : balancedTree.entrySet()) {
          tmp.add(e.getValue());
       }
    }

    return tmp.toArray(new String[tmp.size()]);
  }

  // Add an element to the balancedTree, specifying location
  // Do not allow an existing element to be overwritten
  // Return false if we cannot add, true if we can
  // No negative locations allowed
  public boolean addElement(int location, String value) {
	if (balancedTree.isEmpty() == false) {
      if (balancedTree.containsKey(location)) {
        return false; 
      }
    }
	addOrOverwriteElement(location, value);
	return true;
  }

  // Add an element to the balancedTree, specifying location
  // If the element already exists, it may be overwritten
  // No negative locations allowed
  public void addOrOverwriteElement(int location, String value) {
    if (location < 0) {
      System.err.println("Location must be a positive number.");
      System.exit(1);
    }

    this.balancedTree.put(location, value);
  }

  // Add an element to the end of the balancedTree
  public void addElement(String value) {
	int newKey = getLargest() + PADDING;
    addOrOverwriteElement(newKey, value);
  }

  // Return the lowest numbered key for a specific value, -1 if not
  // found
  public int getElement(String value) {
    if (this.balancedTree.containsValue(value)) {
       for (Map.Entry<Integer, String> e : balancedTree.entrySet()) {
          if (e.getValue().equals(value)) {
            return e.getKey();
          }
       }
    }
    return -1;
  }

  // Return the value for a specific key, empty String otherwise
  public String getElement(int location) {
    if (this.balancedTree.containsKey(location)) {
      return this.balancedTree.get(location);
    }
    return "";
  }

  // Store the name of the file we are trying to access,
  // then call the zero-argument version of the method to
  // write out the file.
  public void writeFile(String fn) {
    setFileName(fn);
    writeFile();
  }

  // Write out the file as a serialized object
  public void writeFile() {
    // Ensure fileName was set
    if (this.fileName == null) {
      System.err.println("FileName must be specified with setter or method call.");
      System.exit(1);
    }

    // If the directory does not exist, create it. If it does exist, make sure
    // it is a directory.
    File directory = new File(DIR);
    try {
      if (! directory.exists()) {
        directory.mkdir();
      } else {
        if (! directory.isDirectory()) {
          System.err.println("File " + DIR + " exists but is not a directory.");
          System.exit(1);
        }
      }
    }
    catch (Exception e) {
      System.err.println("Unable to create directory " + DIR + ".");
      System.err.println(e.toString());
      System.exit(1);
    }

    // Try to write out file
    ObjectOutputStream out = null;
    try {
      out = new ObjectOutputStream(new FileOutputStream(this.fileName));
      out.writeObject(this);
    } 

    catch (Exception e) {
      System.err.println("I/O error opening/writing file " + this.fileName);
      System.err.println(e.toString());
      closeFile(out);
      System.exit(1);
    }

    closeFile(out);
  }

  // Variation of readFile() which sets fileName before executing readFile()
  public void readFile(String fileName) {
    setFileName(fileName);
    readFile();
  }


  // Read in the file
  public void readFile() {
    // Ensure fileName was set
    if (this.fileName == null) {
      System.err.println("FileName must be specified with setter or method call.");
      System.exit(1);
    }

    // Ensure file exists
    File file = new File(this.fileName);
    if (! file.exists()) {
      System.err.println("File " + file + " could not be opened as it does not exist.");
      System.exit(1);
    }

    // Read in the file and set balancedTree
    ObjectInputStream in = null;
    QuizThree tmp = null;
    try {
      in = new ObjectInputStream(new FileInputStream(this.fileName));
      tmp = (QuizThree)in.readObject(); 
    }

    catch (Exception e) {
      System.err.println("I/O error opening/reading file " + this.fileName + ".");
      System.err.println(e.toString());
      closeFile(in);
      System.exit(1);
    }

    // Close file.
    // Store the new balancedTree as our balancedTree; other member
    // data is discarded
    closeFile(in);
    this.balancedTree = tmp.balancedTree;
  }






  // Return the largest key currently used in the balancedTree
  private int getLargest() {
    int tmp = PADDING;

    if (balancedTree.isEmpty() == false) {
      for (Map.Entry<Integer, String> e : balancedTree.entrySet()) {
        if(e.getKey() > tmp) {
			tmp = e.getKey();
		}
      }
    }
    return tmp;
  }

  // Give us the full relative path to the filename, OS independently
  private String getRelativePath(String filename) {
    File path = new File(DIR);
    File full = new File(path, filename);
    return full.getPath();
  }

  // Try to close an input file, exit(1) if not possible
  private void closeFile(ObjectInputStream file) {
    if (file != null) {
      try {
        file.close();
      }

      catch (Exception e){
        System.err.println("I/O error closing input file " + this.fileName + ".");
        System.err.println(e.toString());
        System.exit(1);
      } 
    }
  }

  // Try to close an output file, exit(1) if not possible
  private void closeFile(ObjectOutputStream file) {
    if (file != null) {
      try {
        file.close();
      }

      catch (Exception e){
        System.err.println("I/O error closing output file " + this.fileName + ".");
        System.err.println(e.toString());
        System.exit(1);
      } 

    }
  }
}


