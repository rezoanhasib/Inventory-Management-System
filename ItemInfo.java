/**
* The <CODE>ItemInfo</CODE> Java application creates
* an item that has a name, price, rfid tag number, original location and a current location. 
* @author 
* Rezoan Hasib
* <A HREF="mailto:hasib.rezoan@gmail.com"> (hasib.rezoan@gmail.com) </A>
*/
public class ItemInfo {
	
	private String name; 
	private double price; 
	private String rfidTagNumber;  
	private String originalLocation;  
	private String currentLocation; 
	/**
	 * Default constructor to create an item 
	 */
	public ItemInfo()
	{}
	/**
	 * This method returns the name of the item
	 * @return
	 * 		the name of selected item 
	 */
	public String getName()
	{
		return this.name; 
	}
	/**
	 * Method to return the price of an item 
	 * @return
	 * 		The price of selected item 
	 */		
	public double getPrice()
	{
		return this.price; 
	}
	/**
	 * Method to return the rfid tag number of an item 
	 * @return
	 * 		the rfid tag number of seleced item 
	 */		
	public String getRfidTagNumber()
	{
		return this.rfidTagNumber; 
	}
	/**
	 * Method to return the original location of an item 
	 * @return
	 * 		the original location of selected item 
	 */
	public String getOriginalLocation()
	{
		return this.originalLocation; 
	}
	/**
	 * Method to return the current location of an item 
	 * @return
	 * 		the current location of selected item 
	 */
	public String getCurrentLocation()
	{
		return this.currentLocation; 
	}
	/**
	 * Method to define the name of an item 
	 * @param name
	 * 		The name to be set
	 * @postcondition
	 * 		The name of the item is set  
	 */
	public void setName(String name)
	{
		this.name=name; 
	}
	/**
	 * Method to define the price of an item 
	 * @param price
	 * 		the price of the item 
	 * @postcondition
	 * 		the price of the item is set 
	 */
	public void setPrice(double price)
	{
		this.price=price;
	}
	/**
	 * Method to define the rfid tag of an item 
	 * @param rfidTagNumber
	 * 		the rfid tag number to be set 
	 * @postcondition
	 * 		the rfid tag number of the item is set 
	 */
	public void setRfidTagNumber(String rfidTagNumber)
	{
		this.rfidTagNumber=rfidTagNumber; 
	}
	/**
	 * Method to define the original location of an item 
	 * @param originalLocation
	 * 		the original location to be set
	 * @postcondition
	 * 		the original location of the item is set  
	 */
	public void setOriginalLocation(String originalLocation)
	{
		this.originalLocation=originalLocation;
		this.currentLocation=originalLocation; 
	}
	/**
	 * Method to define the current location of an item 
	 * @param currentLocation
	 * 		the current location to be set 
	 * @postcondition
	 * 		the current location of the item is set 
	 */
	public void setCurrentLocation(String currentLocation)
	{
		this.currentLocation=currentLocation; 
	}
	
}
