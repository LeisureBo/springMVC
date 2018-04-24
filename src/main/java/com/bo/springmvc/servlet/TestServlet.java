package com.bo.springmvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 
 * @author 王博
 * @version 2018年4月24日　下午5:00:50
 */
@WebServlet(urlPatterns= {"/test"})
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = -418862750697087340L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/test.html").forward(req, resp);
	}
	
}
