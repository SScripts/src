package com.sscripts.scripts.salcher;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.Skills;

import com.sscripts.scripts.salcher.gui.Gui;
import com.sscripts.scripts.salcher.task.Task;

@Script.Manifest(description = "Alchs anything, everywhere", name = "SAlcher", properties = "topic=1178419; client=6")

public class SAlcher extends PollingScript<org.powerbot.script.rt6.ClientContext> implements PaintListener, MessageListener {

	
	public List<Task> tasks = new ArrayList<Task>();
	private int casts, startExp, startLvl, expGain;
	private long startTime;
	
	public static int alchID;
	
	   @Override
	    public void start() {
           startTime = System.currentTimeMillis();
   			startLvl = ctx.skills.level(Skills.MAGIC);
   			startExp = ctx.skills.experience(Skills.MAGIC);
		   if (ctx.game.loggedIn()) {
			   EventQueue.invokeLater(new Runnable() {
				   @Override
				   public void run() {
					   new Gui(ctx, tasks);
				   }
			   });
		   }
	   }
	

	@Override
	public void poll() {
		for (Task task : tasks) {
			if (task.activate()) {
				task.execute();
			}
		}
		
	}
	
    @Override
    public void messaged(MessageEvent msg) {
        if (msg.getMessage().contains("coins have been")){
            casts++;
        }
    }
    
    public int perHour(int value) {
        return (int) ((value) * 3600000D / (System.currentTimeMillis() - startTime));
    }
	
    public String formatTime(final long time) {
        final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
    }
    
    private final Font font = new Font("Arial", 1, 24);
    private final Font minifont = new Font("Arial", 1, 8);
    private final Font mainfont = new Font("Arial", 1, 12);
    @Override
    public void repaint(Graphics g) {
    	g.setColor(Color.DARK_GRAY);
    	g.fillRect(2, 80, 170, 125);
    	g.setFont(font);
    	g.setColor(Color.RED);
    	g.drawString("S", 10, 100);
    	g.setColor(Color.BLUE);
    	g.drawString("Alcher", 25, 100);
    	g.setFont(minifont);
    	g.setColor(Color.WHITE);
    	g.drawString("V1.0 - BETA release", 100, 100);
        expGain =  ctx.skills.experience(Skills.MAGIC) - startExp;
        g.setFont(mainfont);
        g.setColor(Color.WHITE);
        g.drawString("Time run: " + formatTime(getTotalRuntime()), 10, 125);
    	g.drawString("Alchs done: " + casts ,10, 140);
    	g.drawString("Alchs per hour: " + perHour(casts), 10, 155);
        g.drawString("EXP Gain: " + (expGain), 10, 170);
        g.drawString("EXP per hour: " + perHour(expGain), 10, 185);
        g.drawString("LVL Gain: " + (ctx.skills.level(Skills.MAGIC) - startLvl), 10, 200);
    	g.setColor(Color.WHITE);
    	g.drawLine(ctx.mouse.getLocation().x, ctx.mouse.getLocation().y - 5, ctx.mouse.getLocation().x, ctx.mouse.getLocation().y + 5);
    	g.drawLine(ctx.mouse.getLocation().x - 5, ctx.mouse.getLocation().y, ctx.mouse.getLocation().x + 5, ctx.mouse.getLocation().y);
    }

	
}
