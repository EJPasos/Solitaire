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
    // Imagen dimensiones Ancho: 5100px, Alto: 2310px
    // imagen de 4 filas (picas, corazones, diamantes, tréboles) y 13 columnas (A, 2-10, J, Q, K.
    // cada carta tiene un ancho de 5100/13 = 392.3px y un alto de 2310/4 = 577.5px
    private static final double ANCHO_CARTA = 392.3;
    private static final double ALTO_CARTA =  577.5;

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
