import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.plaf.FontUIResource;

public class Exchange_Frame extends JFrame {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 700;
    private final int FIELD_WIDTH = 10;
    private JPanel comboPanel, IDEnterPanel, keywordSearchPanel, exchangePanel, infoPanel, overallPanel;
    private JScrollPane scrollPane;
    private JLabel IDLabel, typeLabel, keywordLabel;
    private JComboBox<String> typeCombo;
    private JTextField IDField, keywordField;
    private JTable table;
    private JButton exchangeButton, searchKeywordButton;

    // Constructor
    public Exchange_Frame(Student s, StudentList sl, ClothesList clothesList, ShoesList shoesList, AccessoryList accessoryList) {
    	this.setUIFont(new FontUIResource("Times Roman",Font.PLAIN,15));
    	this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("搜尋 & 交換");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout()); // Set layout manager for the frame

        // Initialize panels
        comboPanel = new JPanel();
        IDEnterPanel = new JPanel();
        keywordSearchPanel = new JPanel();
        exchangePanel = new JPanel();
        infoPanel = new JPanel(new BorderLayout());
        overallPanel = new JPanel(new GridLayout(2, 2));

        createTypeCombo();
        createSearchField(s, sl, clothesList, shoesList, accessoryList);
        createTableData();
        createPanel();

        this.setVisible(true);
    }
    //換整個視窗的字型
    public void setUIFont (FontUIResource fui){
        Enumeration keys=UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
          Object key=keys.nextElement();
          Object value=UIManager.get(key);
          if (value != null && value instanceof FontUIResource) {
            UIManager.put(key, fui);
            }
          } 
        }

    private void createTypeCombo() {
        // Combo box panel content
        comboPanel.setPreferredSize(new Dimension(300, 50));
        typeCombo = new JComboBox<>();
        typeCombo.addItem("衣服");
        typeCombo.addItem("鞋子");
        typeCombo.addItem("飾品");
        typeLabel = new JLabel("類別: ");
        comboPanel.add(typeLabel);
        comboPanel.add(typeCombo);
    }

    private void createSearchField(Student s, StudentList sl, ClothesList clothesList, ShoesList shoesList, AccessoryList accessoryList) {
        // ID enter panel content
        IDLabel = new JLabel("輸入ID:");
        IDField = new JTextField(FIELD_WIDTH);
        exchangeButton = new JButton("交換");
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = typeCombo.getSelectedItem().toString();
                try {
                    int enteredID = Integer.parseInt(IDField.getText());
                    switch (type) {
                        case "衣服":
                            for (Clothes c : clothesList.getClothesList()) {
                                if (c.getID() == enteredID) {
                                	DefaultTableModel model = (DefaultTableModel) table.getModel();
                                    model.setRowCount(0); // Clear the table before adding new rows
                                    model.addRow(new Object[]{c.getID(), c.getIName(), c.getPName(), c.getCondition(), c.getDescription()});
                                    handleExchange(s, sl, clothesList, c);
                                    return;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "查無物件!", "無法執行", JOptionPane.ERROR_MESSAGE);
                            break;
                        case "鞋子":
                            for (Shoes sh : shoesList.getShoesList()) {
                                if (sh.getID() == enteredID) {
                                	DefaultTableModel model = (DefaultTableModel) table.getModel();
                                    model.setRowCount(0); // Clear the table before adding new rows
                                    model.addRow(new Object[]{sh.getID(), sh.getIName(), sh.getPName(), sh.getCondition(), sh.getDescription()});
                                    handleExchange(s, sl, shoesList, sh);
                                    return;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "查無物件!", "無法執行", JOptionPane.ERROR_MESSAGE);
                            break;
                        case "飾品":
                            for (Accessory a : accessoryList.getAccessoryList()) {
                                if (a.getID() == enteredID) {
                                	DefaultTableModel model = (DefaultTableModel) table.getModel();
                                    model.setRowCount(0); // Clear the table before adding new rows
                                    model.addRow(new Object[]{a.getID(), a.getIName(), a.getPName(), a.getCondition(), a.getDescription()});
                                    handleExchange(s, sl, accessoryList, a);
                                    return;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "查無物件!", "無法執行", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "ID輸入格式錯誤", "無法執行", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e2) {
                    if (IDField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "ID輸入為空白", "無法執行", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        exchangeButton.setBackground(Color.BLACK);
        exchangeButton.setForeground(Color.WHITE);
        IDEnterPanel.add(IDLabel);
        IDEnterPanel.add(IDField);
        IDEnterPanel.add(exchangeButton);

        // Keyword search panel content
        keywordLabel = new JLabel("Key Word:");
        keywordField = new JTextField(FIELD_WIDTH);
        searchKeywordButton = new JButton("search");
        searchKeywordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = typeCombo.getSelectedItem().toString();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Clear the table before adding new rows
                switch (type) {
                    case "衣服":
                        populateTable(clothesList.getClothesList(), keywordField.getText());
                        break;
                    case "鞋子":
                        populateTable(shoesList.getShoesList(), keywordField.getText());
                        break;
                    case "飾品":
                        populateTable(accessoryList.getAccessoryList(), keywordField.getText()); 
                        break;
                }
            }
        });
        searchKeywordButton.setBackground(Color.BLACK);
        searchKeywordButton.setForeground(Color.WHITE);
        keywordSearchPanel.add(keywordLabel);
        keywordSearchPanel.add(keywordField);
        keywordSearchPanel.add(searchKeywordButton);
    }

    private void populateTable(ArrayList<? extends Item> itemList, String keyword) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i=0;i<itemList.size();i++) {
        	if(itemList.get(i).getIName() != null && itemList.get(i).getDescription() != null) {
        		if (itemList.get(i).getIName().contains(keyword) || itemList.get(i).getDescription().contains(keyword)) {
                    model.addRow(new Object[]{itemList.get(i).getID(), itemList.get(i).getIName(), itemList.get(i).getPName(), itemList.get(i).getCondition(), itemList.get(i).getDescription()});
                }
        	}
        }
    }

    private void handleExchange(Student s, StudentList sl, ItemList IL, Item item) {
        int result = JOptionPane.showConfirmDialog(null, "確認是否執行動作?", "確認", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION && s.getPoint() >= 1) {
            s.addExchanged(item);
            sl.getStudent(item.getPName()).addNameList(sl.getStudent(item.getPName()).getName());
            sl.getStudent(item.getPName()).calcPoints_publish();
            IL.deleteItem(item);
            s.subPoint();
            JOptionPane.showMessageDialog(null,"提示","成功兌換!",JOptionPane.INFORMATION_MESSAGE);
            IDField.setText("");
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear the table before adding new rows
        } else {
            JOptionPane.showMessageDialog(null,"提示","點數不足，無法兌換",JOptionPane.WARNING_MESSAGE);
        }
    }

    private void createTableData() {
        // Table data for item info
        table = new JTable(new DefaultTableModel(new Object[]{"ID", "物品資訊", "刊登者", "新舊程度", "描述"}, 0));
        scrollPane = new JScrollPane(table);
        infoPanel.add(scrollPane,BorderLayout.CENTER);
    }

    private void createPanel() {
        // Add all panels to overall panel
        overallPanel.add(comboPanel);
        overallPanel.add(keywordSearchPanel);
        overallPanel.add(IDEnterPanel);        
        overallPanel.add(exchangePanel);
        this.add(overallPanel, BorderLayout.NORTH);
        this.add(infoPanel, BorderLayout.CENTER);
    }
}