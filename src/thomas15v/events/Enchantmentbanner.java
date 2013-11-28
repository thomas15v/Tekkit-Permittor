package thomas15v.events;

import java.awt.Color;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import thomas15v.TekkitPermittor;
import thomas15v.configuration.Manager;
import thomas15v.info.ModEnchantmentInfo;

public class Enchantmentbanner implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST)
	void PlayerInteractEvent (PlayerInteractEvent event){
			Player player = event.getPlayer();
			for (ModEnchantmentInfo enchant : Manager.GetBannedEnchantments()){
				
				for (Enchantment e : player.getItemInHand().getEnchantments().keySet()){
					TekkitPermittor.GetLogger().info(e.getName());		
				}
				
				if (player.getItemInHand().getEnchantments().containsKey(Enchantment.getById(enchant.GetTypeId()))){
					if (enchant.Limitonlevel()){
						int level = player.getItemInHand().getEnchantments().get(Enchantment.getById(enchant.GetTypeId()));
						if (level > enchant.GetMaxLevel()){
							player.getItemInHand().removeEnchantment(Enchantment.getById(enchant.GetTypeId()));
							player.getItemInHand().addEnchantment(Enchantment.getById(enchant.GetTypeId()), enchant.GetMaxLevel());
							player.sendMessage(Color.red + "Lowered an limited enchantment on your tool to level " + enchant.GetMaxLevel());
						}
					} 
					else{
						player.getItemInHand().removeEnchantment(Enchantment.getById(enchant.GetTypeId()));
						player.sendMessage(Color.red + "We have removed an harmfull enchantment from your tool");
					}	
				}
			}
		}	
	}
