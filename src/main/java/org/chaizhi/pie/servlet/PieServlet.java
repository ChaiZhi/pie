package org.chaizhi.pie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.chaizhi.pie.dao.DataSourceFactory;
import org.chaizhi.pie.model.NoahFcUser;

/**
 * $Id$
 * Copyright(C) 2010-2016 happyelements.com. All rights reserved.
 */

/**
 * 
 * @author <a href="mailto:zhi.chai@happyelements.com">zhi.chai</a>
 * @version 1.0
 * @since 1.0
 */
public class PieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		QueryRunner qr = new QueryRunner(DataSourceFactory.getDataSource());
		String sql = "select * from noah_fc_name";
		List<NoahFcUser> users = new ArrayList<NoahFcUser>();
		try {
			users = qr.query(sql, new BeanListHandler<NoahFcUser>(NoahFcUser.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (NoahFcUser user : users) {
			out.print(user.getName() + "<br/>");
		}
	}

}
