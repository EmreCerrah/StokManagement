package stock.Commands;

import stock.Controller.MainFrameController;
import stock.Viwer.MainFrameViewer;

import javax.swing.*;

public abstract class BaseFrameCommand implements CommandIF{
    private MainFrameViewer mainFrame;
    private JInternalFrame iframe;

    public BaseFrameCommand(MainFrameViewer mainFrame){
        this.mainFrame=mainFrame;
    }

    @Override
    public void execute() {
        //Frame Intance oluşturur.
        iframe = getFrameInstnce();
        //Frame üzerindeki listener sınıfları set edilir.
        setListeners(iframe);
        //Frame kullanıcıya gösterir.
        iframe.setVisible(true);
        mainFrame.getContentPane().add(iframe);
    }

    protected abstract void setListeners(JInternalFrame iframe);

    public abstract JInternalFrame getFrameInstnce();
}
