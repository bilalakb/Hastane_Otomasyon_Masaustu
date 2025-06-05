package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class clinic {
	private int id;
	private String name;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public clinic() {
	}

	public clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ArrayList<clinic> getList() throws SQLException {
		ArrayList<clinic> list = new ArrayList<>();
		clinic obj;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic");
			while (rs.next()) {
				obj = new clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
		
	}
	
	public clinic getFetch(int id) {
		Connection con = conn.connDb();
		clinic c = new clinic();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic WHERE id="+id);
			while(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;

	}

	public boolean addClinic(String name) throws SQLException {

		String query = "INSERT INTO clinic " + "(name) VALUES" + "(?)";
		boolean key = false;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

	public boolean deleteClinic(int id) throws SQLException {

		String query = "Delete From clinic WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

	public boolean uptadeClinic(int id, String name) throws SQLException {

		String query = "UPDATE clinic SET name = ? WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}
	
	public ArrayList<user> getClinicDoctorList(int clinic_id) throws SQLException {
		ArrayList<user> list = new ArrayList<>();
		user obj;
		try {
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.id,u.tcno,u.type,u.name,u.password FROM worker w LEFT JOIN user u on w.user_id = u.id WHERE clinic_id= "+ clinic_id);
			while (rs.next()) {
				obj = new user(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.password"), rs.getString("u.name"),
						rs.getString("u.type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
