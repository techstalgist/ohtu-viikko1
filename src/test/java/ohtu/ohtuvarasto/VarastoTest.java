package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisellaTilavuudellaKayttokelvotonVarasto() {
        Varasto kelvoton = new Varasto(-10);
        
        assertEquals(0, kelvoton.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uusiVarastoTilavuuttaPienemmälläAlkusaldolla() {
        Varasto v = new Varasto(10, 5);
        
        assertEquals(10, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uusiVarastoTilavuuttaSuuremmallaAlkusaldolla() {
        Varasto v = new Varasto(10, 12);
        
        assertEquals(10, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisellaTilavuudellaJaAlkusaldollaKayttokelvotonVarasto() {
        Varasto v = new Varasto(-10, -8);
        
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-8);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoEiYlitaTilavuutta() {
        varasto.lisaaVarastoon(15);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoPalauttaaNollanSaatunaMaarana() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(-8);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void josOtettavaMaaraYliSaldonOtetaanKaikkiMitaVoidaan() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(9);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);        
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    
    public void oikeaMerkkijonoesitys() {
        varasto.lisaaVarastoon(8);
        
        String tekstina = varasto.toString();
        assertEquals(tekstina, "saldo = 8.0, vielä tilaa 2.0");
    }

}