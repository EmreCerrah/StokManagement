package stock.Controller;

import stock.Commands.*;
import stock.Model.DataHelper;
import stock.Model.KdvType;
import stock.Model.StockItem;
import stock.Model.StockType;
import stock.Viwer.StockCardFrameViwer;

import java.util.ArrayList;

public class StockCardFrameController {
    private StockCardFrameViwer stockCardFrameViwer;
    private ArrayList<StockItem> liste= DataHelper.getInstance().getItemList();

    public StockCardFrameController() {
        stockCardFrameViwer = new StockCardFrameViwer("Stok Kartı");
        setActionListener();
        stockCardFrameViwer.getDeleteButton().setEnabled(false);
        stockCardFrameViwer.getCopyButton().setEnabled(false);
    }

    public StockCardFrameController(StockItem current) {
        stockCardFrameViwer = new StockCardFrameViwer("Stok Kartı");
        stockCardFrameViwer.setCardInfo(current);
        checkButtons();
        setActionListener();
    }
    private void setActionListener() {
        stockCardFrameViwer.getCopyButton().addActionListener(new GeneralActions(new StockCardFrameCopyCommand(stockCardFrameViwer)));
        stockCardFrameViwer.getDeleteButton().addActionListener(new GeneralActions(new StockCardFrameDeleteCommand(stockCardFrameViwer)));
        stockCardFrameViwer.getSaveButton().addActionListener(new GeneralActions(new StockCardFrameSaveCommand(stockCardFrameViwer)));
        stockCardFrameViwer.getNextButton().addActionListener(new GeneralActions(new NextArrowCommand (stockCardFrameViwer)));
        stockCardFrameViwer.getPreButton().addActionListener(new GeneralActions(new PreArrowCommand(stockCardFrameViwer)));
    }
    private void checkButtons(){
        int index= liste.indexOf(stockCardFrameViwer.getCurrent());
        if (index==liste.size()-1){
            stockCardFrameViwer.getNextButton().setEnabled(false);
        }else if(index==0){
            stockCardFrameViwer.getPreButton().setEnabled(false);
        }
    }
    public StockCardFrameViwer getStockCardFrameViwer() {
        return stockCardFrameViwer;
    }
}
