package main;

import ohtu.ohtuvarasto.Varasto;

public class Main {
    private static final double TILAVUUS = 100.0;
    private static final double ALKUSALDO = 20.2;
    private static final double LISAYS = 1000.0;
    
    public static void main(String[] args) {
        
        Varasto olutta = new Varasto(TILAVUUS, ALKUSALDO);

        System.out.println("Olutvarasto: " + olutta);
        System.out.println("olutta.lisaaVarastoon(1000.0)");
        olutta.lisaaVarastoon(LISAYS);
        System.out.println("Olutvarasto: " + olutta);
    }
}
