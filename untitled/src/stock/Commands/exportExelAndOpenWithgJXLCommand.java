package stock.Commands;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import stock.Model.DataHelper;
import stock.Model.StockItem;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class exportExelAndOpenWithgJXLCommand implements CommandIF{
    private static final String EXCEL_FILE_LOCATION = "Stok_listesi.xls";

    private void exportExelAndOpenWithgJXL() {
        WritableWorkbook myFirstWbook = null;
        File file = new File(EXCEL_FILE_LOCATION);
        try {
            myFirstWbook = Workbook.createWorkbook(file);

            // Excel sayfası oluşturma
            WritableSheet excelSheet = myFirstWbook.createSheet("Stok Litesi", 0);

            // Excel tablasunun başlıkları
            excelSheet.addCell(new Label(0, 0, "Stok Kodu"));
            excelSheet.addCell(new Label(1, 0, "Stok İsmi"));
            excelSheet.addCell(new Label(2, 0, "Stok Tipi"));
            excelSheet.addCell(new Label(3, 0, "Birimi"));
            excelSheet.addCell(new Label(3, 0, "Barkod No"));
            excelSheet.addCell(new Label(4, 0, "Oluşturma Tarihi"));
            excelSheet.addCell(new Label(5, 0, "Kdv Oranı"));
            excelSheet.addCell(new Label(6, 0, "Açıklama"));

            //Databaseden veri çekme
            DataHelper.getInstance().loadAllItems();
            ArrayList<StockItem> list = DataHelper.getInstance().getItemList();

            //Db'den verilerin Excel'e yazılması
            for (int i = 0; i < list.size(); i++) {
                excelSheet.addCell(new Label(0, i + 1, list.get(i).getStockCode()));
                excelSheet.addCell(new Label(1, i + 1, list.get(i).getName()));
                excelSheet.addCell(new Label(2, i + 1, list.get(i).getStockType().getName()));
                excelSheet.addCell(new Label(3, i + 1, list.get(i).getUnit()));
                excelSheet.addCell(new Label(3, i + 1, list.get(i).getBarcode()));
                excelSheet.addCell(new Label(4, i + 1, list.get(i).getDate()));
                excelSheet.addCell(new Label(5, i + 1, list.get(i).getKdvType().getKdvName()));
                excelSheet.addCell(new Label(6, i + 1, list.get(i).getDeclaration()));
            }
            myFirstWbook.write();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            if (myFirstWbook != null) {
                try {
                    myFirstWbook.close();
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void execute() {
        exportExelAndOpenWithgJXL();
    }
}
