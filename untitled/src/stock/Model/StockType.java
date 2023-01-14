package stock.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockType {

    private int id;
    private String cod;
    private String name;
    private String dec;

    public StockType(int id, String cod, String name, String dec) {
        this.id = id;
        this.cod = cod;
        this.name = name;
        this.dec = dec;
    }

    public StockType(String cod, String name, String dec) {
        this.cod = cod;
        this.name = name;
        this.dec = dec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public void save() {
        try (Connection conn = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO `stock_card`.`stock_type` (`stok_tip_kodu`, `stok_tip_adi`, `stok_tip_açıklama`) VALUES (?, ?, ?);")) {
            stmt.setString(1, getCod());
            stmt.setString(2, getName());
            stmt.setString(3, getDec());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        try (Connection conn = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM `stock_card`.`stock_type` WHERE ( `stok_tip_kodu` = ? );")) {
            stmt.setString(1, getCod());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
