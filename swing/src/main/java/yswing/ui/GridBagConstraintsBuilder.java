package yswing.ui;

import java.awt.*;

public class GridBagConstraintsBuilder {
    private GridBagConstraints constraints;

    private GridBagConstraintsBuilder(){
        constraints = new GridBagConstraints();
    }

    public GridBagConstraintsBuilder gridX(int x){
        this.constraints.gridx = x;

        return this;
    }

    public GridBagConstraintsBuilder gridY(int y){
        this.constraints.gridy = y;
        return this;
    }

    public GridBagConstraintsBuilder gridWidth(int gridWidth){
        this.constraints.gridwidth = gridWidth;
        return this;
    }

    public GridBagConstraintsBuilder gridHeight(int gridHeight){
        this.constraints.gridheight = gridHeight;
        return this;
    }


    public GridBagConstraintsBuilder weightX(int weightX){
        this.constraints.weightx = weightX;
        return this;
    }


    public GridBagConstraintsBuilder weightY(int weightY){
        this.constraints.weighty = weightY;
        return this;
    }

    public GridBagConstraintsBuilder fill(int fill){
        this.constraints.fill = fill;
        return this;
    }



    public GridBagConstraints build(){
        return this.constraints;
    }


    public static GridBagConstraintsBuilder builder(){
        return new GridBagConstraintsBuilder();
    }
}
