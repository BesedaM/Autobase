package by.epam.javatraining.beseda.webproject.integrationtests.databasecreator;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

public class DatabaseCreator {

	private static Lock lock = new ReentrantLock();

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	private static String ENCODING = "UTF-8";
	private static String DROP_DB_STATEMENT = "DROP SCHEMA trucking_company CASCADE;";
	private static String TRUNCATE_TABLE_STATEMENT = "TRUNCATE trucking_company.table CASCADE";
	private static String CREATE_DB_SCRIPT = "db_dump.sql";

	private static EmbeddedPostgres pg;

	public static void init() {
		try {
			openDatabase();
		} catch (IOException e) {
			log.error(e);
		}
	}

	private static EmbeddedPostgres openDatabase() throws IOException {
		pg = EmbeddedPostgres.builder().start();
		return pg;
	}

	public static DataSource getDataSource() {
		return pg.getPostgresDatabase();
	}

	public static void fillDatabase() {
		lock.lock();
		Connection connection = null;
		try {
			DataSource dataSource = pg.getPostgresDatabase();
			connection = dataSource.getConnection();

			ScriptRunner scriptRunner = new ScriptRunner(connection);
			scriptRunner.runScript(createScript(CREATE_DB_SCRIPT));

		} catch (SQLException /* | IOException */ e) {
			log.error(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e);
				}
			}
			lock.unlock();
		}
	}

	private static BufferedReader createScript(String fileName) {
		File dbScript = new File(classLoader.getResource(fileName).getFile());
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(dbScript), ENCODING));
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			log.error(e);
		}
		return br;
	}

	public static void cleanDatabase() {
		lock.lock();
		try (Connection connection = pg.getPostgresDatabase().getConnection()) {
			Statement st = connection.createStatement();
			st.execute(DROP_DB_STATEMENT);
		} catch (SQLException e) {
			log.error(e);
		} finally {
			lock.unlock();
		}
	}

	public static void truncateTable(String tableName) {
		lock.lock();
		try (Connection connection = pg.getPostgresDatabase().getConnection()) {
			Statement st = connection.createStatement();
			String sql = TRUNCATE_TABLE_STATEMENT.replace("table", tableName);
			st.execute(sql);
		} catch (SQLException e) {
			log.error(e);
		} finally {
			lock.unlock();
		}
	}

	public static void closeDatabase() throws IOException {
		pg.close();
	}

	public Connection getConnection() throws SQLException {
		return pg.getPostgresDatabase().getConnection();
	}

}
