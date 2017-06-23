package com.gamingmesh.jobs.commands.list;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.commands.Cmd;
import com.gamingmesh.jobs.commands.JobCommand;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobProgression;
import com.gamingmesh.jobs.container.JobsPlayer;
import com.gamingmesh.jobs.stuff.ChatColor;

public class exp implements Cmd {

    private enum Action {
	Set, Add, Take
    }

    @Override
    @JobCommand(1600)
    public boolean perform(Jobs plugin, CommandSender sender, String[] args) {
	if (args.length < 4) {
	    Jobs.getCommandManager().sendUsage(sender, "exp");
	    return true;
	}

	Action action = Action.Add;
	int amount = 0;
	String playerName = null;
	Job job = null;

	for (String one : args) {
	    switch (one.toLowerCase()) {
	    case "add":
		action = Action.Add;
		continue;
	    case "set":
		action = Action.Set;
		continue;
	    case "take":
		action = Action.Take;
		continue;
	    }

	    try {
		amount = Integer.parseInt(one);
		continue;
	    } catch (NumberFormatException e) {
	    }

	    if (job == null && Jobs.getJob(one) != null) {
		job = Jobs.getJob(one);
		continue;
	    }
	    playerName = one;
	}

	if (playerName == null)
	    return false;

	JobsPlayer jPlayer = Jobs.getPlayerManager().getJobsPlayer(playerName);

	if (jPlayer == null) {
	    sender.sendMessage(Jobs.getLanguage().getMessage("general.error.noinfoByPlayer", "%playername%", args[0]));
	    return true;
	}

	if (job == null) {
	    sender.sendMessage(ChatColor.RED + Jobs.getLanguage().getMessage("general.error.job"));
	    return true;
	}

	try {
	    // check if player already has the job
	    if (jPlayer.isInJob(job)) {

		JobProgression prog = jPlayer.getJobProgression(job);
		switch (action) {
		case Add:
		    prog.addExperience(amount);
		    break;
		case Set:
		    prog.setExperience(amount);
		    break;
		case Take:
		    prog.takeExperience(amount);
		    break;
		}

		Player player = jPlayer.getPlayer();
		if (player != null) {
		    player.sendMessage(Jobs.getLanguage().getMessage("command.exp.output.target", "%jobname%", job.getChatColor() + job.getName(), "%level%", prog.getLevel(), "%exp%", prog.getExperience()));
		}

		sender.sendMessage(Jobs.getLanguage().getMessage("general.admin.success"));
	    }
	} catch (Exception e) {
	    sender.sendMessage(ChatColor.RED + Jobs.getLanguage().getMessage("general.admin.error"));
	}
	return true;
    }
}
