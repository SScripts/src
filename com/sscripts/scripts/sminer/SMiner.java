package sminer;

import org.powerbot.script.*;
import org.powerbot.script.rt6.Skills;

import sminer.data.Master;
import sminer.gui.Gui;
import sminer.task.framework.Task;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

@Script.Manifest(description = "Mines at different Locations", name = "SMiner")

public class SMiner extends PollingScript<org.powerbot.script.rt6.ClientContext>  implements PaintListener, MouseListener, MessageListener {

    public List<Task> tasks = Collections.synchronizedList(new ArrayList<Task>());

    private int startLvl, startExp, mined, expGain;
    private long startTime;
    


    public static String status = "Waiting for input";

    public static Master loc;

    @Override
    public void start() {
        startLvl = ctx.skills.level(Skills.MINING);
        startExp = ctx.skills.experience(Skills.MINING);
        startTime = System.currentTimeMillis();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui(ctx, tasks);
            }
        });
    }


    @Override
    public void poll() {

        synchronized(tasks) {
            if (tasks.size() == 0) {
                try {
                    tasks.wait();
                } catch (InterruptedException ignored) {}
            }
        }

        for (Task task : tasks) {
            if (task.activate()) {
                task.execute();
            }
        }

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


    public String formatTime(final long time) {
        final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
    }


    private final Font font = new Font("Arial", 1, 16);
    private final Font mainfont = new Font("Arial", 1, 14);

    @Override
    public void repaint(Graphics g) {
        expGain =  ctx.skills.experience(Skills.MINING) - startExp;
        if (!hide) {
            final BufferedImage paint = downloadImage("http://i.imgur.com/YyPLAhA.png");
            g.drawImage(paint, 0, 300, null);
            Graphics2D d = (Graphics2D)g;
            d.setPaint(Color.WHITE);
            g.setFont(font);
            g.drawString("" + SMiner.status, 210, 577);
            g.setFont(mainfont);
            g.drawString("" + formatTime(getTotalRuntime()), 203, 416);
            g.drawString("" + (ctx.skills.level(Skills.MINING) - startLvl), 213, 509);
            g.drawString("" + ctx.skills.level(Skills.MINING), 235, 479);
            g.drawString("" + expGain +" ("+perHour(expGain)+")", 200, 447);
            g.drawString("" + mined, 223, 536);
            g.drawString("HIDE PAINT", 30, 100);
        }
        else {
            Graphics2D d = (Graphics2D)g;
            d.setPaint(Color.BLACK);
            g.setFont(mainfont);
            g.drawString("SHOW PAINT", 30,100);
        }
        g.setColor(Color.WHITE);
        g.drawLine(ctx.mouse.getLocation().x, ctx.mouse.getLocation().y - 5, ctx.mouse.getLocation().x, ctx.mouse.getLocation().y + 5);
        g.drawLine(ctx.mouse.getLocation().x - 5, ctx.mouse.getLocation().y, ctx.mouse.getLocation().x + 5, ctx.mouse.getLocation().y);
    }


}
