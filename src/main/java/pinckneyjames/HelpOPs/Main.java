package pinckneyjames.HelpOPs;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
/*
 * JRP
 */
public final class Main extends JavaPlugin implements Listener{
	
	/**
	 * Enable the plugin and call any startup methods here
	 * (non-Javadoc) 
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("HelpOPs has been Activated!");
	}
	/**
	 * When the plugin is disabled, it will call this method
	 * (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 */
	@Override
	public void onDisable() {
		Bukkit.broadcastMessage("§1[§bHelpOPs§1]§9 Got disabled! is the server shutting down or did the plugin crash?");
	}
	/**
	 * When the player sends a command, trigger this action to be performed
	 * (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onCommand(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 * @param sender this is the sender of the command
	 * @param cmd the command to execute, input as a string
	 * @param label a label for the command
	 * @return we dont really need to return anything for this specific command, however we are overwriting the onCommand method,
	 * 	so we need to return something
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String args[]) {
		if (cmd.getName().equals("helpops")) {
			if(!(sender instanceof Player))
			{
				sender.sendMessage("You must be a player to run this command!");
				return false;
			}
			else {
				Player player = (Player) sender;
				int cnt = 0;
				player.sendMessage("§4Your message has been sent to an online Admin!");
				Player[] playerlist = this.getServer().getOnlinePlayers();
				for(Player p : playerlist)
				{
					if(p.isOp()) {
						String message = "";
						for(String s : args)
						{
							message += ("§2" + s + " ");
						}
						p.sendMessage("§4!----------------------------------------------------!§r" + player.getDisplayName() + " has sent a message to the Operators!\n The message is: " + message + "\n§4!----------------------------------------------------!§r");
					}
					else {
						cnt++;
						if(playerlist.length == cnt)
						{
							player.sendMessage("§4There are currently no Admins online right now! Try again later!");
						}
					}
				}
			}
		}
		return true;
	}
}
