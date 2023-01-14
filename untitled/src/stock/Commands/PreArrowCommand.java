package stock.Commands;

import stock.Model.DataHelper;
import stock.Model.KdvType;
import stock.Model.StockItem;
import stock.Model.StockType;
import stock.Viwer.KdvTypeCardViewer;
import stock.Viwer.StockCardFrameViwer;
import stock.Viwer.StockTypeCardViewer;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreArrowCommand implements CommandIF {

    private JInternalFrame frame;

    public PreArrowCommand(JInternalFrame frame) {
        this.frame = frame;
    }


    @Override
    public void execute() {
        if (frame instanceof StockCardFrameViwer) {
            stockCardFrameViwer((StockCardFrameViwer) frame);
        } else if (frame instanceof KdvTypeCardViewer) {
            try {
                kdvTypeCardViwer((KdvTypeCardViewer) frame);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (frame instanceof StockTypeCardViewer) {
            try {
                stockTypeCardViewer((StockTypeCardViewer) frame);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void stockCardFrameViwer (StockCardFrameViwer frame){
        ArrayList<StockItem> liste = DataHelper.getInstance().getItemList();
        int index = liste.indexOf(frame.getCurrent());
        frame.getPreButton().setEnabled(true);

        frame.setCurrent(liste.get(index - 1));
        frame.setCardInfo(liste.get(index - 1));
        frame.setVisible(true);
        int indexC = liste.indexOf(frame.getCurrent());
        if (indexC == 0) {
            frame.getNextButton().setEnabled(false);
        }
    }
    private void kdvTypeCardViwer (KdvTypeCardViewer ktcv) throws SQLException {
        ArrayList<KdvType> liste = DataHelper.getInstance().getKdv();

        int index = liste.indexOf(ktcv.getKdvType());
        ktcv.getPreButton().setEnabled(true);

        ktcv.setKdvType(liste.get(index - 1));
        ktcv.setInfo(liste.get(index - 1));
        ktcv.setVisible(true);
        int indexC = liste.indexOf(ktcv.getKdvType());
        if (indexC == 0) {
            ktcv.getNextButton().setEnabled(false);
        }
    }
    private void stockTypeCardViewer (StockTypeCardViewer stcv)throws SQLException{
        ArrayList<StockType> liste = DataHelper.getInstance().getTip();
        int index= liste.indexOf(stcv.getStockType());
        stcv.getPreButton().setEnabled(true);

        stcv.setStockType(liste.get(index-1));
        stcv.setInfo(liste.get(index-1));
        stcv.setVisible(true);
        int indexC= liste.indexOf(stcv.getStockType());
        if (indexC==0){
            stcv.getNextButton().setEnabled(false);
        }

    }
}

