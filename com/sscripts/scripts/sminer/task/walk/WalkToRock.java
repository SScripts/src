package sminer.task.walk;

import sminer.gui.Gui;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.Tile;
import sminer.SMiner;
import sminer.task.framework.Task;

import java.util.concurrent.Callable;


public class WalkToRock extends Task {
    public WalkToRock(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        final Area rockArea = Gui.loc.getRockAreas();
        return !rockArea.contains(ctx.players.local().getLocation());
    }

    @Override
    public void execute() {
        Tile[] path = Gui.loc.getPath();
        ctx.movement.newTilePath(path).traverse();
        SMiner.status = "Walking to Mine";
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.movement.getDistance(ctx.players.local(),ctx.movement.getDestination()) < 14;
            }
        },250,20);


    }
}
