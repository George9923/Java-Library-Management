package Biblioteca;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clasa Carte reprezintă un model pentru cărțile dintr-o bibliotecă.
 * Această clasă implementează interfața Serializable pentru a permite
 * serializarea și deserializarea obiectelor de tip Carte.
 */

public class Carte implements Serializable
{
    /**
     * serialVersionUID este folosit pentru a verifica compatibilitatea
     * versiunilor în timpul deserializării. Dacă nu există o clasă compatibilă,
     * va fi aruncată excepția InvalidClassException.
     */

    private static final long serialVersionUID = 1L;

    private int id; // ID-ul unic al cărții.
    private String titlu; // Titlul cărții.
    private String isbn; // Codul ISBN al cărții.
    private Autor autor;  // Autorul cărții.
    private String collection; // Colectia din care face parte cartea.
    private LocalDate borrowedDate; // Data împrumutului cărții.

    /**
     * Constructorul clasei Carte.
     *
     * @param id           ID-ul unic al cărții.
     * @param titlu        Titlul cărții.
     * @param isbn         Codul ISBN al cărții.
     * @param autor        Autorul cărții.
     * @param collection   Colectia din care face parte cartea.
     * @param borrowedDate Data împrumutului cărții.
     */

    public Carte(int id, String titlu, String isbn, Autor autor, String collection, LocalDate borrowedDate) {
        this.id = id;
        this.titlu = titlu;
        this.isbn = isbn;
        this.autor = autor;
        this.collection = collection;
        this.borrowedDate = borrowedDate;
    }

    /** Metodele getter și setter */

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    /**
     * Returnează reprezentarea în șir de caractere a obiectului Carte.
     *
     * @return Șirul de caractere care reprezintă cartea.
     */

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", isbn='" + isbn + '\'' +
                ", autor=" + autor +
                ", collection='" + collection + '\'' +
                '}';
    }
}
