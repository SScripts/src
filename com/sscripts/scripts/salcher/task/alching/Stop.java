package com.sscripts.scripts.salcher.task.alching;

import org.powerbot.script.rt6.ClientContext;

import com.sscripts.scripts.salcher.SAlcher;
import com.sscripts.scripts.salcher.task.Task;

public class Stop extends Task {

	public Stop(ClientContext ctx) {
		super(ctx);
	}
	

	@Override
	public boolean activate() {
		return ctx.backpack.select().id(SAlcher.alchID).count() == 0
				|| ctx.backpack.select().id(561).count() == 0;
	}

	@Override
	public void execute() {
		ctx.controller().stop();
		
	}

}
