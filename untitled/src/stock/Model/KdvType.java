package stock.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KdvType {
    private int id;
    private String kdvCode;
    private String kdvName;
    private Double kdvRate;

    public KdvType(int id, String kdvCode, String kdvName, Double kdvRate) {
        this.id = id;
        this.kdvCode = kdvCode;
        this.kdvName = kdvName;
        this.kdvRate = kdvRate;
    }

    public KdvType(String kdvCode, String kdvName, Double kdvRate) {
        this.kdvCode = kdvCode;
        this.kdvName = kdvName;
        this.kdvRate = kdvRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKdvCode() {
        return kdvCode;
    }

    public void setKdvCode(String kdvCode) {
        this.kdvCode = kdvCode;
    }

    public String getKdvName() {
        return kdvName;
    }

    public void setKdvName(String kdvName) {
        this.kdvName = kdvName;
    }

    public Double getKdvRate() {
        return kdvRate;
    }

    public void setKdvRate(Double kdvRate) {
        this.kdvRate = kdvRate;
    }

    public void save() {
        try (Connection conn = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO `stock_card`.`kdv_type` (`kdv_tipi_kodu`, `kdv_tipi_adi`, `kdv_tipi_orani`) VALUES (?, ?, ?);")) {
            stmt.setString(1, getKdvCode());
            stmt.setString(2, getKdvName());
            stmt.setDouble(3, getKdvRate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        try (Connection conn = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM `stock_card`.`kdv_type` WHERE ( `kdv_tipi_kodu` = ? );")) {
            stmt.setString(1, getKdvCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
