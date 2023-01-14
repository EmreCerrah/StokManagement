package stock.Commands;

import stock.Model.DataHelper;
import stock.Viwer.StockCardListFrameViwer;

public class RefreshStockCardListModelCommand implements CommandIF{
    private   StockCardListFrameViwer listInternalFrameViwer;

    public RefreshStockCardListModelCommand(StockCardListFrameViwer listInternalFrameViwer) {
        this.listInternalFrameViwer = listInternalFrameViwer;
    }

    @Override
    public void execute() {
        try {
            DataHelper.getInstance().loadAllItems();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        listInternalFrameViwer.getModels().fireTableDataChanged();
    }
}
