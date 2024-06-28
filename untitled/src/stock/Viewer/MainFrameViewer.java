package stock.Viwer;

import stock.Controller.StockCardListFrameControler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainFrameViewer extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final JMenuItem getListTable;
    private final JMenuItem addNewCard;
    private final JMenuItem addNewType;
    private final JMenuItem addNewKdv;

    StockCardFrameViwer cardInternalFrame;

    public MainFrameViewer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu NewMenu = new JMenu("Menu");
        menuBar.add(NewMenu);

        getListTable = new JMenuItem("Stok Kart Listesi");
        NewMenu.add(getListTable);
        addNewCard = new JMenuItem("Yeni Stok Kart Ekle");
        NewMenu.add(addNewCard);
        addNewType = new JMenuItem("Yeni Stok Tip Ekle");
        NewMenu.add(addNewType);
        addNewKdv = new JMenuItem("Yeni Kdv Tipi Ekle");
        NewMenu.add(addNewKdv);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Stok Kartı Listesi
        StockCardListFrameControler sclifc = new StockCardListFrameControler();
        contentPane.add(sclifc.getListInternalFrameViwer());

        setVisible(true);
    }

    public JMenuItem getOpenStockListTableMenuButton() {
        return getListTable;
    }

    public JMenuItem getAddNewStockCardMenuButton() {
        return addNewCard;
    }

    public JMenuItem getAddNewStockTypeMenuButton() {
        return addNewType;
    }

    public JMenuItem getAddNewKdvTypeMenuButton() {
        return addNewKdv;
    }

    public StockCardFrameViwer getCardInternalFrame() {
        return cardInternalFrame;
    }
}

