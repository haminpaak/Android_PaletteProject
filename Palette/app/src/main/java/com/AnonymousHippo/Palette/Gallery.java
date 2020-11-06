package com.AnonymousHippo.Palette;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Gallery {
    public String CODE;
    public String title;
    public int NUMBER;
    public ArrayList<Integer> IMAGES;
    public ArrayList<String> TITLES;
    public ArrayList<String> CONTENT;

    public Gallery(String CODE){
        this.CODE = CODE;
    }

    public void setTitle(){

    }

    @NotNull
    @Override
    public String toString(){
        return this.CODE;
    }
}
