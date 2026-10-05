/** Tipos de ítem del inventario. */
public enum TipoItem {
    ARMA("Arma"), POCION("Poción");

    private final String etiqueta;
    TipoItem(String etiqueta) { this.etiqueta = etiqueta; }
    public String getEtiqueta() { return etiqueta; }
}
