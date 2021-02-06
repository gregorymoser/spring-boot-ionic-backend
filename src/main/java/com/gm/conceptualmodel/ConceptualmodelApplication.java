package com.gm.conceptualmodel;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gm.conceptualmodel.domain.Address;
import com.gm.conceptualmodel.domain.Category;
import com.gm.conceptualmodel.domain.City;
import com.gm.conceptualmodel.domain.Customer;
import com.gm.conceptualmodel.domain.Product;
import com.gm.conceptualmodel.domain.State;
import com.gm.conceptualmodel.domain.enums.CustomerType;
import com.gm.conceptualmodel.repositories.AddressRepository;
import com.gm.conceptualmodel.repositories.CategoryRepository;
import com.gm.conceptualmodel.repositories.CityRepository;
import com.gm.conceptualmodel.repositories.CustomerRepository;
import com.gm.conceptualmodel.repositories.ProductRepository;
import com.gm.conceptualmodel.repositories.StateRepository;

@SpringBootApplication
public class ConceptualmodelApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ConceptualmodelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State (null, "Minas Gerais");
		State st2 = new State (null, "Sao Paulo");
		
		City c1 = new City(null, "Uberlandia", st1);
		City c2 = new City(null, "Sao Paulo", st2);
		City c3 = new City(null, "Campinas", st2);
		
		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Customer cus1 = new Customer(null, "Mary", "mary@gmail.com", "36378912377", CustomerType.NATURALPERSON);
		cus1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		Address a1 = new Address(null, "Flower Street", "300", "Apartment 303", "Garden", "38220834", cus1, c1);
		Address a2 = new Address(null, "Capital Avenue", "105", "Office 800", "Center", "38777012", cus1, c2);
		
		cus1.getAddresses().addAll(Arrays.asList(a1, a2));
		
		customerRepository.saveAll(Arrays.asList(cus1));
		addressRepository.saveAll(Arrays.asList(a1, a2));
	}
}