package com.jsm.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import com.jsm.domain.Cliente;
import com.jsm.dto.ClienteDTO;
import com.jsm.error.FieldMessage;
import com.jsm.repositories.ClienteRepository;

@Component
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	HttpServletRequest request;

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		System.out.println(map);

		Long uriId = Long.parseLong(map.get("id"));
		
		System.out.println("URI id: "+uriId);

		List<FieldMessage> list = new ArrayList<>();

		Optional<Cliente> c = repo.findByEmail(dto.getEmail());
		if (c.isPresent() && !dto.getId().equals(uriId) ) {
			list.add(new FieldMessage("Email", "O Email pertence a outro cliente, cliente não pode ser atualizado!"));
		}

		for (FieldMessage fm : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fm.getFielMessage()).addPropertyNode(fm.getFieldName())
					.addConstraintViolation();

		}
		return list.isEmpty();
	}

}
