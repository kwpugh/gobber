package com.kwpugh.gobber2.util;

import net.minecraftforge.fml.ModList;

public enum CuriosModCheck
{
    CURIOS("curios");

    private final boolean loaded;

    CuriosModCheck(String modid) 
    {
        this.loaded = ModList.get() != null && ModList.get().getModContainerById(modid).isPresent();
    }

    public boolean isLoaded() 
    {
        return this.loaded;
    }
}