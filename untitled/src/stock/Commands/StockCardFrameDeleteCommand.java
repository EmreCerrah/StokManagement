package stock.Commands;

import stock.Model.DataHelper;
import stock.Viwer.StockCardFrameViwer;

import javax.swing.*;
import java.sql.SQLException;

public class StockCardFrameDeleteCommand implements CommandIF{
    private StockCardFrameViwer stockCardFrameViwer;

    public StockCardFrameDeleteCommand(StockCardFrameViwer stockCardFrameViwer) {
        this.stockCardFrameViwer=stockCardFrameViwer;
    }

    @Override
    public void execute() {
        if(ConfirmDialogInFrame("Silmek istediğinise emin misiniz?","Stok Kartı Sil!")==0){
            if (stockCardFrameViwer.getCurrent()!=null){
                try {
                    stockCardFrameViwer.getCurrent().deleteItem();
                    DataHelper.getInstance().loadAllItems();
                    stockCardFrameViwer.setClosed(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private int ConfirmDialogInFrame(String message, String title) {
        int input = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        return input;
    }
}
