package com.kwpugh.gobber2.util.handlers;
//
//import static net.minecraftforge.api.distmarker.Dist.CLIENT;
//import static org.lwjgl.glfw.GLFW.GLFW_KEY_APOSTROPHE;
//
//import com.kwpugh.gobber2.Gobber2;
//
//import net.minecraft.client.settings.KeyBinding;
//import net.minecraftforge.event.TickEvent;
//import net.minecraftforge.event.TickEvent.ClientTickEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.client.registry.ClientRegistry;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber(modid = Gobber2.modid, value = CLIENT)
//public final class ClientEventSubscriber
//{
//	private static final String CATEGORY = "key.categories." + Gobber2.modid;
//
//	public static final KeyBinding toggleRingEnderchest = new KeyBinding(Gobber2.modid + ".key.toggleRingEnderchest", GLFW_KEY_APOSTROPHE, CATEGORY);
//
//	public static boolean playerHasKeyPressed;
//
//	static
//	{
//		ClientRegistry.registerKeyBinding(toggleRingEnderchest);
//	}
//	
//	@SubscribeEvent
//	public static void onClientTickEvent(final ClientTickEvent event) 
//	{
//		if (event.phase != TickEvent.Phase.END)
//			return;
//		{		
//			if (toggleRingEnderchest.isPressed())
//			{
//				playerHasKeyPressed = true;
//			}
//			else
//			{
//				playerHasKeyPressed = false;
//			}
//		}
//	}
//}