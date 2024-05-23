package com.example.KVBank;

import com.example.KVBank.entities.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@SpringBootApplication
public class KvBankApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(KvBankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = "SELECT * FROM bankaccount";
		List<BankAccount> customers = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(BankAccount.class));

		customers.forEach(System.out::println);
	}
}
