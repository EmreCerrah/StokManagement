package stock.Commands;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import stock.Model.DataHelper;
import stock.Model.StockItem;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GenerateJasperRaportCommand implements CommandIF {
    private static final String destFileName = "report.pdf";
    @Override
    public void execute() {
        try {
            showPDFRaport(writePDFReport());
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
    private void showPDFRaport(JasperPrint jasperPrint) {
        JFrame frame = new JFrame("Jasper Rapor");
        JRViewer s = new JRViewer(jasperPrint);
        frame.add(s);
        frame.setSize(new Dimension(750, 650));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private JasperPrint writePDFReport() throws JRException {
        String sourceFile = "untitled/src/jasper/Blank_A4_Landscape.jasper";
        String sourcejrxml = "untitled/src/jasper/Blank_A4_Landscape.jrxml";
        ArrayList<StockItem> list = DataHelper.getInstance().getItemList();
        Collection<Map<String, ?>> dataSource = new Vector<>();
        HashMap<String, Object> datamap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {

            datamap = new HashMap<>();
            datamap.put("STOK_KODU", list.get(i).getStockCode());
            datamap.put("STOK_ADI", list.get(i).getName());
            datamap.put("STOK_TİPİ", list.get(i).getStockType().getName());
            datamap.put("STOK_BİRİMİ", list.get(i).getUnit());
            datamap.put("BARKODU", list.get(i).getBarcode());
            datamap.put("KDV_TİPİ", list.get(i).getKdvType().getKdvName());
            datamap.put("KAYIT_TARİHİ", list.get(i).getDate());
            datamap.put("AÇIKLAMA", list.get(i).getDeclaration());

            dataSource.add(datamap);
        }

        JRMapCollectionDataSource mapColDataSource = new JRMapCollectionDataSource(dataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFile, datamap, mapColDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);

        return jasperPrint;
    }
}

