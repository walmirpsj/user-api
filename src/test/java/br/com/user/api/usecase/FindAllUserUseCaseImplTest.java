package br.com.user.api.usecase;

import br.com.user.api.build.UserDataTestBuilder;
import br.com.user.api.build.TemplateLoaderUtil;
import br.com.user.api.usecase.gateway.UserGateway;
import br.com.user.api.usecase.impl.FindAllUserUseCaseImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.user.api.build.ExampleType.VALID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindAllUserUseCaseImplTest {

    @InjectMocks
    private FindAllUserUseCaseImpl findUserUseCase;
    @Mock
    private UserGateway userGateway;

    @BeforeAll
    public static void setUp() {
        TemplateLoaderUtil.load();
    }

    @Test
    public void shouldExecuteFindAll(){
        when(userGateway.findAll()).thenReturn(UserDataTestBuilder.get(2, VALID));

        final var response = findUserUseCase.execute();

        assertNotNull(response);
        assertNotNull(response);
        assertTrue(response.size() > 0);

        verify(userGateway, atLeastOnce()).findAll();
    }

}
