package jug.saxony.camp.jcache.war.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jug.saxony.camp.jcache.jar.logic.Service;

@WebServlet("/Servlet")
public class ServletOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String header = "<!DOCTYPE html><html><head><meta charset=" + "UTF-8"
			+ "><title>JUG SAXONY CAMP 2017</title></head><body>";
	private final String body = "</body></html>";
	private final String title = "<h3>JUG SAXONY CAMP 2017</h3>";
	private final String subtitle = "<h4>Caching-Verfahren f&uuml;r Java Enterprise Anwendungen</h4>";
	private final String subsubtitle = "<h6>JCache API und Infinispan-Interceptor</h6>";

	
	@Inject
	private Service service;

	private Date date;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = new PrintWriter(response.getWriter());

		pw.println(header);
		pw.println(title);
		pw.println(subtitle);
		pw.println(subsubtitle);

		date = new Date();
		pw.println("<i>" + date.toString() + "</i>" + "</br></br>");
		
		pw.println("Service.execute(1, 2): " + service.execute(1, 2) + "</br>");
		pw.println("Service.execute(1, 2): " + service.execute(1, 2) + "</br>");
		pw.println("Service.execute(2, 2): " + service.execute(2, 2) + "</br>");
		pw.println("Service.execute(2, 2): " + service.execute(2, 2) + "</br>");
		pw.println("Service.execute(1, 2): " + service.execute(1, 2) + "</br>");
		pw.println("Service.execute(1, 3): " + service.execute(1, 3) + "</br>");
		pw.println("Service.execute(2, 2): " + service.execute(2, 2) + "</br>");

		pw.println(body);
	}
}
