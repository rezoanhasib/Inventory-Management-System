import java.util.InputMismatchException;
import java.util.Scanner; 

/**
* The <CODE>DepartmentStore</CODE> Java application is the user interface program that has the main method 
* @author 
* Rezoan Hasib
* <A HREF="mailto:hasib.rezoan@gmail.com"> (hasib.rezoan@gmail.com) </A>
*/
public class DepartmentStore{
	/**
	 * The main method to interact with the user by asking the choice. Depending on the choice, the method goes to 
	 * the helper classes and the helper methods in order to complete the relevant process of the choice. 
	 * This method exits when the user inputs a "Q" as the choice. Other choices are: c,i,l,m,o,p,r,u. 
	 * @param args
	 */
	public static void main(String[] args)throws Exception 
	{
		boolean notExit=true;
		String choice,name,rfid,originalLocation,newLocation,location,cart,userRfid;
		double price,totalCost;
		ItemList currentList=new ItemList(); 
		Scanner input= new Scanner(System.in); 

		while(notExit)
		{
			
			System.out.println("Welcome!! Here are the options: ");
			System.out.println("\nC - Clean store");
			System.out.println("I - Insert an item into the list");
			System.out.println("L - List by location");
			System.out.println("M - Move an item in the store");
			System.out.println("O - Checkout");
			System.out.println("P - Print all items in store");
			System.out.println("R - Print by RFID tag number");
			System.out.println("U - Update inventory system");
			System.out.println("Q - Exit the program\n"); 
			System.out.print("Please select an option: ");

			choice=input.nextLine();
			choice=choice.toUpperCase(); 
			switch(choice)
			{
				case"I": 
				{
					System.out.print("\nEnter the name: ");
					name=input.nextLine(); 
					//checking if name has more than 20 characters 
					while(name.length()>20)
					{
						System.out.print("The maximum name LENGTH can be 20 characters!!");
						System.out.print("\nPlease enter a new name: ");
						name=input.nextLine(); 
					}
					System.out.print("Enter the RFID of 9 characters (including Digit or letter):");
					rfid=input.nextLine();
					rfid=rfid.toUpperCase(); 
					System.out.println(rfid);
	
					int a=0;

					while(Character.isLetter(rfid.charAt(a))||Character.isDigit(rfid.charAt(a))||(rfid.length()!=9))
					{		
						if (Character.isLetter(rfid.charAt(a)))
						{
							char x=rfid.charAt(a); 
						
						if (((x!='A')&&(x!='B')&&(x!='C')&&(x!='D')&&(x!='E')&&(x!='F')&&!(Character.isDigit(x)))||rfid.length()!=9)
							{
								System.out.println("Any letter RANGE for RFID is (A-F) and RFID is 9 characters!!");
								System.out.print("Please (for the love of GOD!) enter a correct RFID(including Digit or letter):");
								rfid=input.nextLine();
								rfid=rfid.toUpperCase(); 
								a=0; 	
							}
							else
							{
								if(a<rfid.length()-1)
									a++;
								else break; 
							}
						}
							else if(rfid.length()!=9)
							{
								System.out.print("RFID length should be 9 characters!!");
								System.out.print("\nPlease enter a new RFID(Digit or letter):");
								rfid=input.nextLine();
								rfid=rfid.toUpperCase(); 
								a=0; 
							}
							else if(Character.isDigit(rfid.charAt(a)))
							{
								if(a<rfid.length()-1)
									a++; 
								else break; 
							}
					}		
					System.out.print("Enter the original location(6 characters starting with s): ");
					originalLocation=input.nextLine();
					originalLocation=originalLocation.toLowerCase(); 
					
					while((originalLocation.charAt(0)!='s')||(originalLocation.length()!=6)) 
						{
							if(originalLocation.charAt(0)!='s')
							{
								System.out.print("The first character of original location has be 's or S': ");
								System.out.print("\nEnter the original location: ");
								originalLocation=input.nextLine();
								originalLocation=originalLocation.toLowerCase(); 
							}
							else
							{
								System.out.print("The original location should have 6 characters!!");
								System.out.print("\nEnter the original location: ");
								originalLocation=input.nextLine(); 
								originalLocation=originalLocation.toLowerCase(); 
							}
						}						
					System.out.print("\nEnter the price:" );
					boolean valid=false;
					price=0; 
					while(!valid)
					{	try
						{
							price=input.nextDouble();
							valid = true;
							
						} catch (InputMismatchException e)
							{
								input.nextLine();
								valid = false;
								System.out.println("Price needs to be a digit value!!");
								System.out.println("Please enter the price again: "); 
							}	
					}
					currentList.insertInfo(name,rfid,price,originalLocation); 
					break; 
				}
				case"M": 
				{
					System.out.println("Enter the RFID: ");
					rfid=input.nextLine();
					rfid=rfid.toUpperCase(); 
					System.out.println("Enter the original location: ");
					originalLocation=input.nextLine(); 
					originalLocation=originalLocation.toLowerCase(); 
					while(originalLocation.charAt(0)=='s'&& originalLocation.length()!=6)
					{
						System.out.println("Original location needs to be 6 characters long!!");
						System.out.println("Please enter again: ");
						originalLocation=input.nextLine(); 
						originalLocation=originalLocation.toLowerCase(); 
					}
					System.out.println("Enter the new location: ");
					newLocation=input.nextLine(); 
					newLocation=newLocation.toLowerCase(); 
					while(newLocation.charAt(0)=='s'&& newLocation.length()!=6)
					{
						System.out.println("New location needs to be 6 characters long!!");
						System.out.println("Please enter again: ");
						newLocation=input.nextLine(); 
						newLocation=newLocation.toLowerCase(); 
					}
					
					if(originalLocation=="out"||newLocation=="out")
					{
						System.out.println("OUT is not a valid entry for original location or new location"); 
						System.out.println("Request ignored!!");
						break; 
					}
			
					boolean valid=currentList.moveItem(rfid,originalLocation,newLocation); 
					
					if(valid==false)
					{
						System.out.println("RFID- "+rfid+" is not found in the store!!");
						break; 
					}
					
					System.out.println("RFID- "+rfid+" is moved from "+originalLocation+" to "+newLocation);
					break; 
					
				}
				case"P": 
				{	
					System.out.printf("%43s%14s\n","Original","Current");
					System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","Item Name","RFID","Location","Location","Price"); 
					System.out.printf("%-22s%-13s%-15s%-15s%-5s\n","---------","----","--------","--------","-----");
					currentList.printAll(); 
					break; 	
				}
				case"Q": 
				{
					System.out.print("Program exiting normally...Goodbye!!");
					notExit=false; 
					break; 
				}
				case"L": 
				{
					System.out.println("Enter the location: ");
					location=input.nextLine();
					location=location.toLowerCase(); 
					currentList.printByLocation(location);
					break; 	
				}
				case"C": 
				{
					currentList.cleanStore();
					break; 
				}
				case"O": 
				{
					System.out.println("Enter the cart number: ");
					cart=input.nextLine(); 
					totalCost=currentList.checkOut(cart); 
					System.out.println("The total cost for all merchandise in cart "+cart+" was $"+totalCost+"\n");
					break; 
				}
				case"U": 
				{
					System.out.println("\nThe following item(s) have been removed from the system: "); 
					currentList.removeAllPurchased();
					break; 
				}
				case"R": 
				{
					System.out.println("Enter the RFID number: ");
					userRfid=input.nextLine(); 
					currentList.PrintByRfid(userRfid);
					break; 
				}
				default: 
				{
					System.out.print("Invalid entry!! Please try again: \n\n"); 
					break; 
				}
			}
		}
		//closing the scanner 
		input.close();
	}

}
