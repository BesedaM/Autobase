package database.filling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.service.PasswordHash;

import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseProperties.DATABASE_PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseProperties.DATABASE_URL;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseProperties.DATABASE_USER;

public class FillingDB {

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);) {

			PreparedStatement ps = connection
					.prepareStatement("UPDATE trucking_company.users SET password=? WHERE id=?");

			String[] pw = new String[12];
			pw[0] = "";
			pw[1] = "admin";
			pw[2] = "fbh676";
			pw[3] = "j65hj8";
			pw[4] = "eet7e4";
			pw[5] = "D6u99y";
			pw[6] = "Nh89hkl";
			pw[7] = "GooD56";
			pw[8] = "Tik_Toe8";
			pw[9] = "boria123";
			pw[10] = "25taniaQ";
			pw[11] = "678Run5";

			
			for (int i = 1; i <= 11; i++) {
				ps.setBytes(1, PasswordHash.getHash(pw[i]));
				ps.setInt(2, i);
				
				ps.addBatch();
			}

			ps.executeBatch();

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

}
