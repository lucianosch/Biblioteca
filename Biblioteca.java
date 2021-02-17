import java.util.*;
import java.io.*;
import com.google.gson.*;
/**
 * Gestisce una collezione di Libri
 * Consente l'inserimento di un nuovo libro e
 * la stampa del catalogo anche per genere
 * utilizza un file di testo in cui
 * memorizzare i libri come oggetti in formato JSON
 *
 * @author Luciano Schiavone
 * @version 22.01.2020
 */
public class Biblioteca
{
    private LinkedList<Libro> biblio;
    public Biblioteca()
    {
        biblio = new LinkedList();
        //caricaTxt();
        //caricaDat();
        caricaJSON();
    }
    private void caricaTxt()
    {
        File f = new File("Libri.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int nlibri = Integer.parseInt(br.readLine());
            for (int i = 0; i<nlibri; i++)
            {
                String t = br.readLine();
                String gen = br.readLine();
                int n = Integer.parseInt(br.readLine());
                Libro l = new Libro(i,t,n,gen);
                int naut = Integer.parseInt(br.readLine());
                for (int j=0; j<naut; j++)
                {
                    String a = br.readLine();
                    l.addAutore(a);
                }
                biblio.add(l);
            }
        }
        catch (Exception e){}
    }
    public int getSize()
    {
        return biblio.size();
    }
    public void add(Libro l)
    {
        biblio.add(l);
    }
    public void stampa(String genere)
    {
        ListIterator it = biblio.listIterator();
        while (it.hasNext())
        {
            Libro l = (Libro)it.next();
            if (genere.length()==0 || genere.equals(l.getGenere()))
                System.out.println(l.toString());
        }
    }
    public void stampaPerTitolo()
    {
        Collections.sort(biblio, new PerTitolo());
        this.stampa("");
        Collections.sort(biblio);
    }
    public void salvaDat()
    {
        try
        {
            FileOutputStream f = new FileOutputStream("Libri.dat");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            ListIterator it = biblio.listIterator();
            while (it.hasNext())
            {
                Libro l = (Libro)it.next();
                oos.writeObject(l);
            }
            f.close();
        }
        catch(FileNotFoundException fnfe){System.out.println("Errore: file non trovato");}
        catch(IOException ioe){System.out.println("Errore generico di I/O");}
    }

    private void caricaDat()
    {
        try
        {
            FileInputStream f = new FileInputStream("Libri.dat");
            ObjectInputStream ois = new ObjectInputStream(f);
            Libro l=(Libro)ois.readObject();
            while (l!=null)
            {
                biblio.add(l);
                //System.out.println(l);
                l = (Libro)ois.readObject();
            }
            f.close();
        }
        catch(ClassNotFoundException cnfe){}
        //catch(FileNotFoundException fnfe){}
        catch(IOException ioe){}
    }

    public void salvaJSON()
    {
        try
        {
            FileWriter f = new FileWriter("Libri.json");
            BufferedWriter bw = new BufferedWriter(f);
            ListIterator it = biblio.listIterator();
            while (it.hasNext())
            {
                Libro l = (Libro)it.next();
                //oos.writeObject(l);
                bw.write(objToJson(l)+"\n");
                //System.out.println(objToJson(l));
            }
            bw.close();
        }
        catch(FileNotFoundException fnfe){}
        catch(IOException ioe){}
    }
    private void caricaJSON()
    {
        File f = new File("Libri.json");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            while (s != null)
            {
                Libro l = (Libro) jsonToObj(s, Libro.class);
                biblio.add(l);
                s = br.readLine();
            }
            br.close();
        }
        catch (Exception e){}
    }
    private String objToJson(Object f) {
        Gson gson = new Gson();
        String strJson = gson.toJson(f);
        //System.out.println(strJson);
        return strJson;
    }

    private Object jsonToObj(String strJson, Class c) {
        Gson gson = new Gson();
        //Film film = gson.fromJson(strJson, Film.class);
        //System.out.println(film.getTitolo() + ", " + film.getAnno());
        return gson.fromJson(strJson,c);
    }
}
