package client;

import html.*;
import java.util.*;

/**
 * 
 * @author Jason Engelbrecht
 * Date: 12/06/2018
 * Description: Manages a queue of HTML tags, allowing user
 * to add, remove, and fix HTML markup to ensure there is 
 * proper opening and closing tags in the correct location.
 *
 */
public class HTMLManager {
  
     private Queue<HTMLTag> page;
     
     /**
      * Constructor
      * Throws IllegalArgumentException if page is null
      * @param page - Queue of HTML tags
      */
     public HTMLManager(Queue<HTMLTag> page) {
    	 if(page == null)
    		 throw new IllegalArgumentException();
    	 this.page = new LinkedList<HTMLTag>(page);
     }
     
     /**
      * Adds an HTML tag to the back of queue
      * Throws IllegalArgumentException if tag is null
      * @param tag - HTML tag to be added
      */
     public void add(HTMLTag tag) {
    	 if(tag == null)
    		 throw new IllegalArgumentException();
    	 page.add(tag); 
     }
     
     /**
      * Removes all instances of an HTML tag
      * Throws IllegalArgumentException if tag is null
      * @param tag - tag(s) to be removed
      */
     public void removeAll(HTMLTag tag) {
    	 if(tag == null) 
    		 throw new IllegalArgumentException();
    	 for(int i = 0; i < page.size(); i++) {
    		 page.remove(tag);
    	 }
     }
     
     /**
      * @return - HTML tags
      */
     public Queue<HTMLTag> getTags() {
    	 return page;
     }
     
     /**
      * Fixes HTML markup to ensure there are properly 
      * placed opening, closing and self-closing tags 
      */
     public void fixHTML() {
 		//create stack for opening tags
    	 Stack<HTMLTag> openTags = new Stack<>();

    	 //loop through tags to evaluate
    	 for(int i = 0; i < page.size(); i++) {
    		 //get a tag
    		 HTMLTag tag = page.remove();
    		 
    		 //if tag self closing add it back to queue
    		 if(tag.isSelfClosing()) {
    			  page.add(tag);
    		 }    			
    		 //if tag is opening add it back to queue
    		 //and push it to the top of the stack
    		 else if(tag.isOpening()) {
    			 page.add(tag);
    			 openTags.push(tag);
    		 }
    		 //if tag is closing - evaluate
    		 else if(tag.isClosing()) {      			 
    			 //if stack has open tag to compare
    			 if(!openTags.isEmpty()) {
    				 //get tag
    				 HTMLTag check = openTags.pop();  
    				 //if the type of tag matches, add back to queue
    				 if(check.matches(tag)) {
    					  page.add(tag);
    				 }
    				 //if it doesn't match, get it a match and add it to the queue
    				 else if(!check.matches(tag)) {
    					 tag = check.getMatching();
    					 page.add(tag);
    				 }
    			 }			 
    		 }
    	 }
    	    	 
    	 //go through stack making sure any remaining
    	 //open tags have matching closing tags
    	 while(!openTags.isEmpty()) {
    		 HTMLTag check = openTags.pop();
    		 page.add(check.getMatching());
    	 }
 	}
     
}
