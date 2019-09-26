package com.dna.everythingisbad.init;

public enum Religion {
    NON_RELIGIOUS("Non Religous"),
    CHRISTIAN("Christian"),
    JEWISH("Jewish"),
    MUSLIM("Muslim"),
    HINDU("Hindu"),
    BUDDHIST("Buddhist");
    private String displayName;
    private Religion(String displayName){
        this.displayName = displayName;
    }
    public String getDisplayName(){
        return this.displayName;
    }

}
