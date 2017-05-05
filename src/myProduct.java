
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
 
@WebServlet("/myProduct")
public class myProduct extends HttpServlet {
   public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
              throws java.io.IOException,ServletException {
    String num = request.getParameter("num");		//获得输入的数字
    StringTokenizer fenxi=new StringTokenizer(num,",");		//字符串分析器
    int[] number=new int[num.length()];				//定义一个放字符串转为整型的整型数组
    int i=0;							
    int j=fenxi.countTokens();						//获得字符串一共有多少个数字
    while(fenxi.hasMoreTokens()){
    	number[i]=Integer.parseInt(fenxi.nextToken());
    	i++;
    }
    int[] sum=new int[j];
	for(int l=0;l<j;l++){
		int product=1;							//乘积初始化为1
		for(int p=0;p<j;p++){
			if(l==p){							//当乘数为自己本身时，跳过本次乘积
				continue;
			}
			product=product*number[p];
		}
		sum[l]=product;
	}
	String answer="";							//定义一个字符串answer来输出结果
	for(int a=0;a<j;a++){
		if(a==0){
			answer=answer+sum[a];
			continue;
		}
		answer=answer+","+sum[a];
	}
    HttpSession session = request.getSession();
    synchronized(session) {
        session.setAttribute("answer", answer);
    }
    RequestDispatcher rd =
        request.getRequestDispatcher("/display.jsp");
    rd.forward(request,response);
  }
}