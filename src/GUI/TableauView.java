package GUI;

import DeckOfCards.CartaInglesa;
import javafx.scene.layout.VBox;
import solitaire.TableauDeck;

public class TableauView extends VBox {
    private TableauDeck mazo;
    private int indice;

    public TableauView(TableauDeck mazo, int indice) {
        this.mazo = mazo;
        this.indice = indice;
        this.setSpacing(-90); // Para solapar las cartas verticalmente
        refrescar();
    }


    public void refrescar() {
        getChildren().clear();
        for (CartaInglesa c : mazo.getCards()) {
            getChildren().add(new CartaView(c));
        }
    }
}