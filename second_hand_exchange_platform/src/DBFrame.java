import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DBFrame extends JFrame {
    private JPanel flow_panel, operate_panel;
    private JTextArea outputArea;
    private JLabel column1, column2, column3, column4, column5;
    private JTextField text1, text2, text3, text4, text5;
    private JComboBox<String> operateCombo;
    private JButton commitButton;
    private Connection conn;
    private Statement stat;

    public DBFrame(Connection conn) throws SQLException {
        this.conn = conn;
        createTextArea();
        createLabel();
        createTextField();
        createCombo();
        createButton();
        createLayout();
        setTitle("Query");
        setSize(600, 400);
        
    }

    private void createTextArea()  throws SQLException{
        outputArea = new JTextArea(1, 12); // Set rows and columns for the text area
        outputArea.setEditable(false); // Make text area read-only
       
    }

    

	private void createLabel() {
        column1 = new JLabel("ID:");
        column2 = new JLabel("Name:");
        column3 = new JLabel("Birth:");
        column4 = new JLabel("Height:");
        column5 = new JLabel("Position:");
    }

    private void createTextField() {
        text1 = new JTextField(10);
        text2 = new JTextField(10);
        text3 = new JTextField(10);
        text4 = new JTextField(10);
        text5 = new JTextField(10);
    }

    private void createCombo() {
        operateCombo = new JComboBox<>();
        operateCombo.addItem("Add");
        operateCombo.addItem("Delete");
    }

    private void createButton() throws SQLException{
        commitButton = new JButton("Commit");
        commitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String op = (String) operateCombo.getSelectedItem();
                String query = "";

                // Determine the query based on the selected operation
                if (op.equals("Add")) {
                    // Example of an INSERT operation
                    query = "INSERT INTO Twice (id, name, birthday, height, position) VALUES (" +
                            "'" + text1.getText() + "'," +
                            "'" + text2.getText() + "'," +
                            "'" + text3.getText() + "'," +
                            "'" + text4.getText() + "'," +
                            "'" + text5.getText() + "')";
                } else if (op.equals("Delete")) {
                    // Example of a DELETE operation
                    query = "DELETE FROM Twice WHERE id = '" + text1.getText() + "'";
                }

                try {
                    // Execute the SQL query
                    stat = conn.createStatement();
                    int rowsAffected = stat.executeUpdate(query);
                    outputArea.setText(rowsAffected + " row(s) affected.");
                } catch (SQLException e) {
                    outputArea.setText("Error executing query: " + e.getMessage());
                }
                
            }
        });
    }

    private void createLayout() {
        flow_panel = new JPanel(new GridLayout(2, 1));
        operate_panel = new JPanel(new GridLayout(3, 1));

        JPanel label = new JPanel(new GridLayout(1, 5));
        label.add(column1);
        label.add(column2);
        label.add(column3);
        label.add(column4);
        label.add(column5);

        JPanel text = new JPanel(new GridLayout(1, 5));
        text.add(text1);
        text.add(text2);
        text.add(text3);
        text.add(text4);
        text.add(text5);

        JPanel ope = new JPanel(new GridLayout(1, 2));
        ope.add(operateCombo);
        ope.add(commitButton);

        operate_panel.add(label);
        operate_panel.add(text);
        operate_panel.add(ope);

        flow_panel.add(new JScrollPane(outputArea));
        flow_panel.add(operate_panel);

        add(flow_panel);
    }
    public static String showResultSet(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();
        String output = "";
        for (int i = 1; i <= columnCount; i++) {
        	output += String.format("%15s", metaData.getColumnLabel(i));
        }
        output += "\n";
        while (result.next()) {
        	for (int i = 1; i <= columnCount; i++) {
        		output += String.format("%15s", result.getString(i));
        	}
        output += "\n";
        }
  		return output;
    }
}
