package noTitle;

import java.util.Scanner;

public class ScannerDemo03{

     public static void main(String[] args){

          Scanner scan=new Scanner(System.in);

          boolean flag=true;

          int i=0;

          float f=0.0f;



          while(flag){

                System.out.println("输入整数:\n\n");

                if(scan.hasNextInt()){

                      i=scan.nextInt();

                      System.out.println("整数数据:"+i+"\n");

              
                      flag=false;


                }else{

                      System.out.println("输入的不是整数！\n");

                      flag=true;

                }

           }

          System.out.println("输入小数:\n\n");

          if(scan.hasNextFloat()){

               f=scan.nextFloat();

               System.out.println("小数数据:"+f);


          }else{

               System.out.println("输入的不是小数！");

          }


     }


}

