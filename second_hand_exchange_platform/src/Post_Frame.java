import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Post_Frame extends JFrame{
	
	private JPanel itemIDPanel,overallPanel,categoryPanel,centerPanel,c1Panel,c2Panel,southPanel,buttonPanel,eastPanel,northPanel;
	private JLabel itemIDJLabel1, itemIDJLabel2,categoryLabel,c1Label1,c1Label2,c1Label3,c1Label4,c1Label5,c1Label6,descriptionLabel;
	private JTextField itemField,c1Field1,c1Field2,c1Field3,c1Field4;
	private JComboBox itemCombo;
	private JTextArea infoArea1,infoArea2;
	private JButton addButton,updateButton,delistButton;
	private final int field_width = 10;
	Connection conn;
	Statement stat;
	
	
	public Post_Frame()  {
		
		//this.conn = conn;
		this.setTitle("刊登");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 500);
		createItemComp();
		createButton();
		createInfoArea();
		createPanel();	
		
	}
	public void createItemComp() {
		
		String[] item = {"衣服", "鞋子", "飾品"};
		itemCombo = new JComboBox(item);
		
		
	}
	
	public void createInfoArea() {
		infoArea1 = new JTextArea(4,10);
		infoArea2 = new JTextArea(4,20);
	}
	

	
	public void createButton() {
		addButton = new JButton("新增");
		updateButton = new JButton("修改");
		delistButton = new JButton("下架");
	}
	
	public void createPanel() {
		
		JLabel emptyLabel = new JLabel();

		
		itemIDPanel = new JPanel();
		itemIDJLabel1 = new JLabel("物品ID");
		itemField = new JTextField(field_width);
		
		
		itemIDPanel.add(itemIDJLabel1);
		itemIDPanel.add(itemField);
		
		categoryPanel = new JPanel();
		
		categoryLabel = new JLabel("類別：");
		categoryPanel.add(categoryLabel);
		
		categoryPanel.add(itemCombo);
		
		northPanel = new JPanel(new GridLayout(1,2));
		northPanel.add(itemIDPanel);
		northPanel.add(categoryPanel);
		
		
		
		centerPanel = new JPanel(new GridLayout(3,4));
		
		c1Label1 = new JLabel("物品名稱:");
		c1Label2 = new JLabel("顏色:");
		c1Label3 = new JLabel("尺寸:");
		c1Label4 = new JLabel("新舊程度");
		c1Label5 = new JLabel("敘述:");
		c1Label6 = new JLabel("");
		c1Field1 = new JTextField(10);
		c1Field2 = new JTextField(field_width);
		c1Field3 = new JTextField(field_width);
		c1Field4 = new JTextField(field_width);
		
		centerPanel.add(c1Label1);
		centerPanel.add(c1Label2);
		centerPanel.add(c1Label3);
		centerPanel.add(c1Label4);
	
		centerPanel.add(c1Field1);
		centerPanel.add(c1Field2);
		centerPanel.add(c1Field3);
		centerPanel.add(c1Field4);
		
		centerPanel.add(c1Label5);
		centerPanel.add(c1Label6);
		
		
		
		southPanel = new JPanel(new GridLayout(3,1));
		buttonPanel= new JPanel(new GridLayout(1,3));
		buttonPanel.add(addButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(delistButton);
		southPanel.add(infoArea1);
		southPanel.add(buttonPanel);
		southPanel.add(infoArea2);
		
		
		
		
		
		overallPanel = new JPanel(new BorderLayout());
		overallPanel.add(northPanel, BorderLayout.NORTH);
		overallPanel.add(centerPanel, BorderLayout.CENTER);
		overallPanel.add(southPanel, BorderLayout.SOUTH);
		
	
		
		add(overallPanel);
		
	}
}
