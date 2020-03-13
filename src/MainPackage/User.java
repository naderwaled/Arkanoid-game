package MainPackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class User
{

    private String name;

    private int Score;
    private int level;

    private int High_Score;
    private int lives;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    public User(String name, int High_Score,int level,int Lives) 
    {
        this.name = name;
        this.lives=Lives;
        this.High_Score = High_Score;
        this.level=level;
    }
    public int getHigh_Score() {
        return High_Score;
    }
    public String getName() 
    {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHigh_Score(int High_Score) {
        this.High_Score = High_Score;
    }
    
   

   
    
    
    
}
