package GUI;

import solitaire.WastePile;

public class WastePileView {
    private WastePile wastePile;

    public WastePileView(WastePile wastePile) {
        this.wastePile = wastePile;
    }

    /**
     * Renderiza el estado actual del Waste Pile.
     * @return Representación en String del Waste Pile
     */
    public String render() {
        return wastePile.toString();
    }

    /**
     * Muestra el Waste Pile en consola.
     */
    public void display() {
        System.out.print(render());
    }
}
