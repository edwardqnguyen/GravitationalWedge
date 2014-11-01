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
public class WedgeBoundary implements Boundary {
    double slope, accel;
    double height, width;
    
    public WedgeBoundary(double s, double a){
        slope = s;
        accel = a;
    }
    public WedgeBoundary(double s, double a, double h, double w){
        this(s,a);
        height = h; width = w;
    }
    
    public double nextBounce(Particle p){
        double a=.5*accel;
        double b,c,time;
        
        
            b=p.vel_Y + slope*p.vel_X;
            c=p.pos_Y + slope*p.pos_X;
            
            if(quadraticSolve(a,b,c)==-1||!inBounds(p,quadraticSolve(a,b,c))){
                b=p.vel_Y - slope*p.vel_X;
                c=p.pos_Y - slope*p.pos_X;
            }
            if(quadraticSolve(a,b,c)==-1||!inBounds(p,quadraticSolve(a,b,c)))return 0;
            
            time=quadraticSolve(a,b,c);
        
        
        
        //System.out.println(time);
        return time;
    }
    
    public double getHeight(){
        return height;
    }
    
    public double getWidth(){
        return width;
    }
    
    public void setHeight(double d){
        height = d;
    }
    public void setWidth(double d){
        width = d;
    }
    
    public double quadraticSolve(double a,double b,double c){
        double discriminant=b*b-4*a*c;
        if(discriminant<0)return -1;
        
        double sol1,sol2;
        
        sol1=(-b+Math.sqrt(discriminant))/(2*a);
        sol2=(-b-Math.sqrt(discriminant))/(2*a);
        
        double acc = 0.00001;
        //System.out.println(sol1+" "+sol2);
        if(sol2<=acc && sol1<=acc){//System.out.println(sol1+" "+sol2);
            return -1;}
        if(sol2<=acc && sol1>acc )return sol1;
        if(sol2>acc  && sol1<=acc)return sol2;
        if(sol2>acc && sol1>acc)return sol2<sol1?sol2:sol1;
        
        return -1;
        
    }
    
    public double[] normal(double x){
        double s = 0;
        s = x>0?slope:-slope;
        
        if(x==0)s=0;
        
        double[] ret = new double[2];
        
        ret[0]=s/Math.sqrt((1+s*s));
        ret[1]=-1/Math.sqrt(s*s+1);
        return ret;
    }
    public boolean inBounds(Particle p,double time){
        double pos_X2=p.pos_X+p.vel_X*time;
        double pos_Y2=p.pos_Y+p.vel_Y*time+.5*accel*time*time;
        
        return(pos_Y2-function(pos_X2)>-.0000001);
    }
    public double function(double x){
        return Math.abs(x*slope);
    }
}
