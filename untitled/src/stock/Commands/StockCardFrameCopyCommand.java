package stock.Commands;

import stock.Model.DataHelper;
import stock.Model.StockType;
import stock.Viwer.StockCardFrameViwer;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockCardFrameCopyCommand implements CommandIF {
    private StockCardFrameViwer stockCardFrameViwer;

    public StockCardFrameCopyCommand(StockCardFrameViwer stockCardFrameViwer) {
        this.stockCardFrameViwer = stockCardFrameViwer;
    }

    @Override
    public void execute() {
        if (stockCardFrameViwer.getCurrent()!=null){
            try {
                String input= inputDialogInFrame("Lütfen Stok Kodunu Giriniz.");
                if(input==null || input.equals("")){
                    alertDialogInFrame("Lüften Bir Stok Kodu Giriniz.","Boş Stok Kodu!");
                }else {
                    ArrayList<StockType> liste = DataHelper.getInstance().getTip();
                    stockCardFrameViwer.getCurrent().setStockCode(input);
                    stockCardFrameViwer.getCurrent().saveItem();
                }
            } catch (SQLException ex) {
                alertDialogInFrame("Lüften Bir Stok Kodu Giriniz.","Boş Stok Kodu!");
                throw new RuntimeException(ex);
            }
        }
        try {
            DataHelper.getInstance().loadAllItems();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    private String inputDialogInFrame(String message) {
        String input = JOptionPane.showInputDialog(null,message);
        return input;
    }
    private int alertDialogInFrame(String message, String title) {
        int input = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
        return input;
    }
}
