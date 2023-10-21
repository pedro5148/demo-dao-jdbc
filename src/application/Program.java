package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
//		Department obj = new Department(1, "Livros");
//		System.out.println(obj);
//		
//		Seller seller = new Seller(21, "Pedro", "emai@gmail.com",new Date(), 3000.0, obj);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

	}

}
