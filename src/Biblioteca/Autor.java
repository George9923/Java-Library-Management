package Biblioteca;

import java.io.Serializable;

/**
 * Clasa Autor reprezintă un model pentru autorii dintr-o bibliotecă.
 * Aceasta clasă implementează interfața Serializable pentru a permite
 * serializarea și deserializarea obiectelor de tip Autor.
 */

public class Autor implements Serializable
{

    /**
     * serialVersionUID este folosit pentru a verifica compatibilitatea
     * versiunilor în timpul deserializării. Dacă nu există o clasă compatibilă,
     * va fi aruncată excepția InvalidClassException.
     */

    private static final long serialVersionUID = 1L;

    private int id; // ID-ul unic al autorului.
    private String nume; // Numele autorului.
    private String prenume; // Prenumele autorului.


    /**
     * Constructorul clasei Autor.
     *
     * @param id      ID-ul unic al autorului.
     * @param nume    Numele autorului.
     * @param prenume Prenumele autorului.
     */


    public Autor(int id, String nume, String prenume) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
    }

    /**
     * Returnează ID-ul autorului.
     *
     * @return ID-ul autorului.
     */

    public int getId() {
        return id;
    }

    /**
     * Setează ID-ul autorului.
     *
     * @param id Noul ID al autorului.
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returnează numele autorului.
     *
     * @return Numele autorului.
     */

    public String getNume() {
        return nume;
    }

    /**
     * Setează numele autorului.
     *
     * @param nume Noul nume al autorului.
     */

    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Returnează prenumele autorului.
     *
     * @return Prenumele autorului.
     */

    public String getPrenume() {
        return prenume;
    }

    /**
     * Setează prenumele autorului.
     *
     * @param prenume Noul prenume al autorului.
     */

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    /**
     * Returnează reprezentarea în șir de caractere a obiectului Autor.
     *
     * @return Șirul de caractere care reprezintă autorul.
     */

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }
}
