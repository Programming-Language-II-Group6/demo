import java.util.ArrayList;
public class ClothesList extends ItemList implements ItemList_Manager{
	private ArrayList<Clothes> clothesList;
	public ClothesList() {
		this.clothesList = new ArrayList<Clothes>();
	}
	public ItemList getList() {
		ItemList list = new ClothesList();
		return list;
	}
	public ArrayList<Clothes> getClothesList() {
		return clothesList;
	}
	public void addList(Clothes c) {
		this.clothesList.add(c);
	}
	public Clothes getItem(int ID) {
		for(Clothes c:clothesList) {
			if(c.getID()==ID) {
				return c;
			}
		}
		return null;
	}
	public void deleteItem(Item i) {
		if(i instanceof Clothes) {
			Clothes c = (Clothes)i;
			this.clothesList.remove(c);
		}
	}

}
