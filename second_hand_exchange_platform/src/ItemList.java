import java.util.ArrayList;
public class ItemList implements ItemList_Manager{
	private ArrayList<Item> itemList;
	//constructor
	public ItemList() {
		itemList = new ArrayList<Item>();
	}
	public ItemList getList() {
		return this;
	}
	public void addList(Item i) {
		itemList.add(i);
	};
	public Item getItem(int ID) {
		for(Item i:itemList) {
			if(i.getID()==ID) {
				return i;
			}
		}
		return null;
	};
	public void deleteItem(Item i) {
		for(Item item:itemList) {
			if(i.getID()==item.getID()) {
				itemList.remove(i);
			}
		}
	}
}