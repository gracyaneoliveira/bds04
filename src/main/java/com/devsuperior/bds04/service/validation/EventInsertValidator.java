package com.devsuperior.bds04.service.validation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.devsuperior.bds04.controllers.exceptions.FieldMessage;
import com.devsuperior.bds04.dto.EventInsertDTO;

public class EventInsertValidator implements ConstraintValidator<EventInsertValid, EventInsertDTO> {

	@Override
	public void initialize(EventInsertValid ann) {
	}

	@Override
	public boolean isValid(EventInsertDTO value, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		boolean isValid = value.getDate().isAfter(LocalDate.now(ZoneId.systemDefault()));

		if (!isValid) {
			list.add(new FieldMessage("date", "A data do evento não pode ser passada"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
