 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitationalwedge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Edward
 * 
 */
public class Particle{

    public double pos_X,pos_Y,vel_X,vel_Y,accel,time;
    public double collisions;
  
    
    Boundary b;
    
    public Particle(Particle p){
        b = p.b;
        clone(p);
    }
    
    public Particle(double pX,double pY,double velX,double velY,double acc,Boundary b1) throws IOException
    {
        pos_X=pX;pos_Y=pY;vel_X=velX;vel_Y=velY;accel=acc;b=b1;time=0;
        collisions=0;
        
    }
    
    public boolean timeUpdate(double time1) throws IOException{
        
        if(!b.inBounds(this,time1)){return false;}
        
        pos_X=pos_X+vel_X*time1;
        pos_Y=pos_Y+(vel_Y*time1+.5*accel*time1*time1);
        vel_Y=vel_Y+accel*time1;
        time+=time1;
        
        return true;
        
    }
    
    
    public double energy(){
        return pos_Y*-accel+.5*(vel_X*vel_X+vel_Y*vel_Y);
    }
    
    public boolean bounceUpdate(double time) throws IOException{
        boolean b2=timeUpdate(time);
        if(!b2){return false;}
        double[] normals=b.normal(pos_X);
        
        double newX=vel_X-2*(vel_Y*normals[1]+vel_X*normals[0])*normals[0];
        double newY=vel_Y-2*(vel_Y*normals[1]+vel_X*normals[0])*normals[1];
        
        /*System.out.println(((newX*normals[0]+newY*normals[1])/
                        (Math.sqrt(normals[0]*normals[0]+normals[1]*normals[1])*
                        Math.sqrt(newX*newX+newY*newY)))+" "+
                        (vel_X*normals[0]+vel_Y*normals[1])/
                        (Math.sqrt(vel_X*vel_X+vel_Y*vel_Y)*
                        Math.sqrt(normals[0]*normals[0]+normals[1]*normals[1])));*/
        
        vel_X=newX;vel_Y=newY;collisions+=1;
        return true;
    }
    
    public String toString(){
        String s = "Position x: "+pos_X+"\n"+
                "Position y: "+pos_Y+"\n"+
                "Velocity x: "+vel_X+"\n"+
                "Velocity y: "+vel_Y+"\n"+
                "Energy: "+energy()+"\n";
        
        return s;
                
    }
        
    public void clone(Particle p){
        this.pos_X=p.pos_X;
        this.pos_Y=p.pos_Y;
        this.accel=p.accel;
        this.vel_X=p.vel_X;
        this.vel_Y=p.vel_Y;
        this.time=p.time;
                
    }
    
}
