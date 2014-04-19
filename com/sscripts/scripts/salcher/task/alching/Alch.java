package com.sscripts.scripts.salcher.task.alching;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

import com.sscripts.scripts.salcher.SAlcher;
import com.sscripts.scripts.salcher.task.Task;

public class Alch extends Task {

	public Alch(ClientContext ctx) {
		super(ctx);
	}

	
	@Override
	public boolean activate() {
		
		return ctx.players.local().animation() == -1;
	}

	@Override
	public void execute() {
		final Item i = ctx.backpack.select().id(SAlcher.alchID).first().poll();
		if (ctx.keyboard.send("1")){
			if (i.interact("Cast")) {
				 Condition.wait(new Callable<Boolean>() {
		                public Boolean call() throws Exception {
		                    return ctx.players.local().animation() == -1;
		                }
		            }, 500, 2);
			}
			
		}
		
	}

}
