import java.util.ArrayList;
public class Student {
	private String username;
	private int studentID;
	private String phoneNumber;
	private String password;
	private int point;
	private ArrayList<Item> published;
	private ArrayList<Item> beenExchanged;
	private ArrayList<Item> exchanged;
	private ArrayList<String> nameList; //to store exchangers' names(The order is the same as the beenExchanged)
	//constructor
	public Student(String name,int stuID,String password,String phoneNumber) {
		this.username=name;
		this.studentID=stuID;
		this.phoneNumber=phoneNumber;
		this.password=password;
		this.point=5;
		this.published=new ArrayList<Item>();
		this.beenExchanged=new ArrayList<Item>();
		this.exchanged=new ArrayList<Item>();
		this.nameList=new ArrayList<String>();
	}
	//methods
	public void publish(Item i,ItemList_Manager IM) {
		published.add(i);
		if(i instanceof Clothes) {
			Clothes c = (Clothes)i;
			IM.addList(c);
		}
		else if(i instanceof Shoes) {
			Shoes s = (Shoes)i;
			IM.addList(s);
		}
		else if(i instanceof Accessory) {
			Accessory a = (Accessory)i;
			IM.addList(a);
		}
	}
	
	//要不要改變狀態?要return String嗎?
	public String exchange(Item i,ItemList_Manager IM) {
		if(IM.getItem(i.getID()) != null) {
			IM.deleteItem(IM.getItem(i.getID()));
			return username;
		}
		else {
			return null;
		}
		
	}
	public String calcPoints_exchange() {
		if(point>0) {
			point-=1;
			return "Action performed successfully!";
		}
		else {
			return "Not enough points!";
		}
	}
	public void calcPoints_publish() {
		point++;
	}
	 public void subPoint() {
	        if (point > 0) {
	            point--;
	        }
	    }
	public void addPublished(Item item) {
		published.add(item);
	}
	public void addExchanged(Item item) {
		exchanged.add(item);
	}
	public void addBeenExchanged(Item item) {
		beenExchanged.add(item);
	}
	//setters and getters
	public void setPoint(int num) {
		this.point=num;
	}
	//getNameList method
	public ArrayList<String> getNameList(int stuID) {
		if(stuID==studentID) {
			return nameList;
		}
		else {
			return null;
		}
	}
	public void addNameList(String name) {
		nameList.add(name);
	}
	public String getName() {
		return username;
	}
	public int getStudentID() {
		return studentID;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public int getPoint() {
		return point;
	}

}
