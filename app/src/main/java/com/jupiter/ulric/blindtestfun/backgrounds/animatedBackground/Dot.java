package com.jupiter.ulric.blindtestfun.backgrounds.animatedBackground;

/**
 * Created by ulric on 29/07/2018.
 */

class dot{
    private int x,y,radius,color;

    dot(int x, int y, int radius, int color){
        this.x=x;
        this.y=y;
        this.radius=radius;
        this.color=color;
    }

    public int get_x(){
        return this.x;
    }

    public int get_y(){
        return this.y;
    }

    public int get_radius(){
        return this.radius;
    }

    public int get_color(){
        return this.color;
    }
}
