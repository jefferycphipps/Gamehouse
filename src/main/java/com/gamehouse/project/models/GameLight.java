package com.gamehouse.project.models;




public class GameLight {
    private String name;
    private String boxArtUrl;
    private long IGDBCode;

    public GameLight(){}

    public GameLight(String boxArtUrl, long IGDBCode, String name) {
        this.boxArtUrl = boxArtUrl;
        this.IGDBCode = IGDBCode;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoxArtUrl() {
        return boxArtUrl;
    }

    public void setBoxArtUrl(String boxArtUrl) {
        this.boxArtUrl = boxArtUrl;
    }

    public long getIGDBCode() {
        return IGDBCode;
    }

    public void setIGDBCode(long IGDBCode) {
        this.IGDBCode = IGDBCode;
    }
}
