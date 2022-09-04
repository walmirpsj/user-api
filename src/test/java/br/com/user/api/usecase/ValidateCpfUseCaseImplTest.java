package br.com.user.api.usecase;

import br.com.user.api.usecase.impl.ValidateCpfUseCaseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ValidateCpfUseCaseImplTest {

    @InjectMocks
    private ValidateCpfUseCaseImpl validateCpfUseCase;

    @Test
    public void shouldValidateCpf(){
        final var cpf = "83581285096";
        validateCpfUseCase.execute(cpf);
        assertNotNull(cpf);
    }

    @Test(expected = HttpClientErrorException.class)
    public void shouldThrowExceptionToValidateCpf(){
        validateCpfUseCase.execute("99999999999");
    }
}
