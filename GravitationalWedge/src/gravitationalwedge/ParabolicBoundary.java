/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gravitationalwedge;

/**
 *
 * @author Edward
 */
public class ParabolicBoundary implements Boundary{
    double curvature,accel;
    double width = 5,height = 8;
    public ParabolicBoundary(double c,double a){
        curvature = c;
        accel = a;
    }
    public double function(double x){
        return curvature*x*x;
    }
    public boolean inBounds(Particle p,double time){
        double pos_X2=p.pos_X+p.vel_X*time;
        double pos_Y2=p.pos_Y+p.vel_Y*time+.5*accel*time*time;
        
        return(pos_Y2-function(pos_X2)>-.0000001);
    }
    public double[] normal(double x){
        double slope = curvature*x*2;
        double[] ret = new double[2];
        
        ret[0]=slope/Math.sqrt((1+slope*slope));
        ret[1]=-1/Math.sqrt(slope*slope+1);
        
        return ret;
    }
    public double nextBounce(Particle p){
        double a,b,c;
        
        a=.5*accel - curvature*p.vel_X*p.vel_X;
        b=p.vel_Y-2*p.pos_X*p.vel_X*curvature;
        c=p.pos_Y-curvature*p.pos_X*p.pos_X;
        
        double t=quadraticSolve(a,b,c);
        //System.out.println(a+" "+b+" "+c);
        /*
        if(t>0&&inBounds(p,t))return t;
        
            a=.5*accel + amplitude*p.vel_X*p.vel_X;
            b=p.vel_Y-amplitude*2*p.vel_X*shift+amplitude*2*p.pos_X*p.vel_X;
            c=amplitude*p.pos_X*p.pos_X-2*amplitude*p.pos_X*shift+p.pos_Y;
        
        t=quadraticSolve(a,b,c);
                */
        //System.out.println(a+" "+b+" "+c);
        if(t>0&&inBounds(p,t))return t;
        
        return -1;
    }
    public double quadraticSolve(double a,double b,double c){
        double discriminant=b*b-4*a*c;
        if(discriminant<0)return -1;
        if(a==0)return -b/c<0?-1:-b/c;
        double sol1,sol2;
        
        sol1=(-b+Math.sqrt(discriminant))/(2*a);
        sol2=(-b-Math.sqrt(discriminant))/(2*a);
        
        //System.out.println(sol1+" "+sol2);
        
        double acc = 0.000000001;
        
        
        if(sol2<=acc && sol1<=acc)return -1;
        if(sol2<=acc && sol1>acc)return sol1;
        if(sol2>acc  && sol1<=acc )return sol2;
        if(sol2>acc  && sol1>acc )return sol2<sol1?sol2:sol1;
        return -1;
        
    }
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }
    public void setWidth(double d){
        width = d;
    }
    public void setHeight(double d){
        height = d;
    }
}
