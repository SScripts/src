package sminer.task.drop;

import org.powerbot.script.methods.Hud;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Item;
import sminer.SMiner;
import sminer.gui.Gui;
import sminer.task.framework.Task;

public class Drop extends Task {

    public Drop(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return SMiner.drop && ctx.backpack.select().count() == 28;
    }

    @Override
    public void execute() {
        final int item = Gui.loc.getOre();
        if (!ctx.hud.view(Hud.Window.BACKPACK)){
            SMiner.status = "Opening Backpack";
            ctx.hud.view(Hud.Window.BACKPACK);
        }

        for (Item i: ctx.backpack.select().id(item)){
            i.interact("Drop");
            SMiner.status = "Dropping";
            wait(347);

        }
    }

    public void wait(int ms) {
        try {
            Thread.sleep(Math.max(5, (int) (ms * Random.nextDouble(0.76, 1.45))));
        } catch (InterruptedException ignored) {
        }
    }


}
