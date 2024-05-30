import java.util.ArrayList;
public class ShoesList extends ItemList{
	private ArrayList<Shoes> shoesList;
	public ShoesList() {
		this.shoesList = new ArrayList<Shoes>();
	}
	@Override
    public ItemList getList() {
        return this;
    }

	public ArrayList<Shoes> getShoesList() {
		return shoesList;
	}
	public void addList(Shoes s) {
		this.shoesList.add(s);
	}
	public Shoes getItem(int ID) {
		for(Shoes s:shoesList) {
			if(s.getID()==ID) {
				return s;
			}
		}
		return null;
	}
	public void deleteItem(Item i) {
		if(i instanceof Shoes) {
			Shoes s = (Shoes)i;
			this.shoesList.remove(s);
		}
	}
}
