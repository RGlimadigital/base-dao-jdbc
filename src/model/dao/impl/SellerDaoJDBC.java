package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
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
	
	
	public List<Seller> findByDepId(Department department) {
		List<Seller> sellerList = new ArrayList<>();
		Map<Integer, Department> depMap = new HashMap<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department " 
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name"
					);
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			
			
			if(rs == null) return null;
			
			while(rs.next()) {

				
				Department dep = depMap.get(rs.getInt("DepartmentId"));
				
				if(dep == null){
					dep = instantiateDepartment(rs);
					depMap.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instatiateSeller(rs, dep);
				sellerList.add(obj);
				
			}
			
			return sellerList;
			
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
		}
		
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.id " 
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				
				Department dep = instantiateDepartment(rs);
				Seller sel = instatiateSeller(rs, dep);
				
				return sel;
				
			}
			
			return null;
			
		}catch (SQLException e) {
			
			throw new DbException(e.getMessage());
			
		}finally {
			
			DB.closeStatement(st);
			DB.closeSet(rs);
			
			
		}
		
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department  dep =  new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}
	
	private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
		
		Seller sel = new Seller();
		
		sel.setId(rs.getInt("Id"));
		sel.setName(rs.getString("Name"));
		sel.setEmail(rs.getString("Email"));
		sel.setBirthDate(rs.getDate("BirthDate"));
		sel.setBaseSalary(rs.getDouble("BaseSalary"));
		sel.setDepartment(dep);
		
		return sel;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
