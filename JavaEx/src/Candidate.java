
public class Candidate {
   private int id;
   private String name;
   private String intruduce;
   private int score;
  
   public int GetId() 
   {
	   return id;
   }
   public boolean SetId(int id)
   {
	   this.id=id;
	   return true;
   }
   public String GetName()
   {
	   return name;
   }
   public boolean SetName(String name)
   {
	   try {
	        this.name=name;
	        return true;
	   }
	   catch(Exception e)
	   {
		   return false;
	   }
   }
   public int GetScore()
   {
	   return score;
   }
   public boolean SetScore(int score)
   {
	   this.score=score;
	   return true;
   }
   public String GetIntruduce()
   {
	   return intruduce;
   }
   public boolean SetIntruduce(String intruduce)
   {
	   this.intruduce=intruduce;
	   return true;
   }
}
