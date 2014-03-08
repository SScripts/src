package sminer.task.bank;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import sminer.SMiner;
import sminer.task.framework.Task;

import java.util.concurrent.Callable;


public class Deposit extends Task {
    public Deposit(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.isOpen() && ctx.backpack.select().count() == 28;
    }

    @Override
    public void execute() {
        if (ctx.bank.depositInventory()){
            SMiner.status = "Deposit Inventory";
            Condition.wait(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return ctx.backpack.select().isEmpty();
                }
            }, 500, 2);
        } else ctx.bank.depositInventory();
        SMiner.status = "Deposit Inventory";
    }
}
