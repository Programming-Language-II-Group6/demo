import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class Login_Frame extends JFrame {
	JPanel LoginPanel;
	Student student;
	StudentList studentList;
	ClothesList clothesList;
	ShoesList shoesList;
	AccessoryList accessoryList;
	
	Login_Frame(){
		LoginPanel = new JPanel();
		studentList = new StudentList();
		// Populate the student list from the database
        try {
            populateStudentList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
  // 設定帳號輸入的地方
  JLabel usernameLabel = new JLabel("請輸入帳號：");
  JTextField stuID_Field = new JTextField();
  
  // 設定密碼輸入的地方
  JLabel PasswordLabel = new JLabel("請輸入密碼：");
  JPasswordField PasswordText = new JPasswordField();
  
  // 設定登入和註冊按鍵
  JButton LoginButton = new JButton("登入");
  JButton RegisterButton = new JButton("註冊");
  
  // 登入按鍵上執行監聽，成功的話關閉這個視窗，並開啟主畫面
  LoginButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    int stuID = Integer.parseInt(stuID_Field.getText());
    String password = new String(PasswordText.getPassword());
    try {
     if (ValidLogin(stuID, password)) {
	      JOptionPane.showMessageDialog(Login_Frame.this, "登入成功", "登入成功", JOptionPane.WARNING_MESSAGE);
	      dispose();
	      
	      new Home_Frame(student, studentList, clothesList, shoesList, accessoryList);
     }
    } catch (HeadlessException e1) {
     e1.printStackTrace();
    } catch (SQLException e1) {
     e1.printStackTrace();
    }
   }
  });
   
  // 註冊按鍵上執行監聽，建立 Register GUI
  RegisterButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    new Register_Frame();
   }
  });

  //設定JPanel排版
  LoginPanel.setLayout(new GridLayout(3, 2, 20, 30));
  LoginPanel.add(usernameLabel); LoginPanel.add(stuID_Field);
  LoginPanel.add(PasswordLabel); LoginPanel.add(PasswordText);
  LoginPanel.add(LoginButton); LoginPanel.add(RegisterButton);
  EmptyBorder emptyBorder = new EmptyBorder(50, 60, 50, 60);
  LoginPanel.setBorder(emptyBorder);
  
  //設定JFrame
  this.pack();
  this.setTitle("帳號登入");
  this.setSize(400, 300);
  this.setLocationRelativeTo(null);
  this.setVisible(true);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLayout(new BorderLayout());
  this.add(LoginPanel, BorderLayout.CENTER);  
 }
 
 // ValidLogin 的運作設定
 // 帳號密碼進入 MySQL 比對
 // 如果帳號不存在，跳出 Dialog 提示帳號不存在
 // 如果帳號存在，但密碼不正確，跳出 Dialog 提示密碼不正確
 private boolean ValidLogin(int stuID, String password) throws SQLException {
  try(Connection conn = DriverManager.getConnection("jdbc:mysql://140.119.19.73:3315/111208039?useSSL=false", "111208039", "7rw7y")){
	  String sql = "SELECT * FROM Student WHERE Student_ID = ?";

      try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
          preparedStatement.setInt(1, stuID);

          try (ResultSet resultSet = preparedStatement.executeQuery()) {
              if (resultSet.next()) {
                  String storedPassword = resultSet.getString("Password");
                  if (storedPassword.equals(password)) {
                      String username = resultSet.getString("Name");
                      String phoneNumber = resultSet.getString("Phone_Number");
                      int point = resultSet.getInt("Point");

                      // Instantiate the Student object with the fetched data
                      student = new Student(username, stuID, password, phoneNumber);
                      student.setPoint(point);
                      clothesList = new ClothesList();
                      shoesList = new ShoesList();
                      accessoryList = new AccessoryList();
                      return true;
                  } else {
                      JOptionPane.showMessageDialog(Login_Frame.this, "密碼不正確", "登入失敗", JOptionPane.ERROR_MESSAGE);
                  }
              } else {
                  JOptionPane.showMessageDialog(Login_Frame.this, "帳號不存在", "登入失敗", JOptionPane.ERROR_MESSAGE);
              }
          }
      }
  } catch (SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(Login_Frame.this, "登入失敗", "錯誤", JOptionPane.ERROR_MESSAGE);
  }
  return false;
}
//Method to fetch all students from the database and populate the StudentList
 private void populateStudentList() throws SQLException {
     try (Connection conn = DriverManager.getConnection("jdbc:mysql://140.119.19.73:3315/111208039?useSSL=false", "111208039", "7rw7y")) {
         String sql = "SELECT * FROM Student";

         try (Statement statement = conn.createStatement();
              ResultSet resultSet = statement.executeQuery(sql)) {

             while (resultSet.next()) {
                 int studentID = resultSet.getInt("Student_ID");
                 String username = resultSet.getString("Name");
                 String password = resultSet.getString("Password");
                 String phoneNumber = resultSet.getString("Phone_Number");
                 int point = resultSet.getInt("Point");

                 Student stu = new Student(username, studentID, password, phoneNumber);
                 stu.setPoint(point);

                 studentList.addList(stu);
             }
         }
     } catch (SQLException ex) {
         ex.printStackTrace();
         JOptionPane.showMessageDialog(Login_Frame.this, "無法獲取學生資料", "錯誤", JOptionPane.ERROR_MESSAGE);
     }
 }

 }
 
