package ru.iteco.account;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.iteco.account.model.dto.AddressDto;
import ru.iteco.account.model.entity.AddressEntity;
import ru.iteco.account.model.entity.BankBookEntity;
import ru.iteco.account.model.entity.UserEntity;
import ru.iteco.account.repository.UserRepository;
import ru.iteco.account.service.AddressService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class AccountApplicationTests {

	@Autowired
	private AddressService addressService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testLoadUserFromAddress() {
		AddressDto addressDto = AddressDto.builder().country("RUSSIA").city("OMSK").street("LENINA").home("6").build();
		UserEntity userByAddress = addressService.getUserByAddress(addressDto);
		log.info(String.valueOf(userByAddress));
	}

	@Test
	@Transactional
	void testLoadUserLazyAddress() {
		Optional<UserEntity> userOpt = userRepository.findById(1);
		UserEntity userEntity = userOpt.get();
		log.info("NOT ADDRESS");
		AddressEntity address = userEntity.getAddress();
		log.info("ADDRESS: {}", address.toString());

		List<BankBookEntity> bankBooks = userEntity.getBankBooks();
		log.info("BANK_BOOK: {}", bankBooks);
	}

}
