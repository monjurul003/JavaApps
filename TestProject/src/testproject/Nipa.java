/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testproject;

public class Nipa
{
public static void main (String args[])
{
   int v,b; char s;
   KK  k1=new KK();
   k1.mim();
   BB b1=new BB();
   System.out.println(b1.value());
   v=phar(3, 5);
   System.out.println(v);
   System.out.println(k1.p);  
   s=ule();
 }
 public static int  phar (int k, int h)
{    return k+h;}
public static char ule() 
{ System.out.println("Very Hard Question");
  return 'y'; }
}
class BB
{
   public static int p=5;
   public static int value() 
   {  return 7; }
   public static int gairle (){ return 0;}
 }
class KK extends BB
{	public static int h, x;
	public static void mim()
	{ System.out.println(p);
	   x=value()-1;
	    System.out.println(x);
	  }
}
