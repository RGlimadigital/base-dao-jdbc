package application;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=== TESTE 1: seller findById ===" );
		System.out.println("Insira o Id do vendedor: ");
		int sellerId = sc.nextInt();
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(sellerId);
		System.out.println(seller);
		
		Department department = new Department(4, null);
		
		System.out.println("\n=== TESTE 2: seller find By Department Id ===" );
		List<Seller> list = sellerDao.findByDepId(department);
		
		for(Seller ls : list) {
			System.out.println(ls);
		}

	}

}
