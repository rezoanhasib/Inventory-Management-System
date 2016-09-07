/**
* The <CODE>ItemList</CODE> Java application creates
* a list that has node(s) containing different items. 
* @author 
* Rezoan Hasib
* <A HREF="mailto:hasib.rezoan@gmail.com"> (hasib.rezoan@gmail.com) </A>
*/
public class ItemList {
	
	private ItemInfo data; 
	private ItemInfoNode head,tail,tempNode,cursor,cart; 
	/**
	 * Default constructor that creates a list with no items in it 
	 * @postcondition
	 * 		After this method, the head, tail and cursor are all null 
	 */
	public ItemList() 
	{
		head=null; 
		tail=null; 
		cursor=null; 
	}
	/**
	 * Method to obtain the length of any list 
	 * @return
	 * 		the integer length of selected list
	 */
	public int getLength()
	{
		int nodeNumber=0; 
		ItemInfoNode newNode=head;  
		while(newNode!=null)
		{
			nodeNumber++; 
			newNode=newNode.getNext(); 
			//will this command work as well? newNode.setNext(newNode.getNext());
		}
		return nodeNumber; 
	}
	/**
	 * Method to print all the items in a list 
	 * @postcondition
	 * 		All the items in the list at the current time is printed on the screen 
	 */
	
	//OOC- O(n) because this method traverses all the nodes in the list to print all the items  
	public void printAll()
	{	
		cursor=head; 
		while(cursor!=null)
		{
			System.out.printf("%-22s%-13s%-15s%-15s%-5s\n",cursor.getInfo().getName(),cursor.getInfo().getRfidTagNumber(),cursor.getInfo().getOriginalLocation(),cursor.getInfo().getCurrentLocation(),cursor.getInfo().getPrice()); 
			cursor=cursor.getNext(); 
		}
		System.out.println("\n");
	}
	/**
	 * Method to add an item in the list in sorted order 
	 * @param name
	 * 		the name of the item to be added 
	 * @param rfidTag
	 * 		the rfid of the item to be added 
	 * @param price
	 * 		the price of the item to be added 
	 * @param initPosition
	 * 		the original position of the item to be added 
	 * @postcondition 
	 * 		the user input item has been inserted in the list in sorted order 
	 */
	
	//OOC- O(n) because it traverses the whole list in order to find the suitable position in the list to insert a new item 
	public void insertInfo(String name,String rfidTag,double price,String initPosition)
	{
		data=new ItemInfo(); 
		data.setName(name);
		data.setRfidTagNumber(rfidTag);
		data.setPrice(price); 
		data.setOriginalLocation(initPosition); 
		
		tempNode=new ItemInfoNode(); 
		tempNode.setInfo(data);
		
		if(head==null)	
		{
			tempNode.setNext(null);
			tempNode.setPrev(null);
			head=tail=cursor=tempNode; 
		}
		else if(head.equals(tail))//can I use the '=' sign to compare in here? 		
		{
			cursor=head; 
			if(String.valueOf(rfidTag).compareTo(String.valueOf(head.getInfo().getRfidTagNumber()))<0)
			{	
				cursor.setPrev(tempNode);
				tempNode.setNext(cursor);
				tempNode.setPrev(null);
				head=cursor=tempNode; 	
			}
			else 	 
			{
				tempNode.setPrev(cursor);
				cursor.setNext(tempNode); 
				tempNode.setNext(null);
				tail=tempNode; 
			}	
		}
		else 
		{
			ItemInfoNode newNode=head; 
			while(newNode!=null)
			{
				if(String.valueOf(rfidTag).compareTo(String.valueOf(newNode.getInfo().getRfidTagNumber()))<0)
				{
					tempNode.setPrev(newNode.getPrev());
					if(newNode!=head)
						newNode.getPrev().setNext(tempNode);
					newNode.setPrev(tempNode);
					tempNode.setNext(newNode);
					
					if(newNode==head)
						head=tempNode; 
					
					break; 
				}
				else newNode=newNode.getNext(); 
			}
			
			if(newNode==null)
			{
				tempNode.setPrev(tail); 
				tail.setNext(tempNode); 
				tempNode.setNext(null); 
				tail=tempNode;
			}
				
		}
	}
	/**
	 * Method change the location of an item depending on the current status 
	 * @param rfidTag
	 * 		rfid of the item to be moved 
	 * @param source
	 * 		original location of the item to be moved 
	 * @param dest
	 * 		the updated location of the item to be moved 
	 * @return
	 * 		whether any items status was updated or not 
	 * @throws IllegalArgumentException
	 * 		Informs if proper rfid was not inputed by the user 
	 * @postcondition 
	 * 		the item with a valid rfid is moved to its current location 
	 * @precondition 
	 * 		the input rfid has to match with an existing rfid in the list at current time 
	 */
	
