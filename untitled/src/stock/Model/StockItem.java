package stock.Model;

import java.sql.*;

public class StockItem {

    private String stockCode;
    private String name;
    private StockType stockType;

    private String unit;
    private String barcode;
    private KdvType kdvType;
    private String declaration;
    private Date date;

    public StockItem(String stockCode, String name, String unit, String barcode, String declaration, Date date) {
        this.stockCode = stockCode;
        this.name = name;
        this.unit = unit;
        this.barcode = barcode;
        this.declaration = declaration;
        this.date = date;
    }


    @Override
    public String toString() {
        return
                "Stok kodu:" + stockCode + ", Stok Adı: " + name;
    }

    public String getStockCode() {
        return stockCode;
    }


    public StockType getStockType() {
        return stockType;
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }


    public String getDeclaration() {
        return declaration;
    }

    public String getDate() {
        return String.valueOf(date);
    }

    public Date getDateFormat() {
        return date;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
    }

    public KdvType getKdvType() {
        return kdvType;
    }

    public void setKdvType(KdvType kdvType) {
        this.kdvType = kdvType;
    }

    //Database
    public void saveItem() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO stock_card.stock_card_list (kod,ad,tip_id,birim,barkod,kdv_id,aciklama,olusturma_tarihi) VALUES (?, ?, ?, ? , ?, ?, ?, ?)")) {
            stmt.setString(1, getStockCode());
            stmt.setString(2, getName());
            stmt.setInt(3, getStockType().getId());
            stmt.setString(4, getUnit());
            stmt.setString(5, getBarcode());
            stmt.setInt(6, getKdvType().getId());
            stmt.setString(7, getDeclaration());
            stmt.setDate(8, getDateFormat());
            stmt.executeUpdate();
        }
    }

    public void deleteItem() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM stock_card.stock_card_list WHERE ( `kod` = ? );")) {
            stmt.setString(1, getStockCode());
            stmt.executeUpdate();
        }
    }

    public void updateItem() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             PreparedStatement stmt = conn.prepareStatement("UPDATE `stock_card`.`stock_card_list` SET `ad` = ? , `tip_id` = ? , `birim` = ? , `barkod` = ? , `kdv_id` = ? , `aciklama` = ? , `olusturma_tarihi` = ? WHERE `kod` = ? ")) {
            //Condition
            stmt.setString(8, getStockCode());

            stmt.setString(1, getName());                               // UPDATE `stock_card`.`stock_card_list` SET `ad` = 'white', `tip_id` = '1', `barkod` = '546', `kdv_id` = '3' WHERE (`kod` = 'gg-14564') ;
            stmt.setInt(2, getStockType().getId());
            stmt.setString(3, getUnit());
            stmt.setString(4, getBarcode());
            stmt.setDouble(5, getKdvType().getId());
            stmt.setString(6, getDeclaration());
            stmt.setDate(7, getDateFormat());

            stmt.executeUpdate();
        }
    }

    public void setStockCode(String input) {
        stockCode = input;
    }
}
