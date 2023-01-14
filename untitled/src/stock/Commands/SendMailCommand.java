package stock.Commands;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import stock.Model.DataHelper;
import stock.Model.StockItem;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.*;

public class SendMailCommand implements CommandIF{
    private static final String destFileName = "report.pdf";

    @Override
    public void execute() {
        try {
            sendMail();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    private void sendMail() throws MessagingException {

        final String username = "cerrah.emre@yandex.com";
        final String password = "Cash1223";

        final String host = "smtp.yandex.com.tr";

        String to = inputDialogInFrame("Mail Adresi Giriniz");

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smpt.ssl.enable", "true");
        //props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.port", "587");
        //props.put("mail.smpt.quitwait", "false");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        //Compose the message
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("PDF Raporlama");

            if (destFileName == null) {
                writePDFReport();
            }
            DataSource source = new FileDataSource(destFileName);
            message.setDataHandler(new DataHandler(source));
            message.setFileName(destFileName);

            //send the message
            System.out.println("prepering mail? ...");
            Transport.send(message, message.getAllRecipients());
            System.out.println("message sent successfully...");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
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
    private String inputDialogInFrame(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return input;
    }
}
