package stock.Commands;

import stock.Viwer.KdvTypeCardViewer;
import stock.Viwer.StockCardFrameViwer;
import stock.Viwer.StockTypeCardViewer;

import javax.swing.*;

public class TypeCardSaveCommand implements CommandIF {
    private JInternalFrame frame;
    public TypeCardSaveCommand(JInternalFrame frame) {
        this.frame = frame;
    }
    @Override
    public void execute() {
        if (frame instanceof StockTypeCardViewer) {
            StockTypeCardViewer((StockTypeCardViewer) frame);
        } else if (frame instanceof KdvTypeCardViewer) {
            kdvTypeCardViwer((KdvTypeCardViewer) frame);
    }
}

    private void StockTypeCardViewer(StockTypeCardViewer frame) {
        frame.getStockType().save();
    }

    private void kdvTypeCardViwer(KdvTypeCardViewer frame) {
        frame.getKdvType().save();
    }
}
