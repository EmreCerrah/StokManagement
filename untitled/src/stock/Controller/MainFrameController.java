package stock.Controller;

import stock.Commands.*;
import stock.Viwer.MainFrameViewer;

public class MainFrameController {
 MainFrameViewer mfv;

    public MainFrameController() {
        this.mfv = new MainFrameViewer();
        setButtonListeners();
    }
    public void setButtonListeners(){
    mfv.getOpenStockListTableMenuButton().addActionListener(new GeneralActions(new StockCardListFrameOpenCommand(mfv)));
    mfv.getAddNewStockCardMenuButton().addActionListener(new GeneralActions(new StockCardFrameOpenCommand(mfv)));
    mfv.getAddNewStockTypeMenuButton().addActionListener(new GeneralActions(new StockTypeCardOpenCommand(mfv)));
    mfv.getAddNewKdvTypeMenuButton().addActionListener(new GeneralActions(new KDVTypeCardOpenCommand(mfv)));
    }

}
