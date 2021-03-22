package com.gm.conceptualmodel;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gm.conceptualmodel.domain.Address;
import com.gm.conceptualmodel.domain.BillPayment;
import com.gm.conceptualmodel.domain.CardPayment;
import com.gm.conceptualmodel.domain.Category;
import com.gm.conceptualmodel.domain.City;
import com.gm.conceptualmodel.domain.Customer;
import com.gm.conceptualmodel.domain.ItemRequest;
import com.gm.conceptualmodel.domain.Payment;
import com.gm.conceptualmodel.domain.Product;
import com.gm.conceptualmodel.domain.Request;
import com.gm.conceptualmodel.domain.State;
import com.gm.conceptualmodel.domain.enums.CustomerType;
import com.gm.conceptualmodel.domain.enums.PaymentStatus;
import com.gm.conceptualmodel.repositories.AddressRepository;
import com.gm.conceptualmodel.repositories.CategoryRepository;
import com.gm.conceptualmodel.repositories.CityRepository;
import com.gm.conceptualmodel.repositories.CustomerRepository;
import com.gm.conceptualmodel.repositories.ItemRequestRepository;
import com.gm.conceptualmodel.repositories.PaymentRepository;
import com.gm.conceptualmodel.repositories.ProductRepository;
import com.gm.conceptualmodel.repositories.RequestRepository;
import com.gm.conceptualmodel.repositories.StateRepository;

@SpringBootApplication
public class ConceptualmodelApplication implements CommandLineRunner {

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
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ItemRequestRepository itemRequestRepository;

	public static void main(String[] args) {
		SpringApplication.run(ConceptualmodelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Bedroom");
		Category cat4 = new Category(null, "Electronics");
		Category cat5 = new Category(null, "Gardening");
		Category cat6 = new Category(null, "Decoration");
		Category cat7 = new Category(null, "Perfumery");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "Sao Paulo");

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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Request req1 = new Request(null, sdf.parse("30/09/2017 10:32"), cus1, a1);
		Request req2 = new Request(null, sdf.parse("10/10/2017 19:35"), cus1, a2);

		Payment pay1 = new CardPayment(null, PaymentStatus.PAID, req1, 6);
		req1.setPayment(pay1);
		Payment pay2 = new BillPayment(null, PaymentStatus.PENDING, req2, sdf.parse("20/10/2017 00:00"), null);
		req2.setPayment(pay2);

		cus1.getRequests().addAll(Arrays.asList(req1, req2));

		requestRepository.saveAll(Arrays.asList(req1, req2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		ItemRequest ir1 = new ItemRequest(req1, p1, 0.0, 1, 2000.00);
		ItemRequest ir2 = new ItemRequest(req1, p3, 0.0, 2, 80.00);
		ItemRequest ir3 = new ItemRequest(req2, p2, 100.0, 1, 800.00);
		
		req1.getItems().addAll(Arrays.asList(ir1, ir2));
		req2.getItems().addAll(Arrays.asList(ir3));
		
		p1.getItems().addAll(Arrays.asList(ir1));
		p2.getItems().addAll(Arrays.asList(ir3));
		p3.getItems().addAll(Arrays.asList(ir2));
		
		itemRequestRepository.saveAll(Arrays.asList(ir1, ir2, ir3));
	}
}