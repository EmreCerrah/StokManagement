package stock.Commands;

import stock.Controller.KdvTypeCardController;
import stock.Controller.StockTypeCardController;
import stock.Model.StockItem;
import stock.Viwer.MainFrameViewer;
import stock.Viwer.StockCardListFrameViwer;

public class KDVTypeCardOpenCommand implements CommandIF{
    private MainFrameViewer mfv;
    private StockCardListFrameViwer listInternalFrameViwer;
    private StockItem current=null;


    public KDVTypeCardOpenCommand(MainFrameViewer mfv) {
        this.mfv = mfv;
    }
    public KDVTypeCardOpenCommand(StockCardListFrameViwer listInternalFrameViwer) {
        this.listInternalFrameViwer = listInternalFrameViwer;
    }

    @Override
    public void execute() {
        if (mfv==null){
            int index = listInternalFrameViwer.getListTable().getSelectedRow();
            this.current = listInternalFrameViwer.getModels().getItem(index);

            KdvTypeCardController ktcc= new KdvTypeCardController(current.getKdvType());
            listInternalFrameViwer.getParent().add(ktcc.getKtcv());
        }else
        if(listInternalFrameViwer==null){
            KdvTypeCardController ktcc= new KdvTypeCardController();
            mfv.getContentPane().add(ktcc.getKtcv());
        }
    }
}
