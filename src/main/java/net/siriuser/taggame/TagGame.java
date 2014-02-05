/**
 * Project : SR-TagGame
 * Package : net.siriuser.taggame
 * Created : 2014/02/06 - 4:48:16
 */
package net.siriuser.taggame;

import net.syamn.utils.LogUtil;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author SiriuseR
 *
 */
public class TagGame extends JavaPlugin {
    private static TagGame instance;

    @Override
    public void onEnable() {
        LogUtil.init(this);

        PluginManager pm = getServer().getPluginManager();

        PluginDescriptionFile pdfFile = this.getDescription();
        LogUtil.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);

        PluginDescriptionFile pdfFile = this.getDescription();
        LogUtil.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled!");
    }

    /**
     * @return Instance
     */
    public static TagGame getInstance() {
        return instance;
    }
}
