//import essential packages
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Home_Frame extends JFrame{
	//your code
	private JLabel northLabel1,northLabel2,northLabel3,northLabel4;
	private JPanel northPanel,centerPanel,overallPanel;
	private JButton postButton,searchButton;
	private JTextArea infoArea1,infoArea2;
	private String userName;
	private JScrollPane scroll1,scroll2;
	Student stu;
	StudentList sl;
	ClothesList clothesList;
	ShoesList shoesList;
	AccessoryList accessoryList;
	
	//your code
	public Home_Frame(Student stu, StudentList sl, ClothesList clothesList, ShoesList shoesList, AccessoryList accessoryList) {
		
		this.userName = stu.getName();
		this.stu=stu;
		this.sl=sl;
		this.clothesList=clothesList;
		this.shoesList=shoesList;
		this.accessoryList=accessoryList;
		
		
	    this.setTitle("主頁");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(800,500);
		this.setVisible(true);
		
		createButton();
		createInfoArea();
		createPanel();	
	}
	
	public void createButton() {
		postButton = new JButton("刊登");
		searchButton = new JButton("搜尋/交換");
		
		postButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Post_Frame postFrame = new Post_Frame();
				postFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				postFrame.setVisible(true);
			}
			
		});
		
		searchButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				

				Clothes c1 = new Clothes("條紋襯衫","Cynthia",1,"女","S","粉",8,"學生、上班族");
				Clothes c2 = new Clothes("白襯衫","Lucy",2,"男","L","白",5,"上班族");
				Clothes c3 = new Clothes("雪紡背心","Lydia",3,"女","S","粉",8,"學生");
				Clothes c4 = new Clothes("格紋襯衫","Liz",4,"女","S","粉",8,"休閒");
				Shoes s = new Shoes("平底布鞋","Tina",1,"女",35,"白",8,"學生、上班族");
				Accessory a = new Accessory("星空銀飾手鍊","Melody",1,10,"");
				
				clothesList.addList(c1);
				clothesList.addList(c2);
				clothesList.addList(c3);
				clothesList.addList(c4);
				shoesList.addList(s);
				accessoryList.addList(a);
				
				
				Exchange_Frame exchangeFrame = new Exchange_Frame(stu, sl, clothesList, shoesList, accessoryList);
				exchangeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				exchangeFrame.setVisible(true);
			}
			
		});
	}
	
	public void createInfoArea() {
		infoArea1 = new JTextArea(1,1);
		
		infoArea2 = new JTextArea(1,1);
		
		scroll1 = new JScrollPane(infoArea1);
		scroll2 = new JScrollPane(infoArea2);
	}
	
	public void createPanel() {
		
		northPanel = new JPanel (new GridLayout(3,2));
		northLabel1 = new JLabel("用戶："+this.userName+"您好");
		northLabel2 = new JLabel("目前點數");
		northLabel3 = new JLabel("歷史刊登紀錄");
		northLabel4 = new JLabel("歷史交換紀錄");
		
		
		
		
		
		northPanel.add(northLabel1);
		northPanel.add(northLabel2);
		northPanel.add(postButton);
		northPanel.add(searchButton);
		northPanel.add(northLabel3);
		northPanel.add(northLabel4);
		
		centerPanel = new JPanel(new GridLayout(1,2));
		centerPanel.setPreferredSize(new Dimension(640, 480));
		centerPanel.add(scroll1);
		centerPanel.add(scroll2);
		
		overallPanel = new JPanel(new BorderLayout());
		overallPanel.add(northPanel, BorderLayout.NORTH);
		overallPanel.add(centerPanel, BorderLayout.CENTER);
		
		add(overallPanel);
		
	}
	
	
}
