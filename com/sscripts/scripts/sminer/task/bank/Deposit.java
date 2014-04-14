package sminer.task.bank;


import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import sminer.SMiner;
import sminer.task.framework.Task;

import java.util.concurrent.Callable;


public class Deposit extends Task {
    public Deposit(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.opened() && ctx.backpack.select().count() == 28;
    }

    @Override
    public void execute() {
        if(ctx.bank.depositInventory()){
        	SMiner.status = "Deposit Inventory";
        	Condition.wait(new Callable<Boolean>() {
        		public Boolean call() throws Exception {
        			return ctx.backpack.select().isEmpty();
        		}
        	}, 500, 2);

        }
    }
}
