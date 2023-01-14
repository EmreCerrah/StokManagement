package stock.Commands;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralActions extends AbstractAction implements ActionListener {

    private final CommandIF command;

    public GeneralActions(CommandIF command) {
        super();
        this.command = command;
    }

    public GeneralActions(String name, CommandIF command) {
        super(name);
        this.command = command;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        command.execute();
    }

    @Override
    public boolean accept(Object sender) {
        return super.accept(sender);
    }
}
