package repository;

import enums.EnumSexo;
import models.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BancoDadosTest {
	private BancoDados bd = new BancoDados();

	@BeforeEach
	void init() {
		bd.clearData();
		Pessoa p1 = new Pessoa("Pessoa1", 18, EnumSexo.FEMININO);
		Pessoa p2 = new Pessoa("Pessoa2", 15, EnumSexo.MASCULINO);
		Pessoa p3 = new Pessoa("Pessoa3", 31, EnumSexo.FEMININO);
		Pessoa p4 = new Pessoa("Pessoa4", 10, EnumSexo.FEMININO);
		bd.add(p1);
		bd.add(p2);
		bd.add(p3);
		bd.add(p4);

	}

	@Test
	@DisplayName("Teste listar todos")
	void testListAll() {
		List<Pessoa> cads = bd.listAll();
		assertEquals(4, cads.size());
		assertEquals("Pessoa1", cads.get(0).getNome());
		bd.add(new Pessoa("Pessoa5", 30, EnumSexo.MASCULINO));
		cads = bd.listAll();
		assertEquals(5, cads.size());
		assertEquals(5, cads.get(4).getId());

	}

	@Test
	@DisplayName("Teste findById")
	void testFindById() {
		Pessoa cad = bd.findById(2);
		assertNotNull(cad);
		assertEquals("Pessoa2", cad.getNome());
		assertEquals(15, cad.getIdade());
		assertEquals(EnumSexo.MASCULINO, cad.getSexo());
	}
	@Test
	@DisplayName("Teste findById que não existe")
	void testFindByIdWithoutId() {
		Pessoa p = bd.findById(7);
		assertEquals(null, p);
	}
	  @Test
	    @DisplayName("Teste add")
	    void testAdd() {
	        Pessoa p5 = new Pessoa("Pessoa5", 20, EnumSexo.MASCULINO);
	        Pessoa result = bd.add(p5);
	        assertEquals(5, result.getId());
	        assertEquals(p5, result);
	    }
	@Test
	@DisplayName("Teste delete")
	void testDelete() {
		bd.delete(4);
		Pessoa cad = bd.findById(4);
		
		assertNull(cad);
	}

	@Test
	@DisplayName("Teste update")
	void testUpdate() {
		Pessoa p3 = new Pessoa("Pessoa3 Atualizada", 35, EnumSexo.MASCULINO);
        Pessoa cad = bd.update(3, p3);
        assertNotNull(cad);
        assertEquals(3, cad.getId());
        assertEquals(p3, cad);
	}
	@Test
	@DisplayName("Teste update pessoa que não existe")
	void testUpdateNaoExiste() {
		Pessoa p3 = new Pessoa("Pessoa3 Atualizada", 35, EnumSexo.MASCULINO);
        Pessoa cad = bd.update(3, p3);
        assertNotNull(cad);
        assertEquals(3, cad.getId());
        assertEquals(p3, cad);

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
