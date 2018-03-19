package com.jetbrains;

import java.util.Arrays;
import java.util.Random;

public class Ship {

    public final Integer shipLen;
    public final Integer[][] position;
    public final Boolean isVertical;


    public Ship(final Integer row, final Integer col) {
        this.shipLen = this.initShipLen(col);
        this.isVertical = new Random().nextBoolean();
        this.position = this.initPosition(row, col);



    }

    private Integer initShipLen(Integer col){
        return Math.round(col/3);
    }

    private Integer[][] initPosition(final Integer containerRow, final Integer containerCol){
        final Integer[][] tempPosition = new Integer[this.shipLen][2];


            for(int i = 0; i < tempPosition.length; i++){
                if(i != 0){
                    if(this.isVertical) {
                        tempPosition[i][0] = tempPosition[0][0] + i;
                        tempPosition[i][1] = tempPosition[0][1];
                    }else{
                        tempPosition[i][0] = tempPosition[0][0];
                        tempPosition[i][1] = tempPosition[0][1]+i;
                    }
                }else{
                    //System.out.println(this.isVertical);
                    if(this.isVertical){
                        tempPosition[0][0] = new Random().nextInt(containerRow-this.shipLen);
                        tempPosition[0][1] = new Random().nextInt(containerCol);
                    }else{
                        tempPosition[0][0] = new Random().nextInt(containerRow);
                        tempPosition[0][1] = new Random().nextInt(containerCol-this.shipLen);
                    }


                }


            }
        //System.out.println(Arrays.deepToString(tempPosition));
        return tempPosition;
    }


}

