import java.sql.*;

import javax.swing.JFrame;
public class Tester {
	public static void main(String[] args) {

		//測試用範例；未來要從database存取資料
		Student stu1 = new Student("Cindy",111208039,"r1107","0907603998");
		
		Student stu2 = new Student("Peter",111207018,"r0318","0905693977");
		Clothes c1 = new Clothes("條紋襯衫","Cynthia",1,"女","S","粉",8,"學生、上班族");
		Clothes c2 = new Clothes("白襯衫","Lucy",2,"男","L","白",5,"上班族");
		Clothes c3 = new Clothes("雪紡背心","Lydia",3,"女","S","粉",8,"學生");
		Clothes c4 = new Clothes("格紋襯衫","Liz",4,"女","S","粉",8,"休閒");
		Shoes s = new Shoes("平底布鞋","Tina",1,"女",35,"白",8,"學生、上班族");
		Accessory a = new Accessory("星空銀飾手鍊","Melody",1,10,"");
		
		StudentList sl = new StudentList();
		sl.addList(stu1);
		sl.addList(stu2);
		
		ClothesList clothesList = new ClothesList();
		ShoesList shoesList = new ShoesList();
		AccessoryList accessoryList = new AccessoryList();
		clothesList.addList(c1);
		clothesList.addList(c2);
		clothesList.addList(c3);
		clothesList.addList(c4);
		shoesList.addList(s);
		accessoryList.addList(a);
		
		Login_Frame loginFrame = new Login_Frame();
		loginFrame.setVisible(true);
		
	}

}