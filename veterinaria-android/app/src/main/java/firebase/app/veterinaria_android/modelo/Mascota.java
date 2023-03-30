package firebase.app.veterinaria_android.modelo;

public class Mascota {

    String edad;
    String nombre;
    String raza;
    String sexo;

    public Mascota(){}

    public Mascota(String edad, String nombre, String raza, String sexo) {
        this.edad = edad;
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
