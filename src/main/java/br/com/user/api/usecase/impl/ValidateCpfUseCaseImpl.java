package br.com.user.api.usecase.impl;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.user.api.usecase.ValidateCpfUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ValidateCpfUseCaseImpl implements ValidateCpfUseCase {

    private static final String INVALID_CPF = "Invalid CPF";

    @Override
    public void execute(String cpf) {
        try{
            new CPFValidator().assertValid(cpf);
        } catch (InvalidStateException e){
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, INVALID_CPF);
        }
    }
}
