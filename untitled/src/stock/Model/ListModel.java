package stock.Model;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

public class ListModel extends AbstractTableModel {
    private String[] isimler = {"Stok Kodu", "Stok İsmi", "Stok Tipi", "Birimi", "KDV Tipi", "Barkodu", "Açıklama", "Kayıt Tarihi"};
    private ArrayList<StockItem> list;
    private static ArrayList<StockType> tip = new ArrayList<>();
    private ArrayList<KdvType> kdv;

    public ListModel() {
        this.list = DataHelper.getInstance().getItemList();
        //loadtype();
        //loadKdv();

    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int column) {
        return isimler[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return list.get(rowIndex).getStockCode();
            case 1:
                return list.get(rowIndex).getName();
            case 2:
                return list.get(rowIndex).getStockType().getName();
            case 3:
                return list.get(rowIndex).getUnit();
            case 4:
                return list.get(rowIndex).getKdvType().getKdvName();
            case 5:
                return list.get(rowIndex).getBarcode();
            case 6:
                return list.get(rowIndex).getDeclaration();
            case 7:
                return list.get(rowIndex).getDate();

            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    public StockItem getItem(int rowIndex) {
        if (rowIndex < list.size()) {
            return list.get(rowIndex);
        } else return null;
    }

    private ResultSet loadDbToTable() {
        ResultSet sonuc;
        try (Connection con = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             Statement sorgu = con.createStatement()) {
            sonuc = sorgu.executeQuery("SELECT * FROM stock_card.stock_card_list;");
            while (sonuc.next()) {
                String stockCode = sonuc.getString(1);
                String name = sonuc.getString(2);
                int stockType = sonuc.getInt(3);
                String unit = sonuc.getString(4);
                String barcode = sonuc.getString(5);
                double KDV = sonuc.getDouble(6);
                String declaration = sonuc.getString(7);
                Date date = sonuc.getDate(8);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sonuc;
    }

    public static void loadtype() {
        try (Connection con = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             Statement sorgu = con.createStatement()) {
            ResultSet sonuc = sorgu.executeQuery("SELECT * FROM stock_card.stock_type;");
            while (sonuc.next()) {
                int id = sonuc.getInt(1);
                String kod = sonuc.getString(2);
                String name = sonuc.getString(3);
                String dec = sonuc.getString(3);

                tip.add(new StockType(id, kod, name, dec));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<StockType> getTip() {
        return tip;
    }
}
