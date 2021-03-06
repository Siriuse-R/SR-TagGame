/**
 * Project : SR-TagGame
 * Package : net.siriuser.taggame.listeners
 * Created : 2014/02/06 - 4:51:32
 */
package net.siriuser.taggame.listeners;

import net.siriuser.taggame.TagGame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 * @author SiriuseR
 *
 */
public class AutoSneakListener implements Listener {
    private TagGame plugin;
    public AutoSneakListener (final TagGame plugin) {
        this.plugin = plugin;

        /* プラグイン起動時に自動的にスニークに設定する */
        setupSneak(true);
    }

    @EventHandler
    public void JoinSneak(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.setSneaking(true);
    }

    @EventHandler
    public void RespawnSneak(final PlayerRespawnEvent event) {
        final Player player = event.getPlayer();
        player.setSneaking(true);
    }

    @EventHandler
    public void ToggleSneak(final PlayerToggleSneakEvent event) {
        final Player player = event.getPlayer();
        //TODO: スニークのタスク化
        player.setSneaking(true);
        event.setCancelled(true);
    }

    public void setupSneak(final boolean sneak) {
        plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    player.setSneaking(sneak);
                }
            }
        });
    }
}
