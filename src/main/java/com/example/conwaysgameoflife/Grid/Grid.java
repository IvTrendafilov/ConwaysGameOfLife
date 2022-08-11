package com.example.conwaysgameoflife.Grid;


import java.util.Arrays;

public class Grid {
    //first we need to build the grid for the player
    private int width;


    private int height;

    private int[] content;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    //We are using a single array for the grid with length = width*height
    //One can imagine the cells in the array  like this height = 2  width = 2 arr = [0,0,0,0] == [0][0]
    //                                                                                           [0][0]
    public int[] getContent() {
        if (content == null) {
            this.content = new int[width * height];
        }
        return content;
    }

    public void setContent(int[] content) {
        this.content = content;
    }

    public Grid makeCopy() {
        Grid copy = new Grid();
        copy.setHeight(this.height);
        copy.setWidth(this.width);
        copy.setContent(Arrays.copyOf(getContent(), getContent().length));
        return copy;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "width=" + width +
                ", height=" + height +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
