

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Servlet implementation class Servlet2
 */
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	char [][] gameboard;
	static HashSet<Integer> player1;
    static HashSet<Integer> player2;
    static ArrayList<Integer> empty;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		gameboard=(char[][])session.getAttribute("gameboard");
		empty=(ArrayList<Integer>)session.getAttribute("empty");
		player1=(HashSet<Integer>)session.getAttribute("player1");
		player2=(HashSet<Integer>)session.getAttribute("player2");
		String name=(String)session.getAttribute("name");
		System.out.println(name);
		int position=AI_generate();
		while(player1.contains(position) || player2.contains(position))
        {
            position=AI_generate();
        }
		place(gameboard,position);
        String status=check_win();
        session.setAttribute("empty", empty);
		session.setAttribute("player1", player1);
		session.setAttribute("player2", player2);
		session.setAttribute("gameboard", gameboard);
		session.setAttribute("name", name);
        out.println("<html>");
       	out.println("<head>");
       	out.println("<meta charset="+"UTF-8"+">");
       	out.println("<title>");
       	out.println("Tic Tac Toe Game");
       	out.println("</title>");
       	out.println("<style>");
       		out.println("body{");
       		out.println("font-family: Arial, sans-serif;");
       		out.println("background-color: #f0f0f0;");
       		out.println("text-align: center;}");
       		out.println("h1{");
       		out.println("color: #007bff;}");
       		out.println(".main{");
       		out.println("display: inline-block;");
       		out.println("margin-top: 20px;");
       		out.println("background-color: #fff;");
       		out.println("padding: 20px;");
       		out.println("border-radius: 8px;");
       		out.println("box-shadow: 0 0 8px rgba(0, 0, 0, 0.2);}");
       		out.println("input[type=+"+"text"+"],input[type="+"submit"+"] {");
       		out.println("padding: 8px;");
       		out.println("font-size: 16px;}");
       		out.println(".board-container {");
       		out.println("margin-top: 20px;");
       		out.println(" display: inline-block;}");
       		out.println(".square-table {");
       		out.println("border-collapse: collapse;");
       		out.println("border: 2px solid #007bff;}");
       		out.println(".cell {");
       		out.println("width: 100px;");
       		out.println("height: 100px;");
       		out.println("text-align: center;");
       		out.println("vertical-align: middle;");
       		out.println("font-size: 24px;");
       		out.println("font-weight: bold;");
       		out.println("border: 1px solid #007bff;}");
       		out.println(".cell.x {");
       		out.println("color: #ff5722;}");
       		out.println(".cell.o {");
       		out.println("color: #4caf50;}");
       	out.println("</style>");
       	out.println("</head>");
       	out.println("<body>");
       	out.println("<h1>Eager to Play Tic Tac Toe Game against AI developed by Ariveni Manikanta</h1>");
       	out.println("<div class="+"main"+">");
       	out.println("<div class="+"board-container"+">");
       	out.println("<table class="+"square-table>");
       	out.println("<tr>");
			out.println("<td class="+"cell"+">"+gameboard[0][0]+"</td>");
			out.println("<td class="+"cell"+">"+gameboard[0][1]+"</td>");
			out.println("<td class="+"cell"+">"+gameboard[0][2]+"</td>");
		out.println("</tr>");
		out.println("<tr>");
			out.println("<td class="+"cell"+">"+gameboard[1][0]+"</td>");
			out.println("<td class="+"cell"+">"+gameboard[1][1]+"</td>");
			out.println("<td class="+"cell"+">"+gameboard[1][2]+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class="+"cell"+">"+gameboard[2][0]+"</td>");
			out.println("<td class="+"cell"+">"+gameboard[2][1]+"</td>");
			out.println("<td class="+"cell"+">"+gameboard[2][2]+"</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</div>");
        if(status.length()>0)
        {
        	session.invalidate();
        	if(status.charAt(0)=='D')
        	{
        		out.println("<h1>"+name +" You Can't defeat My AI</h1>");
        		
               }
        	else {
        		out.println("<h1> My AI defeted you "+name+"</h1>");
              }
        	out.println("</div>");
			out.println("<form  method="+"post"+" action="+"Index.html>");
       		
       		out.println("<input type="+"submit"+" value="+"play again>");
       		out.println("</form>");
	        out.println("</body>");
	        out.println("</html");
	        return ;
        	
        }
        else {
        	out.println("<form  method="+"post"+" action="+"authenticate>");
       		out.println("<input type="+"text"+" placeholder="+"Enter your choice"+" name="+"choice1>");
       		out.println("<input type="+"submit"+" value="+"play>");
       		out.println("</form>");
        }
        out.println("</div>");
        out.println("</body>");
        out.println("</html");
        
	}
	static void place(char [][] gameboard,int position){
        char syb='O';
        player2.add(position);
        empty.remove(Integer.valueOf(position));
        
        switch(position){
        case 1: gameboard[0][0]=syb;
                break;
        case 2: gameboard[0][1]=syb;
                break;
        case 3: gameboard[0][2]=syb;
                break;    
        case 4: gameboard[1][0]=syb;
                break;
        case 5: gameboard[1][1]=syb;
                break;
        case 6: gameboard[1][2]=syb;
                break;
        case 7: gameboard[2][0]=syb;
                break;
        case 8: gameboard[2][1]=syb;
                break;
        case 9: gameboard[2][2]=syb;
                break;
        default : System.out.println("In valid option");                                    
    }
        

    }
	static int AI_generate(){
        int i;
        for(i=0;i<empty.size();i++)
        {
            player2.add(empty.get(i));
            String ans=check_win();
            if(ans.equals("Player 2 Won"))
            {

                player2.remove(empty.get(i));
                return empty.get(i);
            }
            
            player2.remove(empty.get(i));
        }
        for(i=0;i<empty.size();i++)
        {
            player2.add(empty.get(i));
            String ans=check_win();
            if(ans.equals("Draw"))
            {
                player2.remove(empty.get(i));
                return empty.get(i); 
            }
            player2.remove(empty.get(i));
        }
        for(i=0;i<empty.size();i++)
        {    
            player1.add(empty.get(i));
            String ans=check_win();
            if(ans.equals("Player1 won"))
            {
                player1.remove(empty.get(i));
                return empty.get(i);
            }
            player1.remove(empty.get(i));
        }
        for(i=0;i<empty.size();i++)
        {    
            player1.add(empty.get(i));
            String ans=check_win();
            if(ans.equals("Draw"))
            {
                player1.remove(empty.get(i));
                return empty.get(i);   
            }
            player1.remove(empty.get(i));
        }
        if((player1.contains(1) && player1.contains(8) && (player2.size()==1 && player2.contains(5))))
        {
        	return 7;
        }
        if((player1.contains(3) && player1.contains(8) && (player2.size()==1 && player2.contains(5))))
        {
        	return 9;
        }
        if((player1.contains(7) && player1.contains(2) && (player2.size()==1 && player2.contains(5))))
        {
        	return 1;
        }
        if((player1.contains(9) && player1.contains(2) && (player2.size()==1 && player2.contains(5))))
        {
        	return 3;
        }
        if(empty.contains(5))
        {
            return 5;
        }
        else{
            if(player2.contains(5))
            {
               int[] corner=new int[]{2,4,6,8};
                for(int j:corner){
                    if(empty.contains(j))
                    {
                    return j;
                    }   
                }
                    
            } 
            else{
                int[] corner=new int[]{1,3,7,9};
                for(int j:corner){
                    if(empty.contains(j))
                    {
                        return j;
                    }
                    
                }
            }
            
            return empty.get(i);
        }
        
    }
	static String check_win(){
        HashSet<Integer> r1=new HashSet<Integer>();
        r1.add(1);r1.add(2);r1.add(3);
        HashSet<Integer> r2=new HashSet<Integer>();
        r2.add(4);r2.add(5);r2.add(6);
        HashSet<Integer> r3=new HashSet<Integer>();
        r3.add(7);r3.add(8);r3.add(9);

        HashSet<Integer> c1=new HashSet<Integer>();
        c1.add(1);c1.add(4);c1.add(7);
        HashSet<Integer> c2=new HashSet<Integer>();
        c2.add(2);c2.add(5);c2.add(8);
        HashSet<Integer> c3=new HashSet<Integer>();
        c3.add(3);c3.add(6);c3.add(9);

        HashSet<Integer> d1=new HashSet<Integer>();
        d1.add(1);d1.add(5);d1.add(9);
        HashSet<Integer> d2=new HashSet<Integer>();
        d2.add(3);d2.add(5);d2.add(7);        

        HashSet<HashSet> set =new HashSet<HashSet> ();
        set.add(r1);set.add(r2);set.add(r3);
        set.add(c1);set.add(c2);set.add(c3);
        set.add(d1);set.add(d2);

        for(HashSet c:set){
            if(player1.containsAll(c)){
                return "Player1 won";
            }
            else if(player2.containsAll(c))
            {
                return "Player 2 Won";
            }
        }
        if(player1.size()+player2.size()==9)
        {
            return "Draw";
        }
        else{
            return "";
        }

}
	

}
