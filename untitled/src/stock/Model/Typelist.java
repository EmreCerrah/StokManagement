package stock.Model;


import java.sql.*;
import java.util.HashMap;

public class Typelist {
    private static HashMap<Integer, String> tip;

    private static final Typelist instance = new Typelist();

    public Typelist() {
        tip = new HashMap<>();
    }

    private void loadtype() {
        try (Connection con = DriverManager.getConnection(DataHelper.getInstance().getConnectionPath());
             Statement sorgu = con.createStatement()) {
            ResultSet sonuc = sorgu.executeQuery("SELECT id, adi FROM stock_card.kdv_type;");
            while (sonuc.next()) {
                int id = sonuc.getInt(1);
                String name = sonuc.getString(2);
                System.out.println(id + name);
                tip.put(id, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<Integer, String> getTip() {
        return tip;
    }

    public static Typelist getInstance() {
        return instance;
    }
}
