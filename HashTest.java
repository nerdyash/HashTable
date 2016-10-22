// File: HashTest.java

import java.lang.*;
import java.io.*;
import java.util.Scanner;

/**********************************************************************
* This class is a homework assignment #03;
**********************************************************************/
// use HashBag.java;


/**
 * class HashTest which tests HashBag class and works with Hash tables
 * HW-03, CS3
 * @author (your name)
 * @version (a version number or a date)
 */
public class HashTest
{
    public static final int H=100;
    public static final int M=10;

    public static void main(String[] args) throws IOException{



       //******************************************************************
       // (1) Generate a HASH TABLE randomly:
       //******************************************************************
    	File file = new File("C:\\Users\\User\\Desktop\\SUNY_Yash\\Suny sem 3\\Advanced Data Structure\\thakkary-02\\DataC.txt");
//    	FileWriter fw = new FileWriter(file,true);
//    	BufferedWriter bw = new BufferedWriter(fw);
    	PrintWriter pw = new PrintWriter(file);
    	float random = 0;
    	try{
	        System.out.println("1) GENERATE a random DATA set: \n=============================\n\nWriting random SSNs to file: \n\n");

	    	for(int i=0; i<M; i++){
		    	random = (float)((Math.random()*900000000)+100000000);
		        pw.write(" "+String.format("%.0f", random));
		        pw.write("\r\n");
		        System.out.println(String.format("%.0f", random));
	    	}
    	}
    	catch(Exception e){
    		System.out.println(e);
    	}
    	finally{
    		pw.close();
    	}
    	System.out.println("\n\n");
       //************************************************************
       // 2)READ SSN from file:
       //************************************************************
    	File file1 = new File("C:\\Users\\User\\Desktop\\SUNY_Yash\\Suny sem 3\\Advanced Data Structure\\thakkary-02\\DataP.txt");

    	Scanner sc = new Scanner(file1);
    	HashBag hb = new HashBag(H);
    	System.out.println("2) Read DATA from file & put in a HASH Table:\n===================================================\n\n");
    	int x = sc.nextInt();
    	while(sc.hasNextInt() && x != 0){
    		int temp = sc.nextInt();
    		if(temp == M){
    		x--;
    		}
    		hb.put(temp);
    		System.out.println("Key -> "+temp+",  HashValue -> "+ temp % H);

    	}
        sc.close();
        System.out.println("\n\n");
        //***************************************************************
        // 3) Print the HashTable
        //***************************************************************
    	Scanner sc1 = new Scanner(file1);
    	int[] table = new int[H];
    	System.out.println("3) DISPLAY the Hash Table: \n============================\n\n");
    	int x1 = sc1.nextInt();
    	while(sc1.hasNextInt()){
    		int temp = sc1.nextInt();
    		int index = hb.hasKey(temp);
    		table[index] = temp;
    	}
    	for(int i=0; i<H;i++){
    		System.out.println("t["+i+"] = "+ table[i]);
    	}
    	sc1.close();
    	System.out.println("\n\n");
        //***************************************************************
        //  4) SEARCH: Let the user ENTER a SSN: A. Search to see if A is in T.
        //  Print out A and the search result(if A is found or not and index of A if it is found)
        //***************************************************************
    	System.out.println("4) SEARCH for a KEY: \n==================== \n\n");
    	System.out.println("Enter the Value of a KEY(SSN) to SEARCH:\n");
    	Scanner sc2 = new Scanner(System.in);
    	int input = sc2.nextInt();
    	int hashValue = input % H;
        int index = hb.hasKey(input);
        if(index>=0){
        	System.out.println("Key = "+input+" -> Hash Value = "+hashValue);
        	if(index == hashValue){
        		System.out.println("T["+hashValue+"] = "+hb.readData(table[hashValue])+" ; w[] = true");
        	}
        	else{
        		while(hashValue != index+1){
        			if(hashValue>99){
        				hashValue = 0;
        			}
        			System.out.println("T["+hashValue+"] = "+hb.readData(table[hashValue])+" ; w[] = true");
        			hashValue++;

        		}
        	}
        	System.out.println("Search result : "+input+" is found at t["+index+"].");
        }
        else{
        	System.out.println("Search result : "+input+" is not found.");
        }
        //****************************************************************************
        //   5)	REMOVE: Let the user enter a SSN: B. Remove B if there is B in T.
        //      Print out B and the remove result(if B is found and removed or not and index where B is removed)
        //*****************************************************************************

        System.out.println("5) REMOVE a KEY:\n==============\n\nEnter the Value of a KEY(SSN) to REMOVE:\n\n");
        int removeNumber = sc2.nextInt();
        int delete = hb.remove(removeNumber);
        int hashValueDelete = removeNumber % H;

        if(delete>=0){
        	System.out.println("Key = "+removeNumber+" -> Hash Value = "+hashValueDelete);
        	if(hashValueDelete == delete){
    			System.out.println("T["+hashValueDelete+"] = "+removeNumber+" ; w[] = true");
        		table[delete]=0;
        		System.out.println("T["+hashValueDelete+"] = "+hb.readData(table[hashValueDelete])+" ; w[] = true");
        		
        	}
        	else{
        		while(hashValueDelete != delete+1){
        			if(hashValueDelete>99){
        				hashValueDelete = 0;
        			}
        			if(removeNumber == table[hashValueDelete]){
            			System.out.println("T["+hashValueDelete+"] = "+removeNumber+" ; w[] = true");

        			}
        			System.out.println("T["+hashValueDelete+"] = "+hb.readData(table[hashValueDelete])+" ; w[] = true");
        			hashValueDelete++;
        		}
        		table[delete]=0;

        	}
        	System.out.println("Remove result : "+removeNumber+" is removed at t["+delete+"].");
        }
        else{
        	System.out.println("Remove result : "+removeNumber+" is not found.");
        }

        //****************************************************************************
        //   6) ADD: Let the user enter a SSN: C. Insert/add C to Hash Table/array T.
        //*****************************************************************************

        System.out.println("6) ADD a KEY to the HASH Table:\n=============================\n\n");
        int add = sc2.nextInt();
        int addIndex = hb.put(add);
        table[addIndex] = add;
        System.out.println("Key = "+add+" -> Hash Value = "+add%H);
        System.out.println("Insert result : "+add+" is inserted at t["+addIndex+"].");

        //***************************************************************
        //  7) Print the HashTable
        //***************************************************************

        System.out.println("7) DISPLAY the NEW Hash Table:\n============================\n\n");
        for(int j=0; j<H; j++){
        	System.out.println("t["+j+"] = "+ table[j]);
        }
    }
}
