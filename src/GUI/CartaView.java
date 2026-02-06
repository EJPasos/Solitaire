package GUI;
import DeckOfCards.CartaInglesa;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CartaView extends StackPane {
    private CartaInglesa carta;
    private final Image SPRITESHEET = new Image("file:/home/jpasos/Documents/POO Lab/Solitaire/src/English_pattern_playing_cards_deck.svg");

    public CartaView(CartaInglesa carta) {
        this.carta = carta;
        actualizar();
    }

    public void actualizar() {
        getChildren().clear();
        Rectangle fondo = new Rectangle(70, 100);
        fondo.setArcHeight(10);
        fondo.setArcWidth(10);
        fondo.setFill(Color.WHITE);
        fondo.setStroke(Color.BLACK);

        if (carta.isFaceup()) {
            Label texto = new Label(carta.toString()); // Usa el toString() de la lógica
            texto.setTextFill(carta.getColor().equals("rojo") ? Color.RED : Color.BLACK);
            getChildren().addAll(fondo, texto);
        } else {
            fondo.setFill(Color.BLUE); // Representación de carta boca abajo
            getChildren().add(fondo);
        }
    }
}
