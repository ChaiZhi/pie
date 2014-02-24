package org.chaizhi.pie.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源工厂
 * 
 */
public class DataSourceFactory {

	private static final Log LOG = LogFactory.getLog(DataSourceFactory.class);

	private static DruidDataSource ds;

	static {
		try {
			ds = initDataSource();
			initShutdownHook();
		} catch (Exception e) {
			LOG.error("DataSource init failed! " + e.getMessage(), e);
		}
	}

	private static DruidDataSource initDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://10.130.136.29:3306/noah_fc?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true");
		dataSource.setUsername("root");
		dataSource.setPassword("test");
		dataSource.setMaxActive(10);
		dataSource.setValidationQuery("select 1");
		dataSource.setTestWhileIdle(true);

		dataSource.setInitialSize(10);
		dataSource.init();

		return dataSource;
	}

	private static void initShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				shutdownDataSource();
			}
		});
	}

	/**
	 * 获取数据源
	 * @return
	 */
	public static DataSource getDataSource() {
		if (ds == null) {
			throw new IllegalStateException("DataSource init failed!");
		}

		return ds;
	}

	/**
	 * 关闭数据源
	 */
	public static void shutdownDataSource() {
		if (ds != null) {
			ds.close();
		}
	}

}
