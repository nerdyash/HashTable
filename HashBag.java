
import java.lang.*;
import java.io.*;

/**
 * class HashBag: use open-address Hashing.
 * HW-03, CS3
 */
public class HashBag
{
    // instance variables
    private final int M=100;
    private int[] t = new int[M];
    private boolean[] w = new boolean[M]; // true means there used to be an element at t[i]
    private int size;

    /**
     * Constructor for class HashBag
     * @param  integer "N"
     * @return a Hash table with size N
     */
    public HashBag(int N)
    {
        // also initialize w[] to False, meaning nothing was there
        // put your code here
    	if(N <= 0){
    		throw new IllegalArgumentException("Capacity is nagative.");
    	}
    	size = N;

    }


   /**
     * method hashValue: calculate Hash value of an element key
     * @param  key,   a SSN
     * @return the Hash value = HashFunction(key)
     */
    private int hashValue(int key)
    {
    	// put your code here (define your Hash function here)
    	int arrSize = t.length;
		return Math.abs(key)%(arrSize);

    }

   /**
     * method readData: get/read the element content of a Hash Table
     * @param  "i" is the Hash value (index)
     * @return the content of "i" index or the Data whose Hash value = i
     */
    public int readData (int i)
    {
        // put your code here
    	int index = hasKey(i);

    	if(index == -1){
    		return 0;
    	}
    	else{
    		return t[index];
    	}
    }


    /**
     *  method PUT: add a new element "key" to this HashBag
     * @param  "key",   a SSN, value of the new element to be added
     * @return -1 (if can't add, i.e. full) or index "i" where key is placed (if added)
     */
    public int put(int key)
    {
        // put your code here
    	int index = hashValue(key);
    	
    	if(index != -1 && t[index] == 0){
    		
    		t[index] = key;
    		w[index] = true;
    		return index;
    	}
    	else if(t[index] != 0){
    		while(t[index] != 0){
    			if(index+1 == t.length){
    				index = 0;
    			}
    			else{
    				index++;
    			}
    		}
    		t[index]=key;
    		w[index] = true;

    		return index;
    	}
    	else{
    		throw new IllegalStateException("Table is full.");
    	}
    }

    /**
     *  method hasKey: search for an element key in this HashBag
     * @param  key,   a SSN, value of the element to search for
     * @return -1 (if not found) or index i where key is placed (if found)
     */
    public int hasKey (int key)
    {
         // put your code here
    	int count = 0;
    	int i = hashValue(key);
    	while(count < t.length && (w[i])){
    		if(key == t[i]){
    			return i;
    		}

    		count++;
    		if(i+1 == t.length){
    			i=0;
    		}
    		else{
    			i++;
    		}

    	}
    	return -1;
      }

    /**
     *  method remove: delete an element key in this HashBag
     * @param  key,   a SSN, value of the element to be removed
     * @return -1 (if not deleted or not found) or index i where key was deleted (if found)
     */
    public int remove(int key)
    {
        // put your code here
    	int index = hasKey(key);
    	int ans;
    	if(index != -1){
    		ans = index;
    		t[index] = 0;
    		size--;
    		return ans;
    	}
    	return index;
    }
}
