package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT s.*, d.Name as Departamento "
					+ "FROM seller as s, department as d "
					+ "WHERE s.DepartmentId = d.Id "
					+ "AND s.Id = ?"
					);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				 Department dep = instatiateDepartment(rs);
				 Seller obj = instatiateSeller(rs, dep);
				 return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
		 Seller obj = new Seller();
		 obj.setId(rs.getInt("Id"));
		 obj.setName(rs.getString("Name"));
		 obj.setEmail(rs.getString("Email"));
		 obj.setBaseSalary(rs.getDouble("BaseSalary"));
		 obj.setBirthDate(rs.getDate("BirthDate"));
		 obj.setDepartment(dep);
		 return obj;
	}

	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		 Department dep = new Department();
		 dep.setId(rs.getInt("DepartmentId"));
		 dep.setName(rs.getString("Departamento"));
		 return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department dep) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT s.*, d.Name as Departamento "
					+ "FROM seller as s, department as d "
					+ "WHERE s.DepartmentId = d.Id "
					+ "AND d.Id = ? "
					+ "ORDER BY s.Name"
					);
			st.setInt(1, dep.getId());
			rs = st.executeQuery();
			
			List<Seller> lista = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {
				Department dep2 = map.get(rs.getInt("s.DepartmentId"));
				
				if (dep2 == null) {
					dep2 = instatiateDepartment(rs);
					map.put(rs.getInt("s.DepartmentId"), dep2);
				}
				Seller obj = instatiateSeller(rs, dep2);
				lista.add(obj);
			}
			return lista;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
