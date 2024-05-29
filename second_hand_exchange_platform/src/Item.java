
public class Item implements Item_Manager{
	private String Iname;
	private String Pname;
	private int ID;
	private int condition;
	private String description;
	//constructor
	public Item(String Iname,String Pname,int ID,int condition,String description) {
		this.Iname=Iname;
		this.Pname=Pname;
		this.ID=ID;
		this.condition=condition;
		this.description=description;
	}
	//methods
	public String getIName() {
		return Iname;
	};
	public int getID() {
		return ID;
	};
	public String getPName() {
		return Pname;
	};
	public int getCondition() {
		return condition;
	};
	public String getDescription() {
		return description;
	};
}
