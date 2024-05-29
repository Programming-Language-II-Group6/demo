
public class Shoes extends Item implements Item_Manager{
	private String Iname;
	private String Pname;
	private int ID;
	private String FM; //true for female;false for male
	private double size;
	private String color;
	private int condition;
	private String description;
	//constructor
	public Shoes(String Iname,String Pname,int ID,String FM,double size,String color,int condition,String description) {
		super(Iname,Pname,ID,condition,description);
		this.ID=ID;
		this.FM=FM;
		this.size=size;
		this.color=color;
		this.Iname = Iname+"/"+color+"/"+FM+"/"+size;
		this.Pname=Pname;
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
