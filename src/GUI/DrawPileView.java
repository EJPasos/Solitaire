package GUI;

import solitaire.DrawPile;

public class DrawPileView {
    private DrawPile drawPile;

    public DrawPileView(DrawPile drawPile) {
        this.drawPile = drawPile;
    }

    /**
     * Renderiza el estado actual del Draw Pile.
     * @return Representación en String del Draw Pile
     */
    public String render() {
        return drawPile.toString();
    }

    /**
     * Muestra el Draw Pile en consola.
     */
    public void display() {
        System.out.print(render());
    }
}
