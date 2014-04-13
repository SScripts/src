package sminer.task.bank;

import java.util.concurrent.Callable;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;

import sminer.SMiner;
import sminer.task.framework.Task;


public class OpenBank extends Task {
    public OpenBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        final Area bankArea = SMiner.loc.getBankAreas();
        return bankArea.contains(ctx.players.local().tile()) && ctx.backpack.select().count() == 28 && !ctx.bank.opened();
    }

    @Override
    public void execute() {
        ctx.bank.opened();
        SMiner.status = "Opening Bank";
        Condition.wait(new Callable<Boolean>() {
        	public Boolean call() throws Exception {
        		return ctx.bank.opened();
        	}
        }, 500, 2);

    }
}
