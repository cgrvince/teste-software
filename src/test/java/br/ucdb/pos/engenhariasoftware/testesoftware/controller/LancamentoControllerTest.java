package br.ucdb.pos.engenhariasoftware.testesoftware.controller;

import br.ucdb.pos.engenhariasoftware.testesoftware.modelo.Lancamento;
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class LancamentoControllerTest {


    /*
    * Buscando o valor mínimo da lista com JAVA
    *
    * */
    @Test
    public void buscandoComPostTest() {
        Response response = given()
                .when()
                .body("Assured")
                .post("/buscaLancamentos");

        assertEquals(response.getStatusCode(), 200);

        InputStream in = response.asInputStream();
        List<Lancamento> list = JsonPath.with(in)
                .getList("lancamentos", Lancamento.class);
        assertEquals(list.size(), 3);

        BigDecimal menorValor = BigDecimal.ZERO;
        System.out.println(menorValor);

        int i =0;
        for(Iterator li = list.iterator(); li.hasNext(); li.next(), i++)
        {
            Lancamento l = (Lancamento) li.next();
            BigDecimal bd = l.getValor();

            if(menorValor.compareTo(bd) == (-1))
            {
                menorValor = bd;
            }
        }

        assertEquals(menorValor, 2.50);
        //assertEquals(i, 3);
    }
}
