package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;

public class CandidatoResultado {

    private Oferente oferente;
    private int cumplidas;
    private int total;
    private String porcentaje;

    public CandidatoResultado(Oferente oferente, int cumplidas, int total) {
        this.oferente = oferente;
        this.cumplidas = cumplidas;
        this.total = total;
        this.porcentaje = total == 0 ? "100.00" : String.format("%.2f", (cumplidas * 100.0) / total);
    }

    public Oferente getOferente() { return oferente; }
    public int getCumplidas() { return cumplidas; }
    public int getTotal() { return total; }
    public String getPorcentaje() { return porcentaje; }
}