package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
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
		
		System.out.println("\n=== Teste 4: Seller Insert  ===");
		Seller novo = new Seller(null, "Maria Paula", "maria@email.com", new Date(), 400.0, dep);
		sellerDao.insert(novo);
		System.out.println("Inserido! Seu ID = "+ novo.getId());
				
		System.out.println("\n=== Teste 5: Seller Update  ===");
		seller = sellerDao.findById(8);
		seller.setName("Maria Eduarda");
		sellerDao.update(seller);
		System.out.println("Update completo!");
		
		System.out.println("\n=== Teste 6: Seller Delete  ===");
		System.out.println("Digite o id para remocao: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete complete.");
		sc.close();
	}

}
