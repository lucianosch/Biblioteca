import java.util.*;
import java.io.Serializable;
public class Libro implements Comparable<Libro>, Serializable
{
    private int cod;
    private String titolo;
    private ArrayList<String> autori;
    private int npagine;
    private String genere;
    public Libro(int k, String titolo, int npagine, String genere)
    {
        autori = new ArrayList<String>();
        this.cod = k;
        this.titolo = titolo;
        this.npagine = npagine;
        this.genere = genere;
    }
    public String getTitolo()
    {
        return titolo;
    }
    public int getCod()
    {
        return cod;
    }
    public String getGenere()
    {
        return genere;
    }
    public void addAutore(String autore)
    {
        autori.add(autore);
    }
    public int compareTo(Libro l)
    {
        return cod - l.getCod();
    }
    public String toString()
    {
        String s = autori + " - " +titolo + " ("+genere+" - p. "+npagine+")";
        return s;
    }
}
