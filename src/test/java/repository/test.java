import static org.junit.jupiter.api.Assertions.*;
import enums.EnumSexo;
import models.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.BancoDados;
import java.util.List;

public class test {

    private BancoDados bd;

    @BeforeEach
    void init() {
        bd = new BancoDados();
        bd.clearData();
        Pessoa p1 = new Pessoa("Pessoa1", 18, EnumSexo.FEMININO);
        Pessoa p2 = new Pessoa("Pessoa2", 15, EnumSexo.FEMININO);
        Pessoa p3 = new Pessoa("Pessoa3", 31, EnumSexo.FEMININO);
        Pessoa p4 = new Pessoa("Pessoa4", 10, EnumSexo.FEMININO);
        bd.add(p1);
        bd.add(p2);
        bd.add(p3);
        bd.add(p4);
    }

    @Test
    @DisplayName("Test add")
    void testAdd() {
        Pessoa p5 = new Pessoa("Pessoa5", 20, EnumSexo.MASCULINO);
        Pessoa result = bd.add(p5);
        assertEquals(5, result.getId());
        assertEquals(p5, result);
    }

    @Test
    @DisplayName("Test findById")
    void testFindById() {
        Pessoa result = bd.findById(2);
        assertNotNull(result);
        assertEquals("Pessoa2", result.getNome());
        assertEquals(15, result.getIdade());
        assertEquals(EnumSexo.FEMININO, result.getSexo());
    }

    @Test
    @DisplayName("Test update")
    void testUpdate() {
        Pessoa p3 = new Pessoa("Pessoa3 Atualizada", 35, EnumSexo.MASCULINO);
        Pessoa result = bd.update(3, p3);
        assertNotNull(result);
        assertEquals(3, result.getId());
        assertEquals(p3, result);
    }

    @Test
    @DisplayName("Test delete")
    void testDelete() {
        bd.delete(4);
        Pessoa result = bd.findById(4);
        assertNull(result);
    }

    @Test
    @DisplayName("Test listAll")
    void testListAll() {
        List<Pessoa> result = bd.listAll();
        assertEquals(4, result.size());
    }

    @Test
    @DisplayName("Test findByIdadeBetween")
    void testFindByIdadeBetween() {
        List<Pessoa> result = bd.findByIdadeBetween(10, 20);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(p -> p.getIdade() >= 10 && p.getIdade() <= 20));
    }

    @Test
    @DisplayName("Test findBySexo")
    void testFindBySexo() {
        List<Pessoa> result = bd.findBySexo(EnumSexo.FEMININO);
        assertEquals(4, result.size());
        assertTrue(result.stream().allMatch(p -> p.getSexo() == EnumSexo.FEMININO));
    }

    @Test
    @DisplayName("Test clearData")
    void testClearData() {
        bd.clearData();
        List<Pessoa> result = bd.listAll();
        assertEquals(0, result.size());
    }
}