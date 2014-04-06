package sminer.task.walk;

import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import sminer.gui.Gui;
import sminer.SMiner;
import sminer.task.framework.Task;

import java.util.concurrent.Callable;


public class WalkToRock extends Task {
    public WalkToRock(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        final Area rockArea = Gui.loc.getRockAreas();
        return !rockArea.contains(ctx.players.local().tile()) && ctx.backpack.isEmpty();
    }

    @Override
    public void execute() {
        Tile[] path = Gui.loc.getPath();
        ctx.movement.newTilePath(path).traverse();
        SMiner.status = "Walking to Mine";
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.movement.distance(ctx.players.local(), ctx.movement.destination()) < 14;
            }
        }, 250, 20);


    }
}
