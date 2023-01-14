package stock.Commands;

import stock.Model.DataHelper;
import stock.Model.KdvType;
import stock.Model.StockItem;
import stock.Model.StockType;
import stock.Viwer.StockCardFrameViwer;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class StockCardFrameSaveCommand implements CommandIF{
    private StockCardFrameViwer stockCardFrameViwer;

    public StockCardFrameSaveCommand(StockCardFrameViwer stockCardFrameViwer) {
        this.stockCardFrameViwer=stockCardFrameViwer;
    }

    @Override
    public void execute() {
        if(ConfirmDialogInFrame("Kaydetmek istediğinise emin misiniz?","Stok Kartı Kaydet")==0){
            try {
                if (stockCardFrameViwer.getCurrent()==null){
                    createNewItem();
                    stockCardFrameViwer.getCurrent().saveItem();
                }else {
                    stockCardFrameViwer.getCurrent().updateItem();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private int ConfirmDialogInFrame(String message, String title) {
        int input = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        return input;
    }
    public void createNewItem(){
        StockType stockCurr=null;
        KdvType kdvCurr=null;
        String code =  stockCardFrameViwer.getStockCodeField().getText();
        String name =  stockCardFrameViwer.getStockNameField().getText();
        String type = stockCardFrameViwer.getStockTypeField().getSelectedItem().toString();

        try {
            stockCurr= getTypeToDb(type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String unit =  stockCardFrameViwer.getUnitField().getSelectedItem().toString();
        String barcodes =  stockCardFrameViwer.getBarcodeField().getText();
        String kdv =  stockCardFrameViwer.getKdvField().getSelectedItem().toString();

        try {
            kdvCurr= getKdvToDb(kdv);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String dec =  stockCardFrameViwer.getDecField().getText();
        LocalDate date = LocalDate.now();
        Date dateF = Date.valueOf(date);

        stockCardFrameViwer.setCurrent(new StockItem(code, name, unit, barcodes, dec, dateF));
        stockCardFrameViwer.getCurrent().setStockType(stockCurr);
        stockCardFrameViwer.getCurrent().setKdvType(kdvCurr);
    }
    public StockType getTypeToDb(String type ) throws SQLException {
        StockType st = null;
        try (Connection conn = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM stock_card.stock_type where stok_tip_adi=?;")){
            stmt.setString(1,type);
            ResultSet sonuc = stmt.executeQuery();
            while (sonuc.next()) {
                int id = sonuc.getInt(1);
                String typeCod = sonuc.getString(2);
                String typeName = sonuc.getString(3);
                String typeDec = sonuc.getString(4);
                st = new StockType(id,typeCod,typeName,typeDec);
            }
            return st;
        }
    }
    public KdvType getKdvToDb(String type ) throws SQLException {
        KdvType kt = null;
        try (Connection conn = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM stock_card.kdv_type where kdv_tipi_adi=?;")){
            stmt.setString(1,type);
            ResultSet sonuc = stmt.executeQuery();
            while (sonuc.next()) {
                int id = sonuc.getInt(1);
                String kdvCod = sonuc.getString(2);
                String kdvName = sonuc.getString(3);
                double kdvRate = sonuc.getDouble(4);
                kt = new KdvType(id,kdvCod,kdvName,kdvRate);
            }
            return kt;
        }
    }
}