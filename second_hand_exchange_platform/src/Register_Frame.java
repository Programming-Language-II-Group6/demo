import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class Register_Frame extends JFrame {
	JPanel RegisterPanel, Button_RPanel;
	Register_Frame(){
		// 因為排版，分成兩個 Panel
	  RegisterPanel = new JPanel();
	  Button_RPanel = new JPanel();
  
	  // 建立輸入帳號的地方
	  JLabel usernameLabel_R = new JLabel("請輸入帳號(學號)：");
	  JTextField usernameText_R = new JTextField();
  
	  // 建立輸入密碼的地方
	  JLabel PasswordLabel_R = new JLabel("請輸入密碼：");
	  JTextField PasswordText_R = new JTextField();
	  
	  // 建立輸入名稱的地方
	  JLabel entered_Name = new JLabel("請輸入名稱：");
	  JTextField name_Field = new JTextField();
	  
	  // 建立輸入電話號碼的地方
	  JLabel entered_PhoneNum = new JLabel("請輸入電話號碼：");
	  JTextField PhoneNum_Field = new JTextField();
  
	  // 建立註冊帳號，執行監聽
	  JButton RegisterButton_R = new JButton("註冊");
	  RegisterButton_R.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    String username = name_Field.getText();
		    String password = PasswordText_R.getText();
		    int account = Integer.parseInt(usernameText_R.getText());
		    String phoneNumber = PhoneNum_Field.getText();
    
    // 將輸入的帳號密碼寫入 MySQL
    // 註冊成功會跳出 Dialog 提示成功，完成後關閉視窗
    try(Connection conn_R = DriverManager.getConnection("jdbc:mysql://140.119.19.73:3315/111208039?useSSL=false", "111208039", "7rw7y")){
     String sql_R = "INSERT INTO Student(Student_ID,Name,Password,Phone_Number,Point) VALUES (?,?,?,?,5)";
     try(PreparedStatement preparedStatement = conn_R.prepareStatement(sql_R)){
      preparedStatement.setInt(1, account);
      preparedStatement.setString(2, username);
      preparedStatement.setString(3, password);
      preparedStatement.setString(4, phoneNumber);
      preparedStatement.executeUpdate();
      JOptionPane.showMessageDialog(Register_Frame.this, "註冊成功");
     }
    } catch (SQLException e1) {
     e1.printStackTrace();
     JOptionPane.showMessageDialog(Register_Frame.this, "註冊失敗");
    }
    
    dispose();
   }
  });
  
  // JPanel 進行排版
  RegisterPanel.setLayout(new GridLayout(4,2));
  RegisterPanel.add(usernameLabel_R); RegisterPanel.add(usernameText_R);
  RegisterPanel.add(PasswordLabel_R); RegisterPanel.add(PasswordText_R);
  RegisterPanel.add(entered_Name); RegisterPanel.add(name_Field);
  RegisterPanel.add(entered_PhoneNum); RegisterPanel.add(PhoneNum_Field);
  Button_RPanel.setLayout(new GridLayout(1, 1, 20, 30));
  Button_RPanel.add(RegisterButton_R);
  EmptyBorder emptyBorder = new EmptyBorder(50, 30, 0, 30);
  RegisterPanel.setBorder(emptyBorder);
  EmptyBorder emptyBorder_B = new EmptyBorder(0, 120, 40, 120);
  Button_RPanel.setBorder(emptyBorder_B);
  
  // JFrame 進行排版
  this.setTitle("帳號註冊");
  this.setSize(400, 300);
  this.setLocationRelativeTo(null);
  this.setVisible(true);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLayout(new BorderLayout());
  this.add(RegisterPanel, BorderLayout.CENTER);
  this.add(Button_RPanel, BorderLayout.SOUTH);
 }
}