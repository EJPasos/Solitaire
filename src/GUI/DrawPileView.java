package GUI;

import DeckOfCards.CartaInglesa;
import DeckOfCards.Palo;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import solitaire.DrawPile;

public class DrawPileView extends StackPane {
    private DrawPile drawPile;
    private Rectangle placeholderCard;
    private Text placeholderText;
    private CartaView cartaView;

    private static final CartaView carta = new CartaView(new CartaInglesa(2, Palo.CORAZON, "ROJO")); // Para obtener dimensiones de carta

    private static final double CARD_WIDTH = carta.getAncho();
    private static final double CARD_HEIGHT = carta.getAlto();

    public DrawPileView(DrawPile drawPile) {
        this.drawPile = drawPile;

        // Placeholder para estado vacío (↻)
        placeholderCard = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        placeholderCard.setArcWidth(10);
        placeholderCard.setArcHeight(10);
        placeholderCard.setStroke(Color.BLACK);
        placeholderCard.setStrokeWidth(2);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0, 0, 0, 0.4));
        placeholderCard.setEffect(shadow);

        placeholderText = new Text();
        placeholderText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        setAlignment(Pos.CENTER);
        update();

        setOnMouseClicked(event -> handleClick());
        setOnMouseEntered(e -> {
            if (cartaView == null) placeholderCard.setStrokeWidth(3);
        });
        setOnMouseExited(e -> {
            if (cartaView == null) placeholderCard.setStrokeWidth(2);
        });
    }

    public void update() {
        getChildren().clear();

        if (drawPile.hayCartas()) {
            // Usar CartaView (carta boca abajo) en lugar de dibujar manual
            CartaInglesa backCard = new CartaInglesa(1, Palo.CORAZON, "ROJO"); // Carta genérica boca abajo
            cartaView = new CartaView(backCard);
            getChildren().add(cartaView);
        } else {
            cartaView = null;
            placeholderCard.setFill(Color.LIGHTGRAY);
            placeholderText.setText("↻");
            placeholderText.setFill(Color.DARKGRAY);
            getChildren().addAll(placeholderCard, placeholderText);
        }
    }

    private void handleClick() {
        System.out.println("DrawPile clicked");
    }
}
