package Biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clasa FunctiiFisiere conține metode pentru manipularea fișierelor într-o bibliotecă.
 * Această clasă oferă funcționalități pentru salvarea și încărcarea datelor studenților, autorilor și cărților.
 */

public class FunctiiFisiere
{


    // METODE STUDENTI

    /**
     * Salvează o listă ArrayList de obiecte Serializable într-un fișier.
     *
     * @param numeFisier Numele fișierului în care să fie salvate datele.
     * @param data       Lista de obiecte de tipul T care urmează să fie salvate.
     * @param <T>        Tipul de obiecte care sunt Serializable.
     */

    public static <T extends Serializable> void saveFisier(String numeFisier, ArrayList<T> data){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(numeFisier))){
            oos.writeObject(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Încarcă o listă ArrayList de obiecte Serializable dintr-un fișier.
     *
     * @param numeFisier Numele fișierului de unde să fie încărcate datele.
     * @param <T>        Tipul de obiecte care sunt Serializable.
     * @return Lista de obiecte de tipul T.
     */

    public static <T extends Serializable> ArrayList<T> incarcadinFisier(String numeFisier) {
        ArrayList<T> data = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(numeFisier))) {
            data = (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Fisierul nu a fost gasit: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erroare pentru a citi din fisier: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Clasa nu a fost gasita: " + e.getMessage());
        }
        return data;
    }



    /**
     * Citește și returnează o listă de obiecte Student dintr-un fișier binar.
     *
     * @param numeFisier Numele fișierului binar de unde să fie încărcate datele.
     * @return Lista de obiecte Student.
     */

    public static ArrayList<Student> citireDinFisierBinar(String numeFisier) {
        ArrayList<Student> studentList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(numeFisier))) {
            studentList = (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    /**
     * Citește o listă de obiecte Student dintr-un fișier binar și o salvează într-un fișier ușor de citit.
     *
     * @param outputFile      Numele fișierului unde vor fi salvate datele.
     * @param binaryInputFile Numele fișierului binar de unde sunt citite datele.
     */

    public static void salvareInFisierCitibil(String outputFile, String binaryInputFile) {
        ArrayList<Student> studentList = citireDinFisierBinar(binaryInputFile);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (Student student : studentList) {
                bw.write(student.getId() + "," + student.getNume() + "," + student.getPrenume() + "," + student.getFacultate() + "," + student.getSpecializare());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // METODE AUTORI

    /**
     * Salvează detaliile fiecărui autor într-un format ușor de citit.
     *
     * @param outputFile      Numele fișierului unde vor fi salvate datele autorilor.
     * @param binaryInputFile Numele fișierului binar de unde sunt citite datele.
     */

    public static void salvareInFisierCitibilAutor(String outputFile, String binaryInputFile) {
        HashMap<Integer, Autor> autorMap = citireDinFisierBinarAutor(binaryInputFile);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (Autor autor : autorMap.values()) {
                bw.write(autor.getId() + "," + autor.getNume() + "," + autor.getPrenume());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Citește un HashMap de obiecte Autor dintr-un fișier binar.
     *
     * @param numeFisier Numele fișierului binar de unde să fie încărcate datele.
     * @return HashMap cu obiecte Autor.
     */

    public static HashMap<Integer, Autor> citireDinFisierBinarAutor(String numeFisier) {
        HashMap<Integer, Autor> autorMap = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(numeFisier))) {
            autorMap = (HashMap<Integer, Autor>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return autorMap;
    }


    /**
     * Salvează un HashMap de perechi cheie-valoare Serializable într-un fișier.
     *
     * @param numeFisier Numele fișierului în care să fie salvate datele.
     * @param data       HashMap-ul de perechi cheie-valoare care urmează să fie salvat.
     * @param <K>        Tipul cheii în HashMap.
     * @param <V>        Tipul valorii în HashMap.
     */

    public static <K extends Serializable, V extends Serializable> void saveFisierAutori(String numeFisier, HashMap<K, V> data){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(numeFisier))){
            oos.writeObject(data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Încarcă un HashMap de perechi cheie-valoare Serializable dintr-un fișier.
     *
     * @param numeFisier Numele fișierului de unde să fie încărcate datele.
     * @param <K>        Tipul cheii în HashMap.
     * @param <V>        Tipul valorii în HashMap.
     * @return HashMap-ul încărcat cu perechi cheie-valoare.
     */

    public static <K extends Serializable, V extends Serializable> HashMap<K, V> incarcadinFisierAutori(String numeFisier) {
        HashMap<K, V> data = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(numeFisier))) {
            data = (HashMap<K, V>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Fisierul nu a fost gasit: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erroare pentru a citi din fisier: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Clasa nu a fost gasita: " + e.getMessage());
        }
        return data;
    }

// METODE CARTE

    /**
     * Citește o listă de obiecte Carte dintr-un fișier binar.
     *
     * @param numeFisier Numele fișierului binar de unde să fie încărcate datele.
     * @return Lista de obiecte Carte.
     */

    public static ArrayList<Carte> citireDinFisierBinarCarte(String numeFisier) {
        ArrayList<Carte> carteList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(numeFisier))) {
            carteList = (ArrayList<Carte>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return carteList;
    }

    /**
     * Citește o listă de obiecte Carte dintr-un fișier binar și le scrie într-un fișier ușor de citit.
     *
     * @param outputFile      Numele fișierului unde vor fi salvate datele cărților.
     * @param binaryInputFile Numele fișierului binar de unde sunt citite datele.
     */

    public static void salvareInFisierCitibilCarte(String outputFile, String binaryInputFile) {
        ArrayList<Carte> carteList = citireDinFisierBinarCarte(binaryInputFile);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (Carte carte : carteList) {
                bw.write(carte.getId() + "," + carte.getTitlu() + "," + carte.getIsbn() + "," + carte.getAutor().getNume() + " " + carte.getAutor().getPrenume() + "," + carte.getCollection());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}

