package sminer.task.drop;


import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Hud;
import org.powerbot.script.rt6.Item;
import sminer.SMiner;
import sminer.task.framework.Task;

public class Drop extends Task {

    public Drop(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28;
    }

    @Override
    public void execute() {
        final int item = SMiner.loc.getOre();
        if (!ctx.hud.opened(Hud.Window.BACKPACK)){
            SMiner.status = "Opening Backpack";
            ctx.hud.open(Hud.Window.BACKPACK);
        }

        Item i = ctx.backpack.select().id(item).poll();
        i.interact("Drop");
        SMiner.status = "Dropping";
        wait(347);

        
    }

    public void wait(int ms) {
        try {
            Thread.sleep(Math.max(5, (int) (ms * Random.nextDouble(0.76, 1.45))));
        } catch (InterruptedException ignored) {
        }
    }


}
