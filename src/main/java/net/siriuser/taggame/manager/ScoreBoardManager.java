/**
 * Project : SR-TagGame
 * Package : net.siriuser.taggame.manager
 * Created : 2014/02/06 - 5:51:40
 */
package net.siriuser.taggame.manager;

import net.siriuser.taggame.TagGame;
import net.syamn.utils.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

/**
 * @author SiriuseR
 *
 */
public class ScoreBoardManager {
    private TagGame plugin;

    private static Scoreboard board;
    private static Objective objective;

    private static Team demon;
    private static Team escaper;

    public ScoreBoardManager(final TagGame plugin) {
        this.plugin = plugin;

        ScoreboardManager sbm = Bukkit.getScoreboardManager();
        board = sbm.getNewScoreboard();

        demon = board.registerNewTeam("Demon");
        demon.setDisplayName(Util.coloring("&c鬼"));
        demon.setAllowFriendlyFire(false);

        escaper = board.registerNewTeam("Escaper");
        escaper.setDisplayName(Util.coloring("&b逃走者"));
        escaper.setAllowFriendlyFire(false);

        objective = board.registerNewObjective("TagGame", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("情報");
    }

    private void Refresh() {
        Score score = objective.getScore(Bukkit.getOfflinePlayer(demon.getDisplayName()));
        score.setScore(demon.getSize());
        Score score2 = objective.getScore(Bukkit.getOfflinePlayer(escaper.getDisplayName()));
        score2.setScore(escaper.getSize());

        plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    player.setScoreboard(board);
                }
            }
        });
    }

    public void addDemon(final Player player) {
        demon.addPlayer(player);
        Refresh();
    }

    public void removeDemon(final Player player) {
        demon.removePlayer(player);
        Refresh();
    }

    public void addEscaper(final Player player) {
        escaper.addPlayer(player);
        Refresh();
    }

    public void removeEscaper(final Player player) {
        escaper.removePlayer(player);
        Refresh();
    }
}
