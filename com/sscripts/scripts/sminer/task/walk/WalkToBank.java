package sminer.task.walk;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import sminer.SMiner;
import sminer.task.framework.Task;

import java.util.concurrent.Callable;


public class WalkToBank extends Task {
    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        final Area bankArea = SMiner.loc.getBankAreas();
        return !bankArea.contains(ctx.players.local().tile())
                && ctx.backpack.select().count() == 28;


    }

    @Override
    public void execute() {
        Tile[] path = SMiner.loc.getBankPath();
        ctx.movement.newTilePath(path).randomize(0, 2).traverse();
        SMiner.status = "Walking to Bank";
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.movement.distance(ctx.players.local().tile(), ctx.movement.destination()) < 14;
            }
        }, 250, 20);


    }
}
