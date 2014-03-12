package sminer.task.mine;


import sminer.gui.Gui;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.GameObject;
import sminer.SMiner;
import sminer.task.framework.Task;

import java.util.concurrent.Callable;


public class Mine extends Task {

    public Mine(MethodContext ctx) {
        super(ctx);
    }

    private int[] rock;

    @Override
    public boolean activate() {
        rock = Gui.loc.getRock();
        final Area rockArea = Gui.loc.getRockAreas();
        return ctx.backpack.select().count() <28
                && ctx.players.local().getAnimation() == -1
                && rockArea.contains(ctx.players.local().getLocation());

    }

    @Override
    public void execute() {
        rock = Gui.loc.getRock();
        final Area rockArea = Gui.loc.getRockAreas();
        final GameObject Rock = ctx.objects.select().id(rock).nearest().first().poll();
        if (Rock.isInViewport()
                && ctx.players.local().getAnimation() == -1
                && rockArea.contains(Rock)
                && !ctx.players.local().isInMotion()){
            Rock.interact("Mine");
            SMiner.status = "Mining";
            Condition.wait(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return ctx.players.local().getAnimation() == -1;
                }
            }, 1000, 2);
        }else {
            ctx.camera.turnTo(Rock.getLocation());
            SMiner.status = "Looking for Rock";
            Condition.wait(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return Rock.isInViewport();
                }
            }, 500, 2);
        }
    }
}
