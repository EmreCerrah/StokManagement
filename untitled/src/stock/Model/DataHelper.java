package stock.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class DataHelper {
    private static final DataHelper instance = new DataHelper();
    private final String USERNAME = "root";
    private final String PASSWORD = "Mysql";
    private final String CONNETION_TYPE = "jdbc:mysql:";
    private final String URL = "//localhost:3306";
    private final ArrayList<StockItem> stockItemList = new ArrayList<>();
    private ArrayList<StockType> tip;
    private ArrayList<KdvType> kdv;

    private DataHelper() {
        try {
            loadAllItems();
            //loadtype();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DataHelper getInstance() {
        return instance;
    }

    public StockItem FindItem(String stockCode) {
        for (int i = 0; i < stockItemList.size(); i++) {
            if (stockItemList.get(i).getStockCode().compareTo(stockCode) == 0) {
                return stockItemList.get(i);
            } else {
                continue;
            }
        }
        return null;
    }

    public void insertOrUpdateItem(StockItem o) throws Exception {
        this.stockItemList.add(o);
    }

    public void deleteItem(StockItem o) {
        stockItemList.remove(o);
    }

    public void loadAllItems() throws Exception {
        try (Connection con = DriverManager.getConnection(getConnectionPath());
             Statement sorgu = con.createStatement()) {
            String sql = "SELECT kod, ad, stok_tip_kodu, tip_id ,stok_tip_adi, stok_tip_açıklama, birim ,barkod, kdv_id, kdv_tipi_kodu , kdv_tipi_adi, kdv_tipi_orani, aciklama,olusturma_tarihi " +
                    "FROM stock_card.stock_card_list LEFT JOIN stock_card.stock_type ON tip_id=stock_card.stock_type.id LEFT JOIN stock_card.kdv_type ON kdv_id=stock_card.kdv_type.id;";
            ResultSet sonuc = sorgu.executeQuery(sql);
            stockItemList.clear();
            while (sonuc.next()) {
                String stockCode = sonuc.getString(1);
                String name = sonuc.getString(2);
                int stockTypeId = sonuc.getInt(3);
                String stockTypeCod = sonuc.getString(4);
                String stockTypename = sonuc.getString(5);
                String stockTypeDec = sonuc.getString(6);

                String unit = sonuc.getString(7);
                String barcode = sonuc.getString(8);

                int kdvId = sonuc.getInt(9);
                String kdvCod = sonuc.getString(10);
                String kdvName = sonuc.getString(11);
                Double kdvRate = sonuc.getDouble(12);

                String declaration = sonuc.getString(13);
                Date date = sonuc.getDate(14);

                StockType st = new StockType(stockTypeId, stockTypeCod, stockTypename, stockTypeDec);
                StockItem stockItem = new StockItem(stockCode, name, unit, barcode, declaration, date);
                stockItem.setKdvType(new KdvType(kdvId, kdvCod, kdvName, kdvRate));
                stockItem.setStockType(st);

                this.stockItemList.add(stockItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        private void insertOrUpdateItemToDB(stock.Model.Item current) throws Exception {
            if (hasItem(current)) {
                saveEditedItem(current);
            } else {
                saveNewItem(current);
            }
        }
    */
    public ArrayList<StockItem> getItemList() {
        return stockItemList;
    }


    public String getConnectionPath() {
        return CONNETION_TYPE + URL + "?user=" + USERNAME + "&password=" + PASSWORD;
    }

    public String[] loadTypeName() throws Exception {
        HashMap<Integer, String> tip = new HashMap<>();
        try (Connection con = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             Statement sorgu = con.createStatement()) {
            ResultSet sonuc = sorgu.executeQuery("SELECT id , stok_tip_adi FROM stock_card.stock_type;");
            while (sonuc.next()) {
                int id = sonuc.getInt(1);
                String name = sonuc.getString(2);
                tip.put(id, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[] values;
        values = tip.values().toArray(new String[tip.size()]);
        return values;
    }

    public String[] loadKdvName() throws Exception {
        HashMap<Integer, String> kdv = new HashMap<>();
        try (Connection con = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             Statement sorgu = con.createStatement()) {
            ResultSet sonuc = sorgu.executeQuery("SELECT id, kdv_tipi_adi FROM stock_card.kdv_type;");
            while (sonuc.next()) {
                int id = sonuc.getInt(1);
                String name = sonuc.getString(2);
                kdv.put(id, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[] values;
        values = kdv.values().toArray(new String[kdv.size()]);
        return values;
    }

    private void loadtype() throws SQLException {
        tip = new ArrayList<>();
        Connection con = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
        Statement sorgu = con.createStatement();
        ResultSet sonuc = sorgu.executeQuery("SELECT * FROM stock_card.stock_type;");
        while (sonuc.next()) {
            int id = sonuc.getInt(1);
            String kod = sonuc.getString(2);
            String name = sonuc.getString(3);
            String dec = sonuc.getString(4);

            tip.add(new StockType(id, kod, name, dec));
        }
    }

    private void loadkdv() throws SQLException {
        kdv = new ArrayList<>();
        Connection con = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
        Statement sorgu = con.createStatement();
        ResultSet sonuc = sorgu.executeQuery("SELECT * FROM stock_card.kdv_type;");
        while (sonuc.next()) {
            int id = sonuc.getInt(1);
            String kod = sonuc.getString(2);
            String name = sonuc.getString(3);
            double rate = sonuc.getDouble(4);

            kdv.add(new KdvType(id, kod, name, rate));
        }
    }

    public ArrayList<StockType> getTip() throws SQLException {
        loadtype();
        return tip;
    }

    public ArrayList<KdvType> getKdv() throws SQLException {
        loadkdv();
        return kdv;
    }
}
