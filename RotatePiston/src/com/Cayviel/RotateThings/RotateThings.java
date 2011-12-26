package com.Cayviel.RotateThings;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;


public class RotateThings extends JavaPlugin {

	private static InteractListener il = new InteractListener();
	public static PermissionHandler permissionHandler;
	public static Logger log = Logger.getLogger("Minecraft");

//	private YamlConfiguration config;
	static public Boolean opOnlyBoolean;
	static public Boolean useWand;
	static public String WandName;
	static public Boolean usePerm = false;
	static public Boolean EnPistons;
	static public Boolean EnPumpkins;
	static public Boolean EnStairs;
	static public Boolean EnFurn;
	static public Boolean EnDisp;
	static public Boolean EnChest;
	static public Boolean EnLever;
	
//	static File confile;
	
	@Override
	public void onDisable() {
		getServer().getLogger().info("RotateThings Disabled!");
	}
	
		@Override
	public void onEnable() {
		// grab an instance of plugin manager
		PluginManager pm = getServer().getPluginManager();
		// register player interaction
		pm.registerEvent(Event.Type.PLAYER_INTERACT, il, Priority.Normal, this);

		//useWand = this.getConfig().getBoolean("Wand.Use a Wand", false);
		useWand = boolConfig("Wand.Use a Wand",true);
		WandName = stringConfig("Wand.Wand", "AIR");
		opOnlyBoolean = boolConfig("Op only (instead of Permissions)", true);
		EnPistons = boolConfig("Rotate.Pistons", true);
		EnPumpkins = boolConfig("Rotate.Pumpkins", false);
		EnStairs = boolConfig("Rotate.Stairs", false);
		EnFurn = boolConfig("Rotate.Furnaces", false);
		EnDisp = boolConfig("Rotate.Dispensers", false);
		EnChest = boolConfig("Rotate.Chest", false);
		EnLever = boolConfig("Rotate.Levers", false);

		this.saveConfig();
		
		// set up our permissions
		if (!opOnlyBoolean && (getServer().getPluginManager().getPlugin("Permissions")!=null)){
			setupPermissions();
			usePerm = true;
		}
		// we have successfully enabled the plugin
		log.info("[RotateThigns]: Enabled!");
	}

	private void setupPermissions() {
		// if our permissions handler isn't null we shall return
		if (permissionHandler != null)
			return;

		// grab an instance of the permissions plugin
		Plugin permissionsPlugin = getServer().getPluginManager().getPlugin("Permissions");

		permissionHandler = ((Permissions) permissionsPlugin).getHandler();
		log.info("Found and will use plugin " + ((Permissions) permissionsPlugin).getDescription().getFullName());
	}
	
	private boolean boolConfig(String string, boolean x){
		if (this.getConfig().isSet(string)){
			x = this.getConfig().getBoolean(string);
			}else{
			this.getConfig().set(string, x);
		}
		return x;
	}
	
	private String stringConfig(String string, String x){
		
		if (this.getConfig().isSet(string)){
			x = this.getConfig().getString(string);
			}else{
			this.getConfig().set(string, x);
		}
		return x;
	}
	
}

 