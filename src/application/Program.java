package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Department obj = new Department(01, "Electronics");
		System.out.println(obj);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		Date birtDate = formatter.parse("08-08-1988");
		
		Seller sel = new Seller(1, "Teste", "Teste@teste.com", birtDate, 4900.00, obj);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(1);
		
		
		
		System.out.println(seller);

	}

}
