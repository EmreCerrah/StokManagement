package stock.Viwer;

import stock.Model.StockType;

import javax.swing.*;
import java.awt.*;

public class StockTypeCardViewer extends JInternalFrame {
    private JTextField typeCodeField;
    private JFormattedTextField typeNameField;
    private JButton saveButton;
    private JTextArea decField;
    private JButton deleteButton;
    private JButton nextButton;
    private JButton preButton;
    private StockType stockType;

    public StockTypeCardViewer() {
        setTypeCardStyle();
    }

    public StockTypeCardViewer(StockType stockType) {
        this.stockType = stockType;
        setTypeCardStyle();
        setInfo(stockType);
    }

    public JTextField getTypeCodeField() {
        return typeCodeField;
    }

    public JFormattedTextField getTypeNameField() {
        return typeNameField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextArea getDecField() {
        return decField;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getPreButton() {
        return preButton;
    }

    public StockType getStockType() {
        if (stockType == null) {
            String cod = typeCodeField.getText();
            String name = typeNameField.getText();
            String dec = decField.getText();
            stockType = new StockType(cod, name, dec);
        }
        return stockType;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
    }

    public void setTypeCardStyle() {
        setTitle("Skok Tip Kartı");
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);
        setBounds(650, 400, 473, 265);
        getContentPane().setLayout(null);

        JLabel typeCodeLabel = new JLabel("Stok Tip Kodu");
        typeCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        typeCodeLabel.setBounds(36, 24, 103, 14);
        getContentPane().add(typeCodeLabel);

        JLabel typeNameLabel = new JLabel("Stok Tip Adı");
        typeNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        typeNameLabel.setBounds(36, 60, 79, 14);
        getContentPane().add(typeNameLabel);

        JLabel decLabel = new JLabel("Açıklama");
        decLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        decLabel.setBounds(36, 104, 79, 14);
        getContentPane().add(decLabel);

        typeCodeField = new JTextField();
        typeCodeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        typeCodeField.setBounds(139, 21, 141, 20);
        getContentPane().add(typeCodeField);
        typeCodeField.setColumns(10);


        typeNameField = new JFormattedTextField();
        typeNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        typeNameField.setBounds(139, 57, 141, 20);
        getContentPane().add(typeNameField);

        decField = new JTextArea();
        decField.setBounds(139, 104, 270, 83);
        getContentPane().add(decField);

        saveButton = new JButton("Yeni Kaydet");
        saveButton.setBounds(292, 198, 117, 23);
        getContentPane().add(saveButton);

        deleteButton = new JButton("Sil");
        deleteButton.setBounds(160, 198, 110, 23);
        getContentPane().add(deleteButton);
        deleteButton.setEnabled(false);

        ImageIcon imageIconN = new ImageIcon("untitled/src/stock/Model/icons/Actions-arrow-right-icon.png");
        nextButton = new JButton(imageIconN);
        nextButton.setBounds(90, 198, 40, 25);
        getContentPane().add(nextButton);


        ImageIcon imageIconP = new ImageIcon("untitled/src/stock/Model/icons/Actions-arrow-left-icon.png");
        preButton = new JButton(imageIconP);
        preButton.setBounds(30, 198, 40, 25);
        getContentPane().add(preButton);

        setVisible(true);
    }

    public void setInfo(StockType stockType) {
        typeCodeField.setText(stockType.getCod());
        typeNameField.setText(stockType.getName());
        decField.setText(stockType.getDec());
        deleteButton.setEnabled(true);
    }
}
