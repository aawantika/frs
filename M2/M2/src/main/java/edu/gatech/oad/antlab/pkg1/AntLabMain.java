package edu.gatech.oad.antlab.pkg1;


// import edu.cs2335.antlab.pkg3.*;
import edu.gatech.oad.antlab.person.*;
import edu.gatech.oad.antlab.pkg2.*;


/**
 * CS2335 Ant Lab
 *
 * Prints out a simple message gathered from all of the other classes
 * in the package structure
 */
 public class AntLabMain {
    
    /**antlab11.java message class*/
    private AntLab11 ant11;
    
    /**antlab12.java message class*/
    private AntLab12 ant12;
    
    // /**antlab21.java message class*/
    // private AntLab21 ant21;
    
    // /**antlab22.java message class*/
    // private AntLab22 ant22;
    
    // /**antlab31 java message class which is contained in a jar resource file*/
    // private AntLab31 ant31;
    
    
    /**
     * the constructor that intializes all the helper classes
     */
    public AntLabMain () {
    	
        ant11 = new AntLab11();
        ant12 = new AntLab12();
        // ant21 = new AntLab21();
        // ant22 = new AntLab22();
        // ant31 = new AntLab31();
        
    }
    
    /**
     * gathers a string from all the other classes and prints the message
     * out to the console     
     * 
     */
    public void printOutMessage() {
    	
        String toPrint = 
        	ant11.getMessage() + ant12.getMessage(); + ant21.getMessage()
      		+ ant22.getMessage(); // + ant31.getMessage();
      	
	//Person1 name/GT ID  
	Person1 p1 = new Person1("Prav Tadikonda");
	toPrint += p1.toString("ptadikonda3");
	//Person2 name/GT ID
	Person2 p2 = new Person2("Jason Bires");
	toPrint += p2.toString("jbires6");
	//Person3 name/GT ID
	Person3 p3 = new Person3("Shannon Nguyen");
	toPrint += p3.toString("snguyen47");
        //Person4 name/GT ID
        Person4 p4 = new Person4("Aawantika Sahu");
        toPrint += p4.toString("asahu9");
        //Person5 name/GT ID
        Person5 p5 = new Person5("HyunJong Lee");
        toPrint += p5.toString("hlee652");
		  
        System.out.println(toPrint);
    }
     
    
    /**
     * entry point for the program
     */
     public static void main(String[] args) {
        
        new AntLabMain().printOutMessage();
        
     } 
    
    
    
    
 } 
