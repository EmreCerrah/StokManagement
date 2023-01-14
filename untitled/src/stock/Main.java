package stock;

import stock.Controller.MainFrameController;
import stock.Model.DataHelper;

public class Main {
    public static void main(String[] args) throws Exception {
        DataHelper.getInstance().loadAllItems();
        MainFrameController mf = new MainFrameController();
    }
}
