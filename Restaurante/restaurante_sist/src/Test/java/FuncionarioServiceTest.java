import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.Controller.FuncionarioController;
import com.example.Model.Funcionario;
import com.example.Service.FuncionarioService;

public class FuncionarioServiceTest {

    @Test
    public void testCadastrarFuncionarioComSucesso() {
        // Criar um mock do FuncionarioController
        FuncionarioController mockController = Mockito.mock(FuncionarioController.class);

        // Criar um exemplo de funcionário válido
        Funcionario funcionario = new Funcionario("João Silva", "Gerente", "15/08/2023", 3500.00);

        // Simular o comportamento do mock para o método cadastrarFuncionario
        when(mockController.cadastrarFuncionario(funcionario)).thenReturn(true);

        // Criar o serviço e passar o mock do controller
        FuncionarioService funcionarioService = new FuncionarioService(mockController);

        // Chamar o método e verificar o resultado
        boolean resultado = funcionarioService.cadastrarFuncionario(funcionario);

        // Verificar se o resultado foi bem-sucedido
        assertTrue(resultado);

        // Verificar se o método cadastrarFuncionario do controller foi chamado
        verify(mockController).cadastrarFuncionario(funcionario);
    }

    @Test
    public void testCadastrarFuncionarioComNomeVazio() {
        FuncionarioController mockController = Mockito.mock(FuncionarioController.class);
        Funcionario funcionario = new Funcionario("", "Gerente", "15/08/2023", 3500.00);

        FuncionarioService funcionarioService = new FuncionarioService(mockController);

        // Espera-se que o serviço rejeite o funcionário com nome vazio
        boolean resultado = funcionarioService.cadastrarFuncionario(funcionario);

        // Nome vazio deve falhar
        assertFalse(resultado);
        
        // O controller não deve ser chamado
        verify(mockController, never()).cadastrarFuncionario(funcionario);
    }

    @Test
    public void testCadastrarFuncionarioComSalarioNegativo() {
        FuncionarioController mockController = Mockito.mock(FuncionarioController.class);
        Funcionario funcionario = new Funcionario("João Silva", "Gerente", "15/08/2023", -1500.00);

        FuncionarioService funcionarioService = new FuncionarioService(mockController);

        // Espera-se que o serviço rejeite o funcionário com salário negativo
        boolean resultado = funcionarioService.cadastrarFuncionario(funcionario);

        // Salário negativo deve falhar
        assertFalse(resultado);
        
        // O controller não deve ser chamado
        verify(mockController, never()).cadastrarFuncionario(funcionario);
    }
}
