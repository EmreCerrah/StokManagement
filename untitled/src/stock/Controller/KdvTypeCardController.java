package stock.Controller;

import stock.Commands.*;
import stock.Model.DataHelper;
import stock.Model.KdvType;
import stock.Viwer.KdvTypeCardViewer;

import java.sql.SQLException;
import java.util.ArrayList;

public class KdvTypeCardController {
    private KdvTypeCardViewer ktcv;
    private ArrayList<KdvType> liste;
    {
        try {
            liste = DataHelper.getInstance().getKdv();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public KdvTypeCardController() {
        ktcv= new KdvTypeCardViewer();
        setActionListener();
    }
    public KdvTypeCardController(KdvType kdvType) {
        ktcv= new KdvTypeCardViewer(kdvType);
        ktcv.getPreButton().setEnabled(false);
        setActionListener();
    }
    public void setActionListener(){
        ktcv.getSaveButton().addActionListener(new GeneralActions(new TypeCardSaveCommand(ktcv)));
        ktcv.getDeleteButton().addActionListener(new GeneralActions(new TypeCardDeleteCommand(ktcv)));
        ktcv.getNextButton().addActionListener(new GeneralActions(new NextArrowCommand(ktcv)));
        ktcv.getPreButton().addActionListener(new GeneralActions(new PreArrowCommand(ktcv)));
    }
    public KdvTypeCardViewer getKtcv() {
        return ktcv;
    }
}
