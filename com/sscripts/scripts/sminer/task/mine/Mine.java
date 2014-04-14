package sminer.task.mine;


import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import sminer.SMiner;
import sminer.task.framework.Task;

import java.util.concurrent.Callable;


public class Mine extends Task {

    public Mine(ClientContext ctx) {
        super(ctx);
    }


    private int[] rock;

    @Override
    public boolean activate() {
        rock = SMiner.loc.getRock();
        final Area rockArea = SMiner.loc.getRockAreas();
        return ctx.backpack.select().count() <28
                && ctx.players.local().animation() == -1
                && rockArea.contains(ctx.players.local().tile());

    }

    @Override
    public void execute() {
        rock = SMiner.loc.getRock();
        final Area rockArea = SMiner.loc.getRockAreas();
        final GameObject Rock = ctx.objects.select().id(rock).nearest().first().poll();
        if (Rock.inViewport()
                && ctx.players.local().animation() == -1
                && rockArea.contains(Rock)
                && !ctx.players.local().inMotion()){
            if (Rock.interact("Mine")){
            	SMiner.status = "Mining";
            	Condition.wait(new Callable<Boolean>() {
            		public Boolean call() throws Exception {
            			return ctx.players.local().animation() == -1;
            		}
            	}, 1000, 2);
           	}
        }else {
            ctx.camera.turnTo(Rock);
            SMiner.status = "Looking for Rock";
            Condition.wait(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return Rock.inViewport();
                }
            }, 500, 2);
        }
    }
}
