package thomas15v.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import thomas15v.configuration.Manager;

public class WarnAdminevents implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	void playerjoin(PlayerJoinEvent event){
		if (!Manager.ConfigFileExist() && (event.getPlayer().hasPermission("tekkitpermittor.command") || event.getPlayer().isOp())){
			event.getPlayer().sendMessage(ChatColor.RED + "Tekkit Permittor plugin isn't configurated");
			event.getPlayer().sendMessage(ChatColor.RED + "Please chose a Default config with");
			event.getPlayer().sendMessage(ChatColor.RED + "/tep choicedefault B|TM|TL");
			event.getPlayer().sendMessage(ChatColor.RED + "B For bukkit");
			event.getPlayer().sendMessage(ChatColor.RED + "TM for Tekkit Main");
			event.getPlayer().sendMessage(ChatColor.RED + "TL for Tekkit Litle");
		}
	}
}
