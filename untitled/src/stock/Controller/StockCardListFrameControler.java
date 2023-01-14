package stock.Controller;

import stock.Commands.*;

import stock.Viwer.StockCardListFrameViwer;


public class StockCardListFrameControler {
    private final StockCardListFrameViwer listInternalFrameViwer;
    public StockCardListFrameControler() {
        this.listInternalFrameViwer = new StockCardListFrameViwer("Stok Kartı Listesi");
        setActionListener();
    }
    public void setActionListener() {
        listInternalFrameViwer.getShowCard().addActionListener(new GeneralActions(new StockCardFrameOpenCommand(listInternalFrameViwer)));
        listInternalFrameViwer.getExportExel().addActionListener(new GeneralActions(new exportExelAndOpenWithgJXLCommand()));
        listInternalFrameViwer.getExportPdf().addActionListener(new GeneralActions(new GenerateJasperRaportCommand()));
        listInternalFrameViwer.getSendMail().addActionListener(new GeneralActions(new SendMailCommand()));
        listInternalFrameViwer.getRefresh().addActionListener(new GeneralActions(new RefreshStockCardListModelCommand(listInternalFrameViwer)));
        listInternalFrameViwer.getShowKdvCards().addActionListener(new GeneralActions(new KDVTypeCardOpenCommand(listInternalFrameViwer)));
        listInternalFrameViwer.getShowStockTypeCards().addActionListener(new GeneralActions(new StockTypeCardOpenCommand(listInternalFrameViwer)));
    }
    public StockCardListFrameViwer getListInternalFrameViwer() {
        return listInternalFrameViwer;
    }
}


