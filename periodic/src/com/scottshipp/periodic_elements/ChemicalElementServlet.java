package com.scottshipp.periodic_elements;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class Elements
 */
@WebServlet("/ChemicalElement")
public class ChemicalElementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChemicalElementServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SearchOptions searchCriteria = SearchOptions.getOptionFromString(request.getParameter("searchCriteria").trim());
		String searchTerm = request.getParameter("searchTerm").trim().toLowerCase();
		ChemicalElement result = null;
		if(SearchOptions.contains(searchCriteria)) {
			result = LookupChemicalElement.by(searchCriteria, searchTerm);
		} else {
			result = new ChemicalElement();
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(new JSONObject(result));
	}

}
