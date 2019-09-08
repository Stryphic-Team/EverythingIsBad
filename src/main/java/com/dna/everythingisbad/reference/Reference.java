package com.dna.everythingisbad.reference;

public class Reference {
    public static final String MOD_ID = "everythingbad";
    public static final String MOD_NAME = "Everything Is Bad";
    public static final String VERSION = "1.12.2-0.01";

    public static final String CLIENT_PROXY_CLASS = "com.dna.everythingisbad.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.dna.everythingisbad.proxy.CommonProxy";
    public static final String SERVER_PROXY_CLASS = "com.dna.everythingisbad.proxy.ServerProxy";

    public static final String PREFIX = MOD_ID + ".";
    public static final String RESOURCE_PREFIX = MOD_ID + ":";

    private static int guiId = 0;
    public static final int GUI_STUPID_CORE_MACHINE = guiId++;
    public static final int GUI_DIARIC_GENERATOR = guiId++;
    public static final int GUI_DRYER_MACHINE = guiId++;
}
