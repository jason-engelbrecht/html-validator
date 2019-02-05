package client;

import java.util.*;
import client.HTMLManager;
import html.*;

/**
 * 
 * @author Jason Engelbrecht
 * Date: 12/07/2018
 * Description: Tests the HTMLManager class basic methods:
 * add, removeAll and getTags
 *
 */
public class HTMLManagerTest {
	/**
	 * Runs test
	 * @param args
	 */
	public static void main(String[] args) {
		//<b>Hi</b><br/>
		//create tags
		Queue<HTMLTag> tags = new LinkedList<HTMLTag>();				
		tags.add(new HTMLTag("b", HTMLTagType.OPENING)); //<b>
		tags.add(new HTMLTag("b", HTMLTagType.CLOSING)); //</b>
		tags.add(new HTMLTag("br", HTMLTagType.SELF_CLOSING)); //<br/>		
		
		//create HTML manager w tags
		//print base tags
		HTMLManager manager = new HTMLManager(tags);
		System.out.println("Base: " + manager.getTags());
		System.out.println();
		
		HTMLTag test1 = new HTMLTag("a", HTMLTagType.OPENING); //<a>
		//test adding one item then removing it
		manager.add(test1); 	
		System.out.println("After add: " + manager.getTags());		
		manager.removeAll(test1);
		System.out.println("After removing all \"<a>\": " + manager.getTags());
		System.out.println();
		
		//test remove all - two items
		HTMLTag test2 = new HTMLTag("p", HTMLTagType.CLOSING); //</p>
		manager.add(test2);
		manager.add(test2);
		System.out.println("After add: " + manager.getTags());
		manager.removeAll(test2);
		System.out.println("After removing all \"</p>\": " + manager.getTags());
		System.out.println();
		
		//test remove all - multiple items, in the middle of list
		HTMLTag test3 = new HTMLTag("tr", HTMLTagType.SELF_CLOSING); //<br/>
		for(int i = 0; i < 5; i++)
			manager.add(test3);
		manager.add(test2);
		manager.add(test1);
		System.out.println("After add: " + manager.getTags());
		manager.removeAll(test3);
		System.out.println("After removing all \"<tr/>\": " + manager.getTags());
	}
}




