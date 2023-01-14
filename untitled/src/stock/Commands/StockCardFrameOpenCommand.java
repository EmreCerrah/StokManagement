package stock.Commands;

import stock.Controller.StockCardFrameController;
import stock.Model.StockItem;

import stock.Viwer.MainFrameViewer;
import stock.Viwer.StockCardListFrameViwer;

public class StockCardFrameOpenCommand implements CommandIF{
    private  StockCardListFrameViwer listInternalFrameViwer;
    private StockItem current=null;
    MainFrameViewer mfv;

    public StockCardFrameOpenCommand(MainFrameViewer mfv) {
        this.mfv = mfv;
    }

    public StockCardFrameOpenCommand(StockCardListFrameViwer listInternalFrameViwer) {
        this.listInternalFrameViwer = listInternalFrameViwer;
    }

    @Override
    public void execute() {
        if(mfv==null) {
            int index = listInternalFrameViwer.getListTable().getSelectedRow();
            if (index > 0) {
                this.current = listInternalFrameViwer.getModels().getItem(index);
            }
            StockCardFrameController scfc = new StockCardFrameController(current);
            listInternalFrameViwer.getParent().add(scfc.getStockCardFrameViwer());
        }else if(listInternalFrameViwer==null){
            StockCardFrameController scfc= new StockCardFrameController();
            mfv.getContentPane().add(scfc.getStockCardFrameViwer());
        }
    }
}
