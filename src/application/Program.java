package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Livros");
		System.out.println(obj);
		
		Seller seller = new Seller(21, "Pedro", "emai@gmail.com",new Date(), 3000.0, obj);
		System.out.println(seller);

	}

}