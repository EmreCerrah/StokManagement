package stock.Viwer;

import stock.Model.KdvType;

import javax.swing.*;
import java.awt.*;

public class KdvTypeCardViewer extends JInternalFrame {
    private JTextField kdvCodeField;
    private JTextField kdvNameField;
    private JButton saveButton;
    private JButton deleteButton;
    private JTextField rate;
    private JButton nextButton;
    private JButton preButton;
    private KdvType kdvType;

    public KdvTypeCardViewer() {
        setKdvViewerStyle();
    }

    public KdvTypeCardViewer(KdvType kdvType) {
        this.kdvType = kdvType;
        setKdvViewerStyle();
        setInfo(kdvType);
    }

    public JTextField getKdvCodeField() {
        return kdvCodeField;
    }

    public JTextField getKdvNameField() {
        return kdvNameField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextField getRate() {
        return rate;
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

    public void setKdvViewerStyle() {
        setTitle("KDV Tip Kartı");
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);
        setBounds(70, 400, 473, 265);
        getContentPane().setLayout(null);

        JLabel typeCodeLabel = new JLabel("KDV Tip Kodu");
        typeCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        typeCodeLabel.setBounds(36, 24, 103, 14);
        getContentPane().add(typeCodeLabel);

        JLabel typeNameLabel = new JLabel("KDV Tip Adı");
        typeNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        typeNameLabel.setBounds(36, 60, 79, 14);
        getContentPane().add(typeNameLabel);

        JLabel decLabel = new JLabel("KDV Oranı");
        decLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        decLabel.setBounds(36, 104, 79, 14);
        getContentPane().add(decLabel);

        kdvCodeField = new JTextField();
        kdvCodeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        kdvCodeField.setBounds(139, 21, 141, 20);
        getContentPane().add(kdvCodeField);
        kdvCodeField.setColumns(10);


        kdvNameField = new JFormattedTextField();
        kdvNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        kdvNameField.setBounds(139, 57, 141, 20);
        getContentPane().add(kdvNameField);

        rate = new JTextField();
        rate.setBounds(139, 104, 141, 20);
        getContentPane().add(rate);

        saveButton = new JButton("Kaydet");
        saveButton.setBounds(290, 198, 110, 23);
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

    public void setInfo(KdvType kdvType) {
        kdvCodeField.setText(kdvType.getKdvCode());
        kdvNameField.setText(kdvType.getKdvName());
        rate.setText(String.valueOf(kdvType.getKdvRate()));
        deleteButton.setEnabled(true);
    }

    public KdvType getKdvType() {
        if (kdvType == null) {
            String cod = kdvCodeField.getText();
            String name = kdvNameField.getText();
            double rate = Double.parseDouble(this.rate.getText());
            kdvType = new KdvType(cod, name, rate);
        }
        return kdvType;
    }

    public void setKdvType(KdvType kdvType) {
        this.kdvType = kdvType;
    }
}
