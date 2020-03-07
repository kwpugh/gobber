package com.kwpugh.gobber2.util;

import static net.minecraftforge.api.distmarker.Dist.CLIENT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_O;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

/**
 * Handles all the events that are fired on the FORGE event bus that should be handled
 * on the Client distribution.
 *
 * @author Cadiboo
 */
//@Mod.EventBusSubscriber(modid = Gobber2.modid, value = CLIENT)
//public final class ClientEventSubscriber
//{
//
//	private static final String CATEGORY = "key.categories." + Gobber2.modid;
//
//	public static final KeyBinding toggleRingAttraction = new KeyBinding(Gobber2.modid + ".key.toggleRingAttraction", GLFW_KEY_O, CATEGORY);
//
//
//	static
//	{
//		ClientRegistry.registerKeyBinding(toggleRingAttraction);
//	}
//
//	@SubscribeEvent
//	public static void onClientTickEvent(final ClientTickEvent event) 
//	{
//
//		if (event.phase != TickEvent.Phase.END)
//			return;
//		{
//			
//			if (toggleRingAttraction.isPressed())
//			{
//				System.out.println("key pressed");
//			}
//		}
//	}
//}