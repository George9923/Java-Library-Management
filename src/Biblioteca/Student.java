package Biblioteca;

import java.io.Serializable;

/**
 * Clasa Student reprezintă un model pentru studenții unei universități.
 * Această clasă implementează interfața Serializable pentru a permite
 * serializarea și deserializarea obiectelor de tip Student.
 */

public class Student implements Serializable
{
    /**
     * serialVersionUID este folosit pentru a verifica compatibilitatea
     * versiunilor în timpul deserializării. Dacă nu există o clasă compatibilă,
     * va fi aruncată excepția InvalidClassException.
     */

    private static final long serialVersionUID = 1L;

    private int id; // ID-ul unic al studentului.
    private String nume; // Numele studentului.
    private String prenume; // Prenumele studentului
    private String facultate; // Numele facultății studentului.
    private String specializare; // Specializarea studentului.

    /**
     * Constructorul clasei Student.
     *
     * @param id          ID-ul unic al studentului.
     * @param nume        Numele studentului.
     * @param prenume     Prenumele studentului.
     * @param facultate   Numele facultății studentului.
     * @param specializare Specializarea studentului.
     */

    public Student(int id, String nume, String prenume, String facultate, String specializare) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.facultate = facultate;
        this.specializare = specializare;
    }

    /** Metodele getter și setter */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    /**
     * Returnează reprezentarea în șir de caractere a obiectului Student.
     *
     * @return Șirul de caractere care reprezintă studentul.
     */

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", facultate='" + facultate + '\'' +
                ", specializare='" + specializare + '\'' +
                '}';
    }
}
