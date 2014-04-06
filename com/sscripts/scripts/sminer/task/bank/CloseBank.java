package sminer.task.bank;


import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.Condition;
import sminer.SMiner;
import sminer.task.framework.Task;

import java.util.concurrent.Callable;


public class CloseBank extends Task {


    public CloseBank(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate() {
        return ctx.bank.open() && ctx.backpack.select().isEmpty();
    }

    @Override
    public void execute() {
        if (ctx.bank.close()){
            SMiner.status = "Closing Bank";
            Condition.wait(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return !ctx.bank.open();
                }
            }, 500, 2);
        }else ctx.bank.close();
        SMiner.status = "Closing Bank";
    }


}
