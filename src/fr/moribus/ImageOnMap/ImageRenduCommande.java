package fr.moribus.ImageOnMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ImageRenduCommande implements CommandExecutor
{

	Player joueur;
	boolean renderName, imgSvg;
	ImageOnMap plugin;
	boolean resize;
	
	public ImageRenduCommande(ImageOnMap p)
	{
		plugin = p;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) 
	{
		// On vérifie si celui qui exécute la commande est bien un joueur
		if (!ImgUtility.VerifierIdentite(sender))
			return false;
		
		joueur = (Player) sender;
		resize = false;
		
		if(joueur.hasPermission("imageonmap.userender"))
		{
			
		}
		else
		{
			joueur.sendMessage("You are not allowed to use this command ( " + arg1.getName() + " )!");
			return false;
		}
		
		if (arg3.length < 1)
		{
			joueur.sendMessage(ChatColor.RED + "You must enter image url");
		    return false;
		}
		
		/*if(arg3.length == 1 && arg3[0].equalsIgnoreCase("test"))
		{
			MapView map = Bukkit.createMap(joueur.getWorld());
			TacheHorloge tache = new TacheHorloge(joueur, map);
			tache.runTaskTimer(plugin, 0, 150);
			return true;
		}*/
		
		if(arg3.length >= 2 && arg3[1].equalsIgnoreCase("resize"))
		{
			resize = true;
		}
		
		
		TacheTraitementMap tache = new TacheTraitementMap(joueur, arg3[0], plugin, resize);
		tache.runTaskTimer(plugin, 0, 10);
		
		return true;
	}

}