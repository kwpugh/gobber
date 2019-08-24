package com.kwpugh.gobber2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kwpugh.gobber2.util.Gobber2_Group;
import com.kwpugh.gobber2.util.GobberConfig;
import com.kwpugh.gobber2.util.SpecialAbilities;
import com.kwpugh.gobber2.world.OreGenerator;

import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;


@Mod(Gobber2.modid)
public class Gobber2 
{
	public static Gobber2 instance;
		
	public static final String modid = "gobber2";
	public static final Logger logger = LogManager.getLogger(modid);	
	public static final ItemGroup gobber2 = new Gobber2_Group();
    
	public Gobber2() 
	{
		instance = this;
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, GobberConfig.SERVER_CONFIG);
		GobberConfig.loadConfig(GobberConfig.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("gobber-general.toml"));
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::modSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
		 
		MinecraftForge.EVENT_BUS.register(this);
	}
    
	private void modSetup(final FMLCommonSetupEvent event)
	{
		OreGenerator.setupOregen();
		OreGenerator.setupNetherOregen();
		OreGenerator.setupEndOregen();
		
		MinecraftForge.EVENT_BUS.register(new SpecialAbilities());
		
		logger.info("Mod setup completed");
	}
	
	private void clientSetup(final FMLClientSetupEvent event)
	{
		logger.info("Mod client setup completed");
	}
	
	private void serverSetup(final FMLDedicatedServerSetupEvent event)
	{
		logger.info("Mod server setup completed");
	}
}




