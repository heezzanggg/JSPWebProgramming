package ch05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet(description = "My first servlet", urlPatterns = { "/hello" })
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); //응답콘텐츠 타입 설정과 한글 출
		PrintWriter out = response.getWriter(); //printWriter : 출력 스트림으로 클라이언트에 전달할 출력 작성에 사용 , response.getWriter() : 출력 스트림 가져
		out.append("<!doctype html><html><head><title>Hello World Servlet</title></head><body>")
			.append("<h2>Hello World!!</h2><hr>")
			.append("현재 날짜와 시간은:"+java.time.LocalDateTime.now())
			.append("</body></html>");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
