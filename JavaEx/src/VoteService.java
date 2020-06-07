import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VoteService {
	 public static  Connection GetConnection()
	    {
	        Connection conn=null;
	    	final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
	    	final String DB_URL="jdbc:mysql://localhost:3306/vote?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	    	final String DB_USER="root";
	    	final String DB_PASSWORD="19980412zhou";
	        try {
	    		Class.forName(DB_DRIVER);
	    		conn=DriverManager.getConnection
	    				(DB_URL,DB_USER,DB_PASSWORD);
	    		return conn;
	  	    }
	        catch(Exception e)
	        {
	        	System.out.println(e.getMessage());
	        	return null;
	        }
	    }
   
	 
	   
	 
     public static boolean AddCandidate(Candidate can)throws Exception
     {
    	Object [][]infor=QueryAllCandidate();
     	for(int i=0;i<infor.length;i++)
     	{
     		if((int)infor[i][0]==can.GetId())
     		{
     			throw new Exception("该候选人已存在！");
     		}
     	}
     	String sql="insert into candidate values('"+can.GetId()+"','"+can.GetName()+"','"+0+"','"+can.GetIntruduce()+"')";
     	Connection n=GetConnection();
     	PreparedStatement p=null;
		p=n.prepareStatement(sql);
		p.executeUpdate();
		p.close();
		n.close();
		return true;
     }
     public static Object[][] QueryAllCandidate()throws Exception
     {
    		Connection n=GetConnection();
        	String sql = "select * from candidate ORDER BY score DESC";     
        	Statement st =null;	
        		
    		st= n.createStatement();
    		ResultSet rs=st.executeQuery(sql);
    		int count =0;
    		while(rs.next())
    		{
    			count++;
    		}
    		rs=st.executeQuery(sql);
    		Object[][] infor=new Object[count][4];
    		count=0;
    		while(rs.next())
    		{
    			infor[count][0] = Integer.valueOf( rs.getInt("id")); 
    			infor[count][1] = rs.getString("name");
    			infor[count][2] = Integer.valueOf( rs.getInt("score")); 
    			infor[count][3] = rs.getString("intruduce");
    			count++;
    		}
    		st.close();
    		n.close();
    		return infor;
        	
     }
     public static boolean UpdateCandidate(int canid)throws Exception
     {
    	 Connection n=GetConnection();
     	String sql="update candidate set score=score+'1' where id="+canid;
     	Statement s=null;
 		s=n.createStatement();
 		s.executeUpdate(sql);
 		s.close();
 		n.close();
 		return true;
     }
     public static Object[]QueryCandidateById(int id)throws Exception
     {
    	 Connection n=GetConnection();
     	String sql="select * from candidate where id="+id;
     	
 		Statement s=n.createStatement();
 		ResultSet rs=s.executeQuery(sql);
 		if(rs.next())
 		{
 			Object[] infor=new Object[4];
 			infor[0] = Integer.valueOf( rs.getInt("id")); 
 			infor[1] = rs.getString("name");
 			infor[2] = Integer.valueOf( rs.getInt("score")); 
 			infor[3] = rs.getString("intruduce");
 			s.close();
 			n.close();
 			return infor;
 		}
 		s.close();
 		n.close();
 		return null;
     }
     public static Object[][]QueryBlurry(String key) throws Exception
     {
     	Connection n=GetConnection();
     	String sql = "select * from candidate where id like  '%"+key+"%'or name like'%"+key+"%' or intruduce like '%"+key+"%'";     
     	Statement st =null;	
     		
 		st= n.createStatement();
 		ResultSet rs=st.executeQuery(sql);
 		int count =0;
 		while(rs.next())
 		{
 			count++;
 		}
 		rs=st.executeQuery(sql);
 		Object[][] infor=new Object[count][4];
 		count=0;
 		while(rs.next())
 		{
 			infor[count][0] = Integer.valueOf( rs.getInt("id")); 
			infor[count][1] = rs.getString("name");
			infor[count][2] = Integer.valueOf( rs.getInt("score")); 
			infor[count][3] = rs.getString("intruduce");
			count++;
 		}
 		st.close();
 		n.close();
 		return infor;
     
     }
     
     public static boolean AddVoter(int id,String name)throws Exception
     {
    	 Object [][]infor=QueryAllVoter();
      	for(int i=0;i<infor.length;i++)
      	{
      		if((int)infor[i][0]==id)
      		{
      			throw new Exception("该投票者已存在！");
      		}
      	}
      	String sql="insert into voter values('"+id+"','"+name+"','"+id+"','"+0+"','"+0+"')";
      	Connection n=GetConnection();
      	PreparedStatement p=null;
 		p=n.prepareStatement(sql);
 		p.executeUpdate();
 		p.close();
 		n.close();
 		return true;
     }
     public static Object[][] QueryAllVoter()throws Exception
     {
    	 Connection n=GetConnection();
     	String sql = "select * from voter";     
     	Statement st =null;	
     		
 		st= n.createStatement();
 		ResultSet rs=st.executeQuery(sql);
 		int count =0;
 		while(rs.next())
 		{
 			count++;
 		}
 		rs=st.executeQuery(sql);
 		Object[][] infor=new Object[count][4];
 		count=0;
 		while(rs.next())
 		{
 			infor[count][0] = Integer.valueOf( rs.getInt("id")); 
 			infor[count][1] = rs.getString("name");
 			infor[count][2] = Integer.valueOf( rs.getInt("candidateid1")); 
 			infor[count][3] = Integer.valueOf( rs.getInt("candidateid2")); 
 			count++;
 		}
 		st.close();
 		n.close();
 		return infor;
     }
     public static String GetPassword(int id)throws Exception
     {
    	 Connection n=GetConnection();
     	String sql="select password from voter where id="+id;
     	
 		Statement s=n.createStatement();
 		ResultSet rs=s.executeQuery(sql);
 		rs.next();
 		String pw=rs.getString("password");
 		s.close();
 		n.close();
 		return pw;
     }
     public static Object[]QueryVoterById(int id)throws Exception
     {
    	 Connection n=GetConnection();
     	String sql="select * from voter where id="+id;
     	
 		Statement s=n.createStatement();
 		ResultSet rs=s.executeQuery(sql);
 		if(rs.next())
 		{
 			Object[] infor=new Object[5];
 			infor[0] = Integer.valueOf( rs.getInt("id")); 
 			infor[1] = rs.getString("name");
 			infor[2] =  rs.getString("password"); 
 			infor[3] = Integer.valueOf( rs.getInt("candidateid1")); 
 			infor[4] = Integer.valueOf( rs.getInt("candidateid2")); 
 		
 			s.close();
 			n.close();
 			return infor;
 		}
 		s.close();
 		n.close();
 		return null;
     }
     public static Object[][]QueryVoterByCanId(int id) throws Exception
     {
    	 Connection n=GetConnection();
      	String sql = "select * from voter where candidateid1="+id+" or candidateid2="+id;     
      	Statement st =null;	
      		
  		st= n.createStatement();
  		ResultSet rs=st.executeQuery(sql);
  		int count =0;
  		while(rs.next())
  		{
  			count++;
  		}
  		rs=st.executeQuery(sql);
  		Object[][] infor=new Object[count][3];
  		count=0;
  		int score=1;
  		while(rs.next())
  		{
  			infor[count][0] = Integer.valueOf( rs.getInt("id")); 
  			infor[count][1] = rs.getString("name");
  			if(Integer.valueOf( rs.getInt("candidateid1"))==id&&Integer.valueOf( rs.getInt("candidateid2"))==id)
  			{
  			   score=2;
  			}
  			else
  			{
  				score=1;
  			}
  			infor[count][2] = score;
  			count++;
  		}
  		st.close();
  		n.close();
  		return infor;
     }
     
     public static boolean UpdateVoteToCandidate(int id)throws Exception
     {
    	 Connection n=GetConnection();
    	    
      	 String sql="update voter set iscandi='1' where id="+id;
      	Statement s=null;
  		s=n.createStatement();
  		s.executeUpdate(sql);
  		s.close();
  		n.close();
  		return true;
     }
     
     public static boolean UpdateVoter(int id,int number,int canid)throws Exception
     {
    	 Connection n=GetConnection();
    
      	 String sql="update voter set candidateid1="+canid+" where id="+id;
    	 if(number==2)
    	 {
    		 sql="update voter set candidateid2="+canid+" where id="+id;
    	 }
      	Statement s=null;
  		s=n.createStatement();
  		s.executeUpdate(sql);
  		s.close();
  		n.close();
  		return true;
     }
    
     
     
}
