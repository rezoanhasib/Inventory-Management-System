/**
* The <CODE>ItemInfoNode</CODE> Java application creates a node that has reference to a data field, a previous 
* node location and a next node location  
* @author 
* Rezoan Hasib
* <A HREF="mailto:hasib.rezoan@gmail.com"> (hasib.rezoan@gmail.com) </A>
*/
public class ItemInfoNode {
	
	private ItemInfoNode nextNode,prevNode,cursor;
	private ItemInfo datafield;  
	/**
	 * Default constructor to create a node with no item 
	 */
	public ItemInfoNode() {}
	/**
	 * Method to define the information(s) of a node 
	 * @param info
	 * 		the information to be set for selected node 
	 * @postcondition
	 * 		the information for the selected node is set 
	 */
	public void setInfo(ItemInfo info)
	{
		this.datafield=info; 
	}
	/**
	 * Method to return the information(s) of a node 
	 * @return
	 * 		the information of a selected node 
	 */
	public ItemInfo getInfo()
	{
		return datafield; 
	}
	/**
	 * Method to define the next reference node of a node 
	 * @param node
	 * 		the next node reference to be set for selected node
	 * @postcondition
	 * 		the next node reference for the selected node is set 
	 */
	public void setNext(ItemInfoNode node)
	{
		this.nextNode=node; 
	}
	/**
	 * Method to set the previous reference node of a node 
	 * @param node
	 * 		the previous node reference to be set for selected node 
	 * @postcondition
	 * 		the previous node reference for the selected node is set 
	 */
	public void setPrev(ItemInfoNode node)
	{
		this.prevNode=node; 
	}
	/**
	 * Method to obtain the next reference node of a node 
	 * @return
	 * 		the next node reference of selected node 
	 */
	public ItemInfoNode getNext()
	{
		return nextNode; 
	}
	/**
	 * Method to obtain the previous reference node of a node 
	 * @return
	 * 		the previous node reference of selected node 
	 */
	public ItemInfoNode getPrev()
	{
		return prevNode; 
	}
	
}
