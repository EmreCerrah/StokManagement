package stock.Viwer;


import stock.Model.ListModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockCardListFrameViwer extends JInternalFrame {
    private ListModel models;
    private final JTable listTable;
    private final JMenuItem exportExel;
    private JMenuItem showCard;

    private JMenuItem exportPdf;
    private JMenuItem sendMail;
    private JButton refresh;
    private JMenuItem showKdvCards;
    private JMenuItem showStockTypeCards;


    public StockCardListFrameViwer(String title) {
        models = new ListModel();
        setTitle(title);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);
        ImageIcon imageIconP = new ImageIcon("untitled/src/stock/Model/icons/Data-List-icon.png");
        setFrameIcon(imageIconP);
        setBounds(10, 10, 650, 375);

        JToolBar toolBar = new JToolBar();
        getContentPane().add(toolBar, BorderLayout.PAGE_END);
        refresh = new JButton("Yenile");
        toolBar.add(refresh);

        listTable = new JTable();
        setTable(models);
        JTableHeader header = listTable.getTableHeader();
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(listTable, BorderLayout.CENTER);

        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(listTable, popupMenu);
        exportExel = new JMenuItem("Exel'e Aktar");
        popupMenu.add(exportExel);
        exportPdf = new JMenuItem("PDF Olarak Aç");
        popupMenu.add(exportPdf);
        sendMail = new JMenuItem("PDF'i Mail Olarak Gönder");
        popupMenu.add(sendMail);
        showCard = new JMenuItem("Stok Kartını Göster");
        popupMenu.add(showCard);
        showKdvCards = new JMenuItem("Kdv Tipi Kartını Göster");
        popupMenu.add(showKdvCards);
        showStockTypeCards = new JMenuItem("Stok Tipi Kartını Göster");
        popupMenu.add(showStockTypeCards);


        setVisible(true);
    }

    private void setTable(ListModel models) {
        listTable.setModel(models);
        listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    public JMenuItem getExportExel() {
        return exportExel;
    }

    public JMenuItem getShowCard() {
        return showCard;
    }

    public JMenuItem getExportPdf() {
        return exportPdf;
    }

    public JMenuItem getSendMail() {
        return sendMail;
    }

    public ListModel getModels() {
        return models;
    }

    public JTable getListTable() {
        return listTable;
    }

    public JButton getRefresh() {
        return refresh;
    }

    public JMenuItem getShowKdvCards() {
        return showKdvCards;
    }

    public JMenuItem getShowStockTypeCards() {
        return showStockTypeCards;
    }
}