	//OOC- O(n) because this method traverses the whole list in the worst case to find an item with the given rfid 
	public boolean moveItem(String rfidTag, String source, String dest) throws IllegalArgumentException
	{
		cursor=head; 
		
		if(dest.charAt(0)!='s' && dest.charAt(0)!='c' && dest!="out")
			throw new IllegalArgumentException("Destination is invalid!!");
		if(rfidTag.length()!=9)
			throw new IllegalArgumentException("RFID entered is invalid!!");
		
		while(cursor!=null)
		{
			//why this did not work here? if(cursor.getInfo().getRfidTagNumber()==rfidTag)
			if(cursor.getInfo().getRfidTagNumber().equals(rfidTag))
				break; 
			cursor=cursor.getNext(); 
		}
		if(cursor==null)
			return false;
		
		cursor.getInfo().setCurrentLocation(dest);
			return true; 
	}
	/**
	 * Method to print all the items in a specified location 
	 * @param location
	 * 		the location of which all the items to be printed 
	 * @postcondition 
	 * 		all the items in the given location are printed on the screen 
	 * @precondition 
	 * 		the input location has to match with an existing location in the list at current time 
	 */
	
	//00C- O(n) because it traverses the whole list in order to find the total items in the given location
	public void printByLocation(String location) throws IllegalArgumentException 
	{
		cursor=head;
		int item=0; 
		System.out.printf("%43s%14s\n","Original","Current");
		System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","Item Name","RFID","Location","Location","Price"); 
		System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","---------","----","--------","--------","-----");
		
		while(cursor!=null)
		{
			if(cursor.getInfo().getCurrentLocation().equals(location))
			{
				System.out.printf("%-22s%-13s%-15s%-15s%-5s\n",cursor.getInfo().getName(),
						cursor.getInfo().getRfidTagNumber(),cursor.getInfo().getOriginalLocation(),
						cursor.getInfo().getCurrentLocation(),cursor.getInfo().getPrice()); 
				item++;
				cursor=cursor.getNext(); 
			}
		}
		if(item==0)
			System.out.println("There are no items in this location at this time!!\n"); 
	}
	/**
	 * Method to move any item in the cart to its original location 
	 */
	
	//00C- O(n) because it traverses the whole list to find a match in the worst case when all the items of the list 
	//are in the cart 
	public void cleanStore()
	{
		cursor=head; 
		int itemMoved=0; 
		
		System.out.println("The following item(s) have been moved back to their original locations:"); 
		System.out.printf("\n%43s%14s\n","Original","Current");
		System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","Item Name","RFID","Location","Location","Price"); 
		System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","---------","----","--------","--------","-----");
		
		while(cursor!=null)
		{
			if((!((cursor.getInfo().getOriginalLocation()).equals(cursor.getInfo().getCurrentLocation()))) &&
					(cursor.getInfo().getCurrentLocation().charAt(0)!='c')&&
					(cursor.getInfo().getCurrentLocation()!="out"))
			{
				System.out.printf("%-22s%-13s%-15s%-15s%-5s\n",cursor.getInfo().getName(),
						cursor.getInfo().getRfidTagNumber(),cursor.getInfo().getOriginalLocation(),
						cursor.getInfo().getCurrentLocation(),cursor.getInfo().getPrice()); 
				cursor.getInfo().setCurrentLocation(cursor.getInfo().getOriginalLocation());
				itemMoved++; 
			}
			cursor=cursor.getNext(); 
		}
		if(itemMoved==0)
			System.out.println("There are no items available to be moved from current to original location");
	}
	/**
	 * Method to checkout all the items in a given cart 
	 * @param cartNumber
	 * 		the cart of which all items are to be removed 
	 * @return
	 * 		a true if any item were checked out, false otherwise 
	 * @throws IllegalArgumentException
	 * 		informs if the cart number is not found in the list at current time 
	 * @precondition 
	 * 		the input cart number needs to be present already in the list at current time 
	 * 	@postcondition 
	 * 		the item with given cart has been checked out and its current location is "out"
	 */
	
