package stock.Viwer;

import stock.Model.DataHelper;
import stock.Model.StockItem;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class StockCardFrameViwer extends JInternalFrame {
    private final JButton saveButton;
    private final JButton deleteButton;
    private final JButton copyButton;

    private final JTextField stockCodeField;
    private final JTextField stockNameField;
    private final JTextField barcodeField;
    private final JComboBox stockTypeField;
    private final JComboBox unitField;
    private final JTextArea decField;
    private final JComboBox kdvField;
    private final JFormattedTextField dateField;

    private MaskFormatter mask;

    {
        try {
            mask = new MaskFormatter("####-##-##");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] stocktypeArr;

    {
        try {
            stocktypeArr = DataHelper.getInstance().loadTypeName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final String[] UnitArr = {"Metre", "İnç", "Metrekare"};
    private String[] kdvArr;

    {
        try {
            kdvArr = DataHelper.getInstance().loadKdvName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private StockItem current;

    private JButton nextButton;
    private JButton preButton;

    public StockCardFrameViwer(String title) {
        setTitle(title);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);
        ImageIcon imageIconv = new ImageIcon("untitled/src/stock/Model/icons/ID-Card-icon.png");
        setFrameIcon(imageIconv);
        setBounds(700, 10, 473, 378);
        getContentPane().setLayout(null);
//labels
        JLabel stockCodeLabel = new JLabel("Stok Kodu");
        stockCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        stockCodeLabel.setBounds(36, 24, 79, 14);
        getContentPane().add(stockCodeLabel);

        JLabel stockTypeLabel = new JLabel("Stok Tipi");
        stockTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        stockTypeLabel.setBounds(36, 60, 79, 14);
        getContentPane().add(stockTypeLabel);

        JLabel stockNameLabel = new JLabel("Stok Adı");
        stockNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        stockNameLabel.setBounds(234, 24, 79, 14);
        getContentPane().add(stockNameLabel);

        JLabel unitLabel = new JLabel("Birimi");
        unitLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        unitLabel.setBounds(234, 60, 79, 14);
        getContentPane().add(unitLabel);

        JLabel barcodeLabel = new JLabel("Barkod");
        barcodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        barcodeLabel.setBounds(36, 96, 79, 14);
        getContentPane().add(barcodeLabel);

        JLabel dateLabel = new JLabel("Oluşturma Tarihi");
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateLabel.setBounds(234, 96, 79, 14);
        getContentPane().add(dateLabel);

        JLabel kdvLabel = new JLabel("Kdv Oranı");
        kdvLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        kdvLabel.setBounds(36, 241, 79, 14);
        getContentPane().add(kdvLabel);

        JLabel decLabel = new JLabel("Açıklama");
        decLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        decLabel.setBounds(36, 142, 79, 14);
        getContentPane().add(decLabel);
//Fields
        stockCodeField = new JTextField();
        stockCodeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        stockCodeField.setBounds(125, 21, 100, 20);
        getContentPane().add(stockCodeField);
        stockCodeField.setColumns(10);

        stockNameField = new JTextField();
        stockNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        stockNameField.setBounds(323, 21, 100, 20);
        getContentPane().add(stockNameField);
        stockNameField.setColumns(10);

        barcodeField = new JTextField();
        barcodeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        barcodeField.setBounds(125, 91, 100, 20);
        getContentPane().add(barcodeField);
        barcodeField.setColumns(10);


        try {
            stockTypeField = new JComboBox(DataHelper.getInstance().loadTypeName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ;
        stockTypeField.setBounds(125, 58, 100, 22);
        getContentPane().add(stockTypeField);

        unitField = new JComboBox(UnitArr);
        unitField.setBounds(323, 58, 100, 20);
        getContentPane().add(unitField);

        dateField = new JFormattedTextField(mask);
        dateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateField.setBounds(323, 93, 100, 20);
        getContentPane().add(dateField);

        decField = new JTextArea();
        decField.setBounds(125, 137, 300, 83);
        getContentPane().add(decField);


        kdvField = new JComboBox(kdvArr);
        kdvField.setBounds(125, 239, 100, 23);
        getContentPane().add(kdvField);

        setVisible(true);

//Buttons
        saveButton = new JButton("Kaydet");
        saveButton.setBounds(295, 315, 150, 23);
        getContentPane().add(saveButton);

        deleteButton = new JButton("Sil");
        deleteButton.setBounds(135, 315, 89, 23);
        getContentPane().add(deleteButton);

        copyButton = new JButton("Kopyala");
        copyButton.setBounds(35, 315, 89, 23);
        getContentPane().add(copyButton);

        ImageIcon imageIconN = new ImageIcon("untitled/src/stock/Model/icons/Actions-arrow-right-icon.png");
        nextButton = new JButton(imageIconN);
        nextButton.setBounds(368, 239, 40, 25);
        getContentPane().add(nextButton);

        ImageIcon imageIconP = new ImageIcon("untitled/src/stock/Model/icons/Actions-arrow-left-icon.png");
        preButton = new JButton(imageIconP);
        preButton.setBounds(317, 239, 40, 25);
        getContentPane().add(preButton);
    }

    public void setCardInfo(StockItem c) {
        current = c;
        stockCodeField.setText(c.getStockCode());
        stockCodeField.setEditable(false);
        stockNameField.setText(c.getName());
        stockTypeField.setSelectedItem(c.getStockType().getName());
        unitField.setSelectedItem(c.getUnit());
        barcodeField.setText(c.getBarcode());
        kdvField.setSelectedItem(c.getKdvType().getKdvName());
        decField.setText(c.getDeclaration());
        dateField.setText(c.getDate());

    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getCopyButton() {
        return copyButton;
    }

    public StockItem getCurrent() {
        return current;
    }

    public JTextField getStockCodeField() {
        return stockCodeField;
    }

    public JTextField getStockNameField() {
        return stockNameField;
    }

    public JTextField getBarcodeField() {
        return barcodeField;
    }

    public JComboBox getStockTypeField() {
        return stockTypeField;
    }

    public JComboBox getUnitField() {
        return unitField;
    }

    public JTextArea getDecField() {
        return decField;
    }

    public JComboBox getKdvField() {
        return kdvField;
    }

    public JFormattedTextField getDateField() {
        return dateField;
    }

    public MaskFormatter getMask() {
        return mask;
    }

    public Object[] getStocktypeArr() {
        return stocktypeArr;
    }

    public String[] getUnitArr() {
        return UnitArr;
    }

    public String[] getKdvArr() {
        return kdvArr;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getPreButton() {
        return preButton;
    }

    public void setCurrent(StockItem current) {
        this.current = current;
    }

}
