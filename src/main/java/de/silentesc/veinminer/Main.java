package de.silentesc.veinminer;

import de.silentesc.veinminer.events.PlayerBlockBreakEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "veinminer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        PlayerBlockBreakEvents.BEFORE.register(PlayerBlockBreakEvent::onPlayerBlockBreakEventBefore);
    }
}