	//00C- O(n) because it traverses the whole list in the worst case in order to find the items in the given cartNumber 
	public double checkOut(String cartNumber) throws IllegalArgumentException 
	{
		cursor=head; 
		int itemCheckedOut=0; 
		double totalCost=0; 
		
		System.out.printf("\n%43s%14s\n","Original","Current");
		System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","Item Name","RFID","Location","Location","Price"); 
		System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","---------","----","--------","--------","-----");
		while(cursor!=null)
		{
			if(cursor.getInfo().getCurrentLocation().equals(cartNumber))
			{
				System.out.printf("%-22s%-13s%-15s%-15s%-5s\n",cursor.getInfo().getName(),cursor.getInfo().getRfidTagNumber(),cursor.getInfo().getOriginalLocation(),cursor.getInfo().getCurrentLocation(),cursor.getInfo().getPrice()); 
				cursor.getInfo().setCurrentLocation("out");
				itemCheckedOut++;
				totalCost+=cursor.getInfo().getPrice(); 
			}
			cursor=cursor.getNext(); 	
		}
		if(itemCheckedOut==0)
			throw new IllegalArgumentException("Not a valid location for a cart at this time!!"); 
			
		return totalCost; 
	}
	/**
	 * Method to remove all items that were checked out from the list 
	 */
	//00C- O(n) because this method traverses the whole list in the worst case if all the items in the list are checked out 
	public void removeAllPurchased()
	{
		cursor=head; 
		int nodesRemoved=0; 
		
		System.out.printf("\n%43s%14s\n","Original","Current");
		System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","Item Name","RFID","Location","Location","Price"); 
		System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","---------","----","--------","--------","-----");
		
		while(cursor!=null)
		{
			//== or .equals method is a better suit here? 
			if(cursor.getInfo().getCurrentLocation()=="out")
			{
				System.out.printf("%-22s%-13s%-15s%-15s%-5s\n",cursor.getInfo().getName(),
						cursor.getInfo().getRfidTagNumber(),cursor.getInfo().getOriginalLocation(),
						cursor.getInfo().getCurrentLocation(),cursor.getInfo().getPrice()); 
				
				removeNode(cursor); 
				nodesRemoved++; 
			}
			cursor=cursor.getNext(); 		
		}
		if(nodesRemoved==0)
			System.out.println("No items were removed at this time!!");	
	}
	/**
	 * Helper method removeAllPurchased method to remove an item from the list 
	 * @param cursor
	 * 		The node which needs to be removed from the list 
	 */
	
	//This is a helper method for the the method removeAllPurchased 
	//00C= O(1) because the node to be deleted are already in the argument and 
	//since its a doubly linked list, the next and previous nodes can be found from the argument node links 
	public void removeNode(ItemInfoNode cursor)
	{
		if(cursor==head)
		{
			if(head==tail)
			{
				head=tail=null; 
			}
			else if(head.getNext().getNext()==null)
			{
				head.getNext().setPrev(null);
				head=tail; 
			}
			else
			{
				head.getNext().setPrev(null);
				head=head.getNext(); 
			}
		}
		else if(cursor==tail)
		{
			if(head==tail)
			{
				head=tail=null; 
			}
			else if(head.getNext().getNext()==null)
			{
				cursor.getPrev().setNext(null);
				head=tail; 
			}
			else
			{
				tail=cursor.getPrev(); 
				cursor.getPrev().setNext(null);
			}
		}
		else
		{
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
		}
	}
	/**
	 * Method to print all the items of a particular rfid tag 
	 * @param rfid
	 * 		the rfid tag number of which all the items are to be printed 
	 * @precondition 
	 * 		the input rfid needs to exist in the list already at current time 
	 * @postcondition 
	 * 		all the itmes with the given rfid is printed on the screen 
	 */
	//OOC= O(n) because in the worst case, it traverses the whole list to find a item with the given rfid 
	public void PrintByRfid(String rfid)
	{
		cursor=head; 
		int rfidFound=0; 
		
		System.out.printf("\n%43s%14s\n","Original","Current");
		System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","Item Name","RFID","Location","Location","Price"); 
		System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","---------","----","--------","--------","-----");
		
		while(cursor!=null)
		{
			if(cursor.getInfo().getRfidTagNumber().equals(rfid))
			{
				System.out.printf("%-22s%-13s%-15s%-15s%-5s\n",cursor.getInfo().getName(),cursor.getInfo().getRfidTagNumber(),cursor.getInfo().getOriginalLocation(),cursor.getInfo().getCurrentLocation(),cursor.getInfo().getPrice()); 
				rfidFound++; 
			}
			cursor=cursor.getNext(); 
		}
		if(rfidFound==0)
		{
			System.out.println("There are no item with the given RFID at this time!!");
		}
	}

}
	

