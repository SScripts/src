package sminer.task.bank;

import sminer.gui.Gui;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Area;
import sminer.SMiner;
import sminer.task.framework.Task;


public class OpenBank extends Task {
    public OpenBank(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        final Area bankArea = Gui.loc.getBankAreas();
        return bankArea.contains(ctx.players.local().getLocation()) && ctx.backpack.select().count() == 28 && !ctx.bank.isOpen();
    }

    @Override
    public void execute() {
        if (ctx.bank.open()){
            SMiner.status = "Opening Bank";

        } ctx.bank.open();
        SMiner.status = "Opening Bank";
    }
}
