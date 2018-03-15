package com.jsm.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jsm.domain.enums.TipoCliente;
import com.jsm.dto.ClienteNewDTO;
import com.jsm.error.FieldMessage;
import com.jsm.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getCpfCnpj() == null) {
			list.add(new FieldMessage("CpfCnpj", "CPF ou CNPJ não pode ser vazio."));
		}
		if (dto.getTipo().equals(TipoCliente.PESSOA_FISICA.codigo)) {
			if (!BR.isValidCPF(dto.getCpfCnpj().replaceAll("[^0-9]", ""))) {
				list.add(new FieldMessage("CpfCnpj", "CFP Inválido"));
			}
		} else
		if (dto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.codigo)) {
			if (!BR.isValidCNPJ(dto.getCpfCnpj().replaceAll("[^0-9]", ""))) {
				list.add(new FieldMessage("CpfCnpj", "CNPJ Inválido"));
			}
		}else {
			//throw new RuntimeException("Tipo de Pessoa Inválido!");
			list.add(new FieldMessage("tipo", "Tipo de Pessoa Inválido!"));
		}

		for (FieldMessage fm : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fm.getFielMessage()).addPropertyNode(fm.getFieldName())
					.addConstraintViolation();

		}
		return list.isEmpty();
	}

}
