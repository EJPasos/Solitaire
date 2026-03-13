package GUI;
import DeckOfCards.CartaInglesa;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CartaView extends StackPane {
    private CartaInglesa carta;
    private final Image SPRITESHEET = new Image("file:/home/jpasos/Documents/POO Lab/Solitaire/src/English_pattern_playing_cards_deck.svg");
    // Imagen dimensiones Ancho: 5100px, Alto: 2310px
    // imagen de 4 filas (picas, corazones, diamantes, tréboles) y 13 columnas (A, 2-10, J, Q, K.
    // cada carta tiene un ancho de 5100/13 = 392.3px y un alto de 2310/4 = 577.5px
    protected static final double ANCHO_CARTA = 392.3;
    protected static final double ALTO_CARTA =  577.5;
    protected static final double CARD_WIDTH = 70;
    protected static final double CARD_HEIGHT = 100;

    public CartaView(CartaInglesa carta) {
        this.carta = carta;
        actualizar();
    }

    public void actualizar() {
        getChildren().clear();
        Rectangle fondo = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        fondo.setArcHeight(10);
        fondo.setArcWidth(10);
        fondo.setStroke(Color.BLACK);
        fondo.setStrokeWidth(1.5);

        // Agregar sombra para efecto 3D
        DropShadow shadow = new DropShadow();
        shadow.setRadius(3);
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        shadow.setColor(Color.rgb(0, 0, 0, 0.3));
        fondo.setEffect(shadow);

        if (carta.isFaceup()) {
            fondo.setFill(Color.WHITE);
            Label texto = new Label(carta.toString());
            texto.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            texto.setTextFill(carta.getColor().equals("rojo") ? Color.RED : Color.BLACK);
            texto.setAlignment(Pos.CENTER);
            getChildren().addAll(fondo, texto);
        } else {
            // Carta boca abajo con patrón
            fondo.setFill(Color.rgb(30, 80, 180));
            Rectangle innerPattern = new Rectangle(CARD_WIDTH - 10, CARD_HEIGHT - 10);
            innerPattern.setArcHeight(8);
            innerPattern.setArcWidth(8);
            innerPattern.setFill(Color.TRANSPARENT);
            innerPattern.setStroke(Color.WHITE);
            innerPattern.setStrokeWidth(2);
            getChildren().addAll(fondo, innerPattern);
        }
    }

    public double getAncho(){
        return CARD_WIDTH;
    }

    public double getAlto(){
        return CARD_HEIGHT;
    }

    public CartaInglesa getCarta() {
        return carta;
    }
}
