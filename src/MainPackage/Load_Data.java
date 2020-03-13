/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.function.Supplier; 

import java.util.ArrayList;

/**
 *
 * @author nader
 */
public class Load_Data {
     public static void save(ArrayList<User> u)
   {
     try {
          File dfile= new File("file.txt");
       dfile.delete();
     FileWriter fs = new FileWriter("file.txt",true);
     BufferedWriter file = new BufferedWriter (fs);
     for(int i=0;i<u.size();i++){
     file.write(u.get(i).getName()+"\n");
             file.write(u.get(i).getHigh_Score()+"\n");
             file.write( u.get(i).getLevel() +"\n");
             file.write(u.get(i).getLives()+"\n");
     }
     file.close();
     }
     catch(Exception e){System.err.println("Error : "+e.getMessage());}
   }
   public static void read(){
          ArrayList<User> u= new ArrayList<User>();
   try {
  
     FileWriter fs = new FileWriter("file.txt",true);
     BufferedReader  file = new BufferedReader(new FileReader("file.txt"));
     String line;
     
     while((line=file.readLine())!= null)
     {
         String name=line;
         int  score=Integer.valueOf(file.readLine());
         int Level=Integer.valueOf(file.readLine());
         int Lives=Integer.valueOf(file.readLine());
         User user= new User(name, score, Level, Lives);
         System.out.println(name+"        "+Level);
         plan.UserList.add(user);
         
     }
     
    file.close();
   }
     catch(Exception e){System.err.println("Error : "+e.getMessage());}
 
   }
    
}
