
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
 
@WebServlet("/myProduct")
public class myProduct extends HttpServlet {
   public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
              throws java.io.IOException,ServletException {
    String num = request.getParameter("num");		//������������
    StringTokenizer fenxi=new StringTokenizer(num,",");		//�ַ���������
    int[] number=new int[num.length()];				//����һ�����ַ���תΪ���͵���������
    int i=0;							
    int j=fenxi.countTokens();						//����ַ���һ���ж��ٸ�����
    while(fenxi.hasMoreTokens()){
    	number[i]=Integer.parseInt(fenxi.nextToken());
    	i++;
    }
    int[] sum=new int[j];
	for(int l=0;l<j;l++){
		int product=1;							//�˻���ʼ��Ϊ1
		for(int p=0;p<j;p++){
			if(l==p){							//������Ϊ�Լ�����ʱ���������γ˻�
				continue;
			}
			product=product*number[p];
		}
		sum[l]=product;
	}
	String answer="";							//����һ���ַ���answer��������
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