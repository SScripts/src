package sminer.task.walk;

import sminer.gui.Gui;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.Tile;
import sminer.SMiner;
import sminer.task.framework.Task;

import java.util.concurrent.Callable;


public class WalkToBank extends Task {
    public WalkToBank(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        final Area bankArea = Gui.loc.getBankAreas();
        return !bankArea.contains(ctx.players.local().getLocation())
                && ctx.backpack.select().count() == 28
                && !SMiner.drop;

    }

    @Override
    public void execute() {
        Tile[] path = Gui.loc.getBankPath();
        ctx.movement.newTilePath(path).traverse();
        SMiner.status = "Walking to Bank";
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.movement.getDistance(ctx.players.local(), ctx.movement.getDestination()) < 14;
            }
        }, 250, 20);


    }
}
