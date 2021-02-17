import java.io.*;
import java.util.Collections;
/**
 * Gestione di una raccolta di Libri (Biblioteca)
 * con un'interfaccia testuale a menu che permette:
 * 1) l'inserimento di un nuovo libro
 * 2) la visualizzazione dell'elenco di libri (ordinati per titolo)
 * 3) la visualizzazione di un elenco di libri riguardanti un
 *    dato argomento (se campo vuoto li stampa tutti)
 * 
 * @author Luciano Schiavone
 * @version 22.01.2020
 */
public class GestBiblio
{
    public static void main(String args[]) throws IOException
    {
        Biblioteca biblio = new Biblioteca();
        int scelta;
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
        do
        {
            menu();
            System.out.print("Scelta: ");
            scelta = Integer.parseInt(tastiera.readLine());
            switch (scelta)
            {
                case 1: 
                {
                    System.out.print("Titolo: ");
                    String t = tastiera.readLine();
                    System.out.print("Genere: ");
                    String gen = tastiera.readLine();
                    System.out.print("N. pagine: ");
                    int n = Integer.parseInt(tastiera.readLine());
                    Libro l = new Libro(biblio.getSize(),t,n,gen);
                    System.out.print("Aggiungi autore: ");
                    String a = tastiera.readLine();
                    l.addAutore(a);
                    do
                    {
                        System.out.print("Aggiungi autore: ");
                        a = tastiera.readLine();
                        if (a.length()>0)
                            l.addAutore(a);
                    }while (a.length()>0);
                    biblio.add(l);
                    break;
                }
                case 2:
                {
                    biblio.stampaPerTitolo();
                    break;
                }
                case 3:
                {
                    System.out.print("Genere: ");
                    String arg = tastiera.readLine();
                    biblio.stampa(arg);
                    break;
                }
                case 0:
                {
                    biblio.salvaJSON();
                    break;
                }
                default:
                {
                    System.out.println("Scelta errata");
                    break;
                }
            }
        }while (scelta!=0);
    }
    private static void menu()
    {
        System.out.println("1. Inserimento");
        System.out.println("2. Stampa in ordine (per Titolo)");
        System.out.println("3. Stampa elenco per genere");
        System.out.println("0. Uscita");
    }
}
