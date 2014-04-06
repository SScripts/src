package sminer.task.bank;

import org.powerbot.script.Area;
import org.powerbot.script.rt6.ClientContext;
import sminer.gui.Gui;
import sminer.SMiner;
import sminer.task.framework.Task;


public class OpenBank extends Task {
    public OpenBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        final Area bankArea = Gui.loc.getBankAreas();
        return bankArea.contains(ctx.players.local().tile()) && ctx.backpack.select().count() == 28 && !ctx.bank.opened();
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()){
            SMiner.status = "Opening Bank";


        } else ctx.bank.open();
        SMiner.status = "Opening Bank";
    }
}
