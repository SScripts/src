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


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Manifest(description = "Mines at different Locations", name = "SMiner", authors = "SScripts", version = 1.0)

public class SMiner extends PollingScript implements PaintListener, MouseListener, MessageListener {

    public ArrayList<Task> tasks = new ArrayList<Task>();

    private int startLvl, startExp, mined, expGain;
    private long startTime;
    public static boolean drop = false;

    public static String status = "Waiting for input";


    @Override
    public void start() {
        startLvl = ctx.skills.getLevel(Skills.MINING);
        startExp = ctx.skills.getExperience(Skills.MINING);
        startTime = System.currentTimeMillis();
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Gui(ctx, tasks);


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
            final BufferedImage paint = downloadImage("http://i.imgur.com/YyPLAhA.png");
            g.drawImage(paint, 0, 300, null);
            Graphics2D d = (Graphics2D)g;
            d.setPaint(Color.WHITE);
            g.drawString("" + SMiner.status, 210, 574);
            g.drawString("" + Timer.format(getRuntime()), 200, 415);
            g.drawString("" + (ctx.skills.getLevel(Skills.MINING) - startLvl), 213, 509);
            g.drawString("" + ctx.skills.getLevel(Skills.MINING), 235, 479);
            g.drawString("" + expGain +" ("+perHour(expGain)+")", 200, 447);
            g.drawString("" + mined, 223, 536);
            g.drawString("HIDE PAINT", 30, 100);
        }
        else {
            Graphics2D d = (Graphics2D)g;
            d.setPaint(Color.BLACK);
            g.drawString("SHOW PAINT", 30,100);


        }


        g.setColor(Color.WHITE);
        g.drawLine(ctx.mouse.getLocation().x, ctx.mouse.getLocation().y - 5, ctx.mouse.getLocation().x, ctx.mouse.getLocation().y + 5);
        g.drawLine(ctx.mouse.getLocation().x - 5, ctx.mouse.getLocation().y, ctx.mouse.getLocation().x + 5, ctx.mouse.getLocation().y);
    }
}
