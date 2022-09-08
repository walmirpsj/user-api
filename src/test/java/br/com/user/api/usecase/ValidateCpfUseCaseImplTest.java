package br.com.user.api.usecase;

import br.com.user.api.usecase.impl.ValidateCpfUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ValidateCpfUseCaseImplTest {

    @InjectMocks
    private ValidateCpfUseCaseImpl validateCpfUseCase;

    @Test
    public void shouldValidateCpf(){
        final var cpf = "83581285096";
        validateCpfUseCase.execute(cpf);
        assertNotNull(cpf);
    }

    @Test
    public void shouldThrowExceptionToValidateCpf(){
        final var exception = assertThrows(
                HttpClientErrorException.class,
                () -> validateCpfUseCase.execute("99999999999"));
        assertNotNull(exception, exception.getMessage());
    }
}
