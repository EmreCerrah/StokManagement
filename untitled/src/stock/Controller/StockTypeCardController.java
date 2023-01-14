package stock.Controller;

import stock.Commands.*;
import stock.Model.DataHelper;
import stock.Model.StockType;
import stock.Viwer.StockTypeCardViewer;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockTypeCardController {
    private final StockTypeCardViewer stcv;
    private final ArrayList<StockType> liste;

    {
        try {
            liste = DataHelper.getInstance().getTip();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public StockTypeCardController() {
        stcv = new StockTypeCardViewer();
        setActionListener();
    }

    public StockTypeCardController(StockType stockType) {
        stcv = new StockTypeCardViewer(stockType);
        stcv.getPreButton().setEnabled(false);
        setActionListener();
    }

    public void setActionListener() {
        stcv.getSaveButton().addActionListener(new GeneralActions(new TypeCardSaveCommand(stcv)));
        stcv.getDeleteButton().addActionListener(new GeneralActions(new TypeCardDeleteCommand(stcv)));
        stcv.getNextButton().addActionListener(new GeneralActions(new NextArrowCommand(stcv)));
        stcv.getPreButton().addActionListener(new GeneralActions(new PreArrowCommand(stcv)));
    }

    public StockTypeCardViewer getStcv() {
        return stcv;
    }
}
