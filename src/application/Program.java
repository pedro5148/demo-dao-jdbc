package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
//		Department obj = new Department(1, "Livros");
//		System.out.println(obj);
//		
//		Seller seller = new Seller(21, "Pedro", "emai@gmail.com",new Date(), 3000.0, obj);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== Teste 1: Seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("\n=== Teste 2: Seller findByDepartmentId ===");
		Department dep = new Department(2, null);
		List<Seller> lista = sellerDao.findByDepartment(dep);
		for (Seller obj : lista) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== Teste 3: FindAll  ===");
		lista = sellerDao.findAll();
		for (Seller obj : lista) {
			System.out.println(obj);
		}
	}

}
