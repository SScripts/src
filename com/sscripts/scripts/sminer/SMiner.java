package sminer;

import sminer.gui.Gui;
import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import sminer.task.framework.Task;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

@Manifest(description = "Mines at different Locations", name = "SMiner", authors = "SScripts")

public class SMiner extends PollingScript implements PaintListener, MouseListener, MessageListener {

    public static ArrayList<Task> tasks = new ArrayList<>();

    private int startLvl, startExp, mined, expGain;
    private long startTime;

    public static String status = "Waiting for input";


    private Timer runTime = new Timer(0);



    @Override
    public void start() {
        tasks.clear();
        startLvl = ctx.skills.getLevel(Skills.MINING);
        startExp = ctx.skills.getExperience(Skills.MINING);
        startTime = System.currentTimeMillis();
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Gui(ctx);
            }

        });
    }

    @Override
    public int poll() {

        for (Task task : tasks) {
            if (task.activate()) {
                task.execute();
                return Random.nextInt(200, 450);
            }
        }
        return 150;
    }

    @Override
    public void messaged(MessageEvent msg) {
        if (msg.getMessage().startsWith("You manage to ")){
            mined++;
        }
    }
    boolean hide = false;
    Point p;
    Rectangle close = new Rectangle(10, 70, 170, 170);
    Rectangle open = new Rectangle(10, 70, 170, 170);

    @Override
    public void mouseClicked(MouseEvent e) {
        p = e.getPoint();
        if (close.contains(p) && !hide) {
            hide = true;
        } else if (open.contains(p) && hide) {
            hide = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int perHour(int value) {
        return (int) ((value) * 3600000D / (System.currentTimeMillis() - startTime));
    }

    @Override
    public void repaint(Graphics g) {
        expGain =  ctx.skills.getExperience(Skills.MINING) - startExp;
        if (!hide) {
        Graphics2D d = (Graphics2D)g;
        g.clearRect(10, 65, 220, 170);
        g.drawRect(10, 65, 220, 170);
        d.setPaint(Color.WHITE);
        g.drawString("SMiner - by SScripts", 24, 85);
        g.drawString("Status: " + SMiner.status, 24, 105);
        g.drawString("Time Run:" + runTime.toElapsedString(), 24, 125);
        g.drawString("Lvls made:" + (ctx.skills.getLevel(Skills.MINING) - startLvl), 24, 145);
        g.drawString("Current Lvl:" + ctx.skills.getLevel(Skills.MINING), 24, 165);
        g.drawString("Mining EXP made(ph):" + expGain +" ("+perHour(expGain)+")", 24, 185);
        g.drawString("Ores Mined:" + mined, 24, 205);
        g.drawString(">>>Hide Paint<<<", 24, 225);
        }
        else {
            Graphics2D d = (Graphics2D)g;
            g.drawRect(10, 65, 220, 170);
            d.setPaint(Color.WHITE);

        }


        g.setColor(Color.WHITE);
        g.drawLine(ctx.mouse.getLocation().x, ctx.mouse.getLocation().y - 5, ctx.mouse.getLocation().x, ctx.mouse.getLocation().y + 5);
        g.drawLine(ctx.mouse.getLocation().x - 5, ctx.mouse.getLocation().y, ctx.mouse.getLocation().x + 5, ctx.mouse.getLocation().y);
    }
}




