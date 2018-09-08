package br.ucdb.pos.engenhariasoftware.testesoftware.controller;

import br.ucdb.pos.engenhariasoftware.testesoftware.modelo.Categoria;
import br.ucdb.pos.engenhariasoftware.testesoftware.modelo.Lancamento;
import br.ucdb.pos.engenhariasoftware.testesoftware.modelo.TipoLancamento;
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class LancamentoControllerTest {

    public ArrayList<Lancamento> popularBase(int tipo)
    {
        ArrayList<Lancamento> lista = new ArrayList<Lancamento>();

        switch(tipo)
        {

            case 1:
                    Lancamento l = new Lancamento("Gasto1", BigDecimal.valueOf(20.00), new Date("20/04/2018"),
                            TipoLancamento.SAIDA, Categoria.ALIMENTACAO);
                    lista.add(l);
                break;

            case 2:
                Lancamento l1 = new Lancamento("Provento1", BigDecimal.valueOf(1320.00), new Date("20/04/2018"),
                        TipoLancamento.ENTRADA, Categoria.INVESTIMENTOS);
                Lancamento l2 = new Lancamento("Provento2", BigDecimal.valueOf(97.00), new Date("22/04/2018"),
                        TipoLancamento.ENTRADA, Categoria.INVESTIMENTOS);
                lista.add(l1);
                lista.add(l2);
                break;

            case 3:
                l1 = new Lancamento("Gasto1", BigDecimal.valueOf(10.00), new Date("20/04/2018"),
                        TipoLancamento.SAIDA, Categoria.ALIMENTACAO);
                l2 = new Lancamento("Gasto2", BigDecimal.valueOf(34.00), new Date("22/04/2018"),
                        TipoLancamento.SAIDA, Categoria.ALIMENTACAO);
                lista.add(l1);
                lista.add(l2);
                break;

            default:
                l1 = new Lancamento("Gasto1", BigDecimal.valueOf(101.00), new Date("20/04/2018"),
                        TipoLancamento.SAIDA, Categoria.ALIMENTACAO);
                l2 = new Lancamento("Provento1", BigDecimal.valueOf(380.00), new Date("22/04/2018"),
                        TipoLancamento.ENTRADA, Categoria.INVESTIMENTOS);
                Lancamento l3 = new Lancamento("Gasto2", BigDecimal.valueOf(48.50), new Date("06/01/2018"),
                        TipoLancamento.SAIDA, Categoria.ALIMENTACAO);
                Lancamento l4 = new Lancamento("Provento2", BigDecimal.valueOf(11.00), new Date("13/07/2018"),
                        TipoLancamento.ENTRADA, Categoria.INVESTIMENTOS);
                Lancamento l5 = new Lancamento("Provento3", BigDecimal.valueOf(248.00), new Date("27/09/2018"),
                        TipoLancamento.ENTRADA, Categoria.INVESTIMENTOS);
                break;

        }

        return lista;
    }
    /*
    * Buscando o valor mínimo da lista com JAVA
    *
    * */
    @Test
    public void buscandoComListaTamanho1() {

        popularBase(1);

        Response response = given()
                .when()
                .body("Assured")
                .post("/buscaLancamentos");

        assertEquals(response.getStatusCode(), 200);

        InputStream in = response.asInputStream();
        List<Lancamento> list = JsonPath.with(in)
                .getList("lancamentos", Lancamento.class);
        assertEquals(list.size(), 1);

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

        assertEquals(menorValor, 20.00);
        //assertEquals(i, 3);
    }

    public void buscandoComListaTamanho2Positivo() {

        popularBase(2);

        Response response = given()
                .when()
                .body("Assured")
                .post("/buscaLancamentos");

        assertEquals(response.getStatusCode(), 200);

        InputStream in = response.asInputStream();
        List<Lancamento> list = JsonPath.with(in)
                .getList("lancamentos", Lancamento.class);
        assertEquals(list.size(), 2);

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

        assertEquals(menorValor, 97.00);
    }

    public void buscandoComListaTamanho2Negativo() {

        popularBase(3);

        Response response = given()
                .when()
                .body("Assured")
                .post("/buscaLancamentos");

        assertEquals(response.getStatusCode(), 200);

        InputStream in = response.asInputStream();
        List<Lancamento> list = JsonPath.with(in)
                .getList("lancamentos", Lancamento.class);
        assertEquals(list.size(), 2);

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

        assertEquals(menorValor, 10.00);
    }

    public void buscandoComListaTamanho5() {

        popularBase(4);

        Response response = given()
                .when()
                .body("Assured")
                .post("/buscaLancamentos");

        assertEquals(response.getStatusCode(), 200);

        InputStream in = response.asInputStream();
        List<Lancamento> list = JsonPath.with(in)
                .getList("lancamentos", Lancamento.class);
        assertEquals(list.size(), 5);

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

        assertEquals(menorValor, 11.00);
    }
}
