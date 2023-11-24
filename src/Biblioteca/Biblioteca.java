package Biblioteca;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * Clasa Biblioteca conține metode pentru gestionarea studenților, autorilor și cărților într-o bibliotecă.
 * Aceasta clasă oferă interfața pentru interacțiunea cu diferite entități ale bibliotecii.
 */

import static Biblioteca.FunctiiFisiere.*;

class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.Menu();
    }
}

public class Biblioteca {

    private static final String STUDENTI_FILE = "studenti.txt";
    private static final String FIȘIER_CITIBIL = "fisier_citibil.txt";

    private static final String AUTORI_FILE = "autori.txt";
    private static final String FIȘIER_CITIBIL_AUTOR = "fisier_citibil_autori.txt";

    private static final String CARTE_FILE = "cartii.txt";
    private static final String FISIER_CITIBIL_CARTE = "fisier_citibil_carte.txt";

    private ArrayList<Student> studentList;
    private HashMap<Integer, Autor> autorMap;
    private ArrayList<Carte> carteList;

    public Biblioteca() {
        studentList = new ArrayList<>();
        autorMap = new HashMap<>();
        carteList = new ArrayList<>();

        studentList = incarcadinFisier(STUDENTI_FILE);
        autorMap = incarcadinFisierAutori(AUTORI_FILE);
        carteList = incarcadinFisier(CARTE_FILE);
    }

    /**
     * Meniul principal al aplicației.
     * Permite utilizatorului să selecteze și să execute diferite operații de gestionare a bibliotecii.
     */

