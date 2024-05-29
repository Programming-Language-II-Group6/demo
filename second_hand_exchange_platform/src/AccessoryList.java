import java.util.ArrayList;
public class AccessoryList extends ItemList implements ItemList_Manager{
	private ArrayList<Accessory> accessoryList;
	public AccessoryList() {
		this.accessoryList = new ArrayList<Accessory>();
	}
	public ItemList getList() {
		ItemList list = new AccessoryList();
		return list;
	}
	public ArrayList<Accessory> getAccessoryList() {
		return accessoryList;
	}
	public void addList(Accessory a) {
		this.accessoryList.add(a);
	}
	public Accessory getItem(int ID) {
		for(Accessory a:accessoryList) {
			if(a.getID()==ID) {
				return a;
			}
		}
		return null;
	}
	public void deleteItem(Item i) {
		if(i instanceof Accessory) {
			Accessory a = (Accessory)i;
			this.accessoryList.remove(a);
		}
	}
}
