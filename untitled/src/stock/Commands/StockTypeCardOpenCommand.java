package stock.Commands;

import stock.Controller.StockTypeCardController;
import stock.Model.StockItem;
import stock.Viwer.MainFrameViewer;
import stock.Viwer.StockCardListFrameViwer;

public class StockTypeCardOpenCommand implements CommandIF {
    private MainFrameViewer mfv;
    private StockCardListFrameViwer listInternalFrameViwer;
    private StockItem current=null;

    public StockTypeCardOpenCommand(MainFrameViewer mfv) {
        this.mfv = mfv;
    }
    public StockTypeCardOpenCommand(StockCardListFrameViwer listInternalFrameViwer) {
        this.listInternalFrameViwer = listInternalFrameViwer;
    }

    @Override
    public void execute() {
        if (mfv==null){
            int index = listInternalFrameViwer.getListTable().getSelectedRow();
            this.current = listInternalFrameViwer.getModels().getItem(index);

            StockTypeCardController stcc= new StockTypeCardController(current.getStockType());
            listInternalFrameViwer.getParent().add(stcc.getStcv());
        }else
        if(listInternalFrameViwer==null){
            StockTypeCardController stcc= new StockTypeCardController();
            mfv.getContentPane().add(stcc.getStcv());
        }
    }
}
