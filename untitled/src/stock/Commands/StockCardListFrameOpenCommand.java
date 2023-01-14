package stock.Commands;

import stock.Controller.StockCardListFrameControler;
import stock.Viwer.MainFrameViewer;

public class StockCardListFrameOpenCommand implements CommandIF{
    MainFrameViewer mfv;

    public StockCardListFrameOpenCommand(MainFrameViewer mfv) {
        this.mfv = mfv;
    }

    @Override
    public void execute() {
        StockCardListFrameControler sclifc=new StockCardListFrameControler();
        mfv.getContentPane().add(sclifc.getListInternalFrameViwer());
    }
}