    public void Menu() {
        Scanner s = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Sistemul de management al bibliotecii ---");
            System.out.println("1. Gestionați studenții!");
            System.out.println("2. Gestionați autorii!");
            System.out.println("3. Gestionați cartiile!");
            System.out.println("0. Iesire!");
            System.out.print("Introduceți opțiunea: ");

            int option = s.nextInt();
            switch (option) {
                case 1:
                    gestioneazăStudenții();
                    break;
                case 2:
                    gestioneazăAutorii();
                    break;
                case 3:
                    gestioneazăCartiile();
                    break;
                case 0:
                    running = false;
                    System.out.println("Iesire...");
                    break;
                default:
                    System.out.println("Opțiune nevalida! Vă rugam să introduceți o opțiune valida.");
            }
        }
        s.close();
    }

    /**
     * Gestionează operațiile legate de studenți.
     * Permite adăugarea, afișarea, actualizarea și ștergerea studenților din bibliotecă.
     */

    private void gestioneazăStudenții() {
        Scanner s = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.println("\n--- Managementul Studenților ---");
            System.out.println("1. Adauga un student in registrul biblioteci");
            System.out.println("2. Arata studentii din biblioteca");
            System.out.println("3. Actualizează un student din registrul biblioteci");
            System.out.println("4. Sterge un student din registrul biblioteci");
            System.out.println("5. Inapoi la meniul principal");
            System.out.print("Alege o optiune: ");

            int option = s.nextInt();
            s.nextLine();

            switch (option) {
                case 1:
                    addStudent(s);
                    saveFisier(STUDENTI_FILE, studentList);
                    salvareInFisierCitibil(FIȘIER_CITIBIL, STUDENTI_FILE);
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    updateStudent(s);
                    saveFisier(STUDENTI_FILE, studentList);
                    salvareInFisierCitibil(FIȘIER_CITIBIL, STUDENTI_FILE);
                    break;
                case 4:
                    deleteStudent(s);
                    saveFisier(STUDENTI_FILE, studentList);
                    salvareInFisierCitibil(FIȘIER_CITIBIL, STUDENTI_FILE);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }

    /**
     * Gestionează operațiile legate de autori.
     * Permite adăugarea, afișarea, actualizarea și ștergerea autorilor din bibliotecă.
     */

    private void gestioneazăAutorii() {
            Scanner s = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n--- Managementul Autorilor ---");
                System.out.println("1. Adauga un autor in registrul biblioteci");
                System.out.println("2. Arata autori din biblioteca");
                System.out.println("3. Actualizeaza un autor din registrul biblioteci");
                System.out.println("4. Sterge un autor din registrul biblioteci");
                System.out.println("5. Inapoi la meniul principal");
                System.out.print("Alege o optiune: ");

                int option = s.nextInt();
                s.nextLine();

                switch (option) {
                    case 1:
                        addAutor(s);
                        saveFisierAutori(AUTORI_FILE, autorMap);
                        salvareInFisierCitibilAutor(FIȘIER_CITIBIL_AUTOR, AUTORI_FILE);
                        break;
                    case 2:
                        displayAutori();
                        break;
                    case 3:
                        updateAutor(s);
                        saveFisierAutori(AUTORI_FILE, autorMap);
                        salvareInFisierCitibilAutor(FIȘIER_CITIBIL_AUTOR, AUTORI_FILE);
                        break;
                    case 4:
                        deleteAutor(s);
                        saveFisierAutori(AUTORI_FILE, autorMap);
                        salvareInFisierCitibilAutor(FIȘIER_CITIBIL_AUTOR, AUTORI_FILE);
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Optiune invalida!");
                }
            }
    }

    /**
     * Gestionează operațiile legate de cărți.
     * Permite adăugarea, afișarea, actualizarea și ștergerea cărților din bibliotecă,
     * precum și afișarea cărților după autor, colecție sau data împrumutului.
     */

    private void gestioneazăCartiile() {
        Scanner s = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.println("\n--- Managementul Cartilor ---");
            System.out.println("1. Adauga o carte in biblioteca");
            System.out.println("2. Arată cartile din biblioteca");
            System.out.println("3. Actualizeaza o carte din biblioteca");
            System.out.println("4. Șterge o carte din biblioteca");
            System.out.println("5. Cartile apartinand unui anumit autor");
            System.out.println("6. Cartile dintr-o anumita colecție");
            System.out.println("7. Cartile imprumutate dupa o anumita data");
            System.out.println("8. Inapoi la meniul principal");
            System.out.print("Alege o opțiune: ");

            int option = s.nextInt();
            s.nextLine();

            switch (option) {
                case 1:
                    adaugăCarte(s);
                    saveFisier(CARTE_FILE, carteList);
                    salvareInFisierCitibilCarte(FISIER_CITIBIL_CARTE, CARTE_FILE);
                    break;
                case 2:
                    aratăCartile();
                    saveFisier(CARTE_FILE, carteList);
                    salvareInFisierCitibilCarte(FISIER_CITIBIL_CARTE, CARTE_FILE);
                    break;
                case 3:
                    actualizeazăCarte(s);
                    saveFisier(CARTE_FILE, carteList);
                    salvareInFisierCitibilCarte(FISIER_CITIBIL_CARTE, CARTE_FILE);
                    break;
                case 4:
                    ștergeCarte(s);
                    saveFisier(CARTE_FILE, carteList);
                    salvareInFisierCitibilCarte(FISIER_CITIBIL_CARTE, CARTE_FILE);
                    break;
                case 5:
                    cartileAparținândUnuiAnumitAutor(s);
                    saveFisier(CARTE_FILE, carteList);
                    salvareInFisierCitibilCarte(FISIER_CITIBIL_CARTE, CARTE_FILE);
                    break;
                case 6:
                    cartileDintrOAnumităColecție(s);
                    saveFisier(CARTE_FILE, carteList);
                    salvareInFisierCitibilCarte(FISIER_CITIBIL_CARTE, CARTE_FILE);
                    break;
                case 7:
                    listaCartilorImprumutateDupaData(s);
                    saveFisier(CARTE_FILE, carteList);
                    salvareInFisierCitibilCarte(FISIER_CITIBIL_CARTE, CARTE_FILE);
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Opțiune invalidă!");
            }
        }
    }

    /**
     * Adaugă o nouă carte în bibliotecă.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */

    public void adaugăCarte(Scanner s){
        System.out.println("Introduceti ID-ul cartii: ");
        int id = s.nextInt();
        s.nextLine();

        System.out.print("Introduceți titlul cartii: ");
        String titlu = s.nextLine();

        System.out.print("Introduceți ISBN-ul cartii: ");
        String isbn = s.nextLine();

        System.out.print("Introduceți numele autorului cartii: ");
        String autorNume = s.nextLine();

        Autor autor = gasireAutorDupaNume(autorNume);
        if(autor == null){
            System.out.println("Autorul cu numele " + autorNume + " nu exista. Adaugati autorul mai intai.");
        }

        System.out.print("Introduceți colecția cartii: ");
        String collection = s.nextLine();

        System.out.print("Este cartea imprumutata? (da/nu): ");
        String isBorrowed = s.nextLine().toLowerCase();

        LocalDate borrowedDate = null;

        if("da".equals(isBorrowed)){
            System.out.print("Introduceti data cartii imprumutate (format yyyy-MM-dd): ");
            String dateInput = s.nextLine();

            try {
                borrowedDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e){
                System.out.println("Data introdusa " + dateInput + " este invalida. Incercati din nou.");
                return;
            }
        }

        Carte carte = new Carte(id, titlu, isbn, autor, collection, borrowedDate);
        carteList.add(carte);

    }

    /**
     * Găsește un autor în bibliotecă pe baza numelui.
     *
     * @param nume Numele autorului căutat.
     * @return Autorul dacă este găsit, altfel null.
     */

    private Autor gasireAutorDupaNume(String nume) {
        for (Autor autor : autorMap.values()) {
            if (autor.getNume().equalsIgnoreCase(nume)) {
                return autor;
            }
        }
        return null;
    }

    /**
     * Afișează toate cărțile din bibliotecă.
     */

    public void aratăCartile(){
        for (Carte carte:carteList){
            System.out.println(carte);
        }
    }

    /**
     * Actualizează informațiile unei cărți existente.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */

    public void actualizeazăCarte(Scanner s) {
        System.out.print("Introduceți titlu cartii pe care doriți să o modificați: ");
        String titluCarte = s.nextLine();

        Carte cartegasita = null;
        for (Carte carte : carteList) {
            if (titluCarte.equals(carte.getTitlu())) {
                cartegasita = carte;
                break;
            }
        }

        if (cartegasita != null) {
            System.out.print("Introduceți noul titlu (lasati gol pentru a pastra valoarea curenta): ");
            String newTitlu = s.nextLine();
            if (!newTitlu.isEmpty()) {
                cartegasita.setTitlu(newTitlu);
            }

            System.out.print("Introduceți noul ISBN (lasati gol pentru a pastra valoarea curenta):  ");
            String newIsbn = s.nextLine();
            if (!newIsbn.isEmpty()) {
                cartegasita.setIsbn(newIsbn);
            }

            System.out.print("Introduceți noul nume al autorului (lasati gol pentru a pastra valoarea curenta): ");
            String newAutorNume = s.nextLine();
            if (!newAutorNume.isEmpty()) {
                Autor newAutor = gasireAutorDupaNume(newAutorNume);
                if (newAutor != null) {
                    cartegasita.setAutor(newAutor);
                } else {
                    System.out.println("Autorul cu numele dat nu există.");
                }
            }

            System.out.print("Introduceți noua colecție (lasati gol pentru a pastra valoarea curenta):  ");
            String newCollection = s.nextLine();
            if (!newCollection.isEmpty()) {
                cartegasita.setCollection(newCollection);
            }
        } else {
            System.out.println("Cartea cu titlul dat nu a fost gasita.");
        }
    }

    /**
     * Șterge o carte din bibliotecă.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */

    public void ștergeCarte(Scanner s){
        System.out.println("Introduceti titlul cartii pe care doriti sa o stergeti: ");
        String titlu = s.nextLine();

        Carte cartegasita = null;
        for(Carte carte: carteList){
            if(carte.getTitlu().equalsIgnoreCase(titlu)){
                cartegasita = carte;
                break;
            }
        }

        if (cartegasita != null){
            carteList.remove(cartegasita);
            System.out.println("Cartea cu titlul '" + titlu + "' a fost stearsa cu succes!");
        } else {
            System.out.println("Cartea cu titlul '" + titlu + "' nu a fost gasita");
        }
    }

    /**
     * Afișează cărțile care aparțin unui anumit autor.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */

    public void cartileAparținândUnuiAnumitAutor(Scanner s){
        System.out.println("Introduceti numele autorului: ");
        String numeAutor = s.nextLine();

        for (Carte carte: carteList){
            if(carte.getAutor().getNume().equalsIgnoreCase(numeAutor)){
                System.out.println(carte);
            }
        }
    }

    /**
     * Afișează cărțile care fac parte dintr-o anumită colecție.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */

    public void cartileDintrOAnumităColecție(Scanner s){
        System.out.println("Introduceti numele colectiei: ");
        String collection = s.nextLine();

        for(Carte carte: carteList){
            if(carte.getCollection().equalsIgnoreCase(collection)){
                System.out.println(carte);
            }
        }
    }

    /**
     * Afișează cărțile împrumutate după o anumită dată.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */

    public void listaCartilorImprumutateDupaData(Scanner s){
        System.out.println("Introduceti data (format yyyy-MM-dd): ");
        String dataInput = s.nextLine();

        LocalDate date;
        try {
            date = LocalDate.parse(dataInput);
        } catch (DateTimeParseException e){
            System.out.println("Data introdusa este invalida. Incercati din nou.");
            return;
        }

        System.out.println("Cartile imprumutate dupa " + date + " : ");
        for (Carte carte : carteList){
            if(carte.getBorrowedDate() != null && carte.getBorrowedDate().isAfter(date)){
                System.out.println(carte);
            }
        }

    }

    /**
     * Adaugă un nou autor în bibliotecă.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */

    public void addAutor(Scanner s){
         s = new Scanner(System.in);

        System.out.print("Introduceți ID-ul autorului: ");
        int id = s.nextInt();
        s.nextLine();

        System.out.print("Introduceți numele autorului: ");
        String nume = s.nextLine();

        System.out.print("Introduceți prenumele autorului: ");
        String prenume = s.nextLine();

        Autor newAutor = new Autor(id, nume, prenume);
        autorMap.put(id, newAutor);
    }

    /**
     * Afișează toți autorii din bibliotecă.
     */

    public void displayAutori(){
        for (Autor autor : autorMap.values()) {
            System.out.println(autor);
        }
    }

    /**
     * Actualizează informațiile unui autor existent.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */

    public void updateAutor(Scanner s){
        System.out.print("Introduceți numele autorului pe care doriți să-l modificați: ");
        String nume = s.nextLine();

        Autor autor = null;
        for (Map.Entry<Integer, Autor> entry : autorMap.entrySet()) {
            if (entry.getValue().getNume().equalsIgnoreCase(nume)) {
                autor = entry.getValue();
                break;
            }
        }

        if (autor != null) {
            System.out.print("Introduceți noul nume (lăsați gol pentru a păstra valoarea curentă): ");
            String newNume = s.nextLine();
            if (!newNume.isEmpty()) {
                autor.setNume(newNume);
            }

            System.out.print("Introduceți noul prenume (lăsați gol pentru a păstra valoarea curentă): ");
            String newPrenume = s.nextLine();
            if (!newPrenume.isEmpty()) {
                autor.setPrenume(newPrenume);
            }
        } else {
            System.out.println("Autorul cu numele dat nu a fost găsit.");
        }
    }

    /**
     * Șterge un autor din bibliotecă.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */


    public void deleteAutor(Scanner s){
        System.out.print("Introduceți numele autorului pe care doriți să-l ștergeți: ");
        String nume = s.nextLine();

        Integer idToRemove = null;
        for (Map.Entry<Integer, Autor> entry : autorMap.entrySet()) {
            if (entry.getValue().getNume().equalsIgnoreCase(nume)) {
                idToRemove = entry.getKey();
                break;
            }
        }

        if (idToRemove != null) {
            autorMap.remove(idToRemove);
            System.out.println("Autorul a fost șters cu succes.");
        } else {
            System.out.println("Autorul cu numele dat nu a fost găsit.");
        }
    }

    /**
     * Adaugă un nou student în bibliotecă.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */
    public void addStudent(Scanner s){
        System.out.println("Introduceti ID-ul studentului:");
        int id = s.nextInt();

        s.nextLine();

        System.out.println("Introduceți numele studentului:");
        String nume = s.nextLine();

        System.out.println("Introduceți prenumele studentului:");
        String prenume = s.nextLine();

        System.out.println("Introduceți facultatea studentului:");
        String facultate = s.nextLine();

        System.out.println("Introduceți specializarea studentului:");
        String specializare = s.nextLine();

        Student student = new Student(id, nume, prenume, facultate, specializare);

        studentList.add(student);

    }

    /**
     * Afișează toți studenții din bibliotecă.
     */
    public void displayStudents(){
        if (studentList.isEmpty()) {
            System.out.println("Nu sunt studenți în lista.");
        } else {
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    /**
     * Actualizează informațiile unui student existent.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */

    public void updateStudent(Scanner s){
        System.out.print("Introduceți numele studentului pentru actualizare: ");
        String numeCautat = s.nextLine().trim();

        Student studentToUpdate = null;
        for (Student student : studentList) {
            if (student.getNume().equalsIgnoreCase(numeCautat)) {
                studentToUpdate = student;
                break;
            }
        }

        if (studentToUpdate == null) {
            System.out.println("Studentul cu numele " + numeCautat + " nu a fost găsit.");
            return;
        }

        System.out.println("Actualizarea studentului: " + studentToUpdate);
        System.out.println("Lăsați câmpul gol dacă nu doriți să actualizați valoarea.");

        System.out.print("Nume nou [" + studentToUpdate.getNume() + "]: ");
        String nume = s.nextLine().trim();
        if (!nume.isEmpty()) studentToUpdate.setNume(nume);

        System.out.print("Prenume nou [" + studentToUpdate.getPrenume() + "]: ");
        String prenume = s.nextLine().trim();
        if (!prenume.isEmpty()) studentToUpdate.setPrenume(prenume);

        System.out.print("Facultate nouă [" + studentToUpdate.getFacultate() + "]: ");
        String facultate = s.nextLine().trim();
        if (!facultate.isEmpty()) studentToUpdate.setFacultate(facultate);

        System.out.print("Specializare nouă [" + studentToUpdate.getSpecializare() + "]: ");
        String specializare = s.nextLine().trim();
        if (!specializare.isEmpty()) studentToUpdate.setSpecializare(specializare);

        System.out.println("Studentul a fost actualizat cu succes!");
    }

    /**
     * Șterge un student din bibliotecă.
     *
     * @param s Scanner-ul pentru citirea datelor de intrare.
     */

    public void deleteStudent(Scanner s){
        System.out.println("Introduceți numele studentului pentru a șterge:");
        String numeCautat = s.nextLine().trim();
        Student studentToRemove = null;

        for (Student student : studentList) {
            if (student.getNume().equalsIgnoreCase(numeCautat)) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            studentList.remove(studentToRemove);
            System.out.println("Studentul a fost șters.");
            saveFisier(STUDENTI_FILE, studentList);
        } else {
            System.out.println("Nu s-a găsit un student cu numele: " + numeCautat);
        }
    }

}


