package ru.academits.danilov_e.minesweeper;

public class Minesweeper {
    private boolean isOpened;
    private boolean hasMine;
    private int mineCount;

    public Minesweeper(){
        isOpened = false;
        hasMine = false;
        mineCount = 0;
    }

    public void setOpened(boolean value){
        isOpened = value;
    }

    public boolean getOpened(){
        return isOpened;
    }

    public void setHasMine(boolean value){
        hasMine = value;
    }

    public boolean getHasMine(){
        return hasMine;
    }

    public void setMineCount(int mineCount){
        this.mineCount = mineCount;
    }

    public int getMineCount(){
        return mineCount;
    }
}
