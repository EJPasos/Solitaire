package GUI;

import DeckOfCards.CartaInglesa;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import solitaire.FoundationDeck;

import java.util.function.Consumer;

public class FundationDeckView extends StackPane {
    private FoundationDeck foundationDeck;
    private Rectangle card;
    private Text cardText;
    private static final double CARD_WIDTH = 80;
    private static final double CARD_HEIGHT = 120;
    private Consumer<FoundationDeck> onFoundationClick;

    public FundationDeckView(FoundationDeck foundationDeck) {
        this.foundationDeck = foundationDeck;

        // Crear el rectángulo de la carta
        card = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        card.setArcWidth(10);
        card.setArcHeight(10);
        card.setStroke(Color.BLACK);
        card.setStrokeWidth(2);

        // Agregar sombra
        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0, 0, 0, 0.4));
        card.setEffect(shadow);

        // Crear el texto
        cardText = new Text();
        cardText.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        getChildren().addAll(card, cardText);
        setAlignment(Pos.CENTER);

        update();

        // Hacer clickeable para futuras funcionalidades
        setOnMouseClicked(event -> handleClick());

        // Efecto hover
        setOnMouseEntered(e -> card.setStrokeWidth(3));
        setOnMouseExited(e -> card.setStrokeWidth(2));
    }

    /**
     * Actualiza la vista según el estado del FoundationDeck.
     */
    public void update() {
        if (foundationDeck.estaVacio()) {
            // Foundation vacío - mostrar placeholder con el símbolo del palo
            card.setFill(Color.rgb(245, 245, 245));
            String paloSymbol = getPaloSymbol();
            cardText.setText(paloSymbol);

            // Color del símbolo según el palo
            String palo = String.valueOf(foundationDeck.getPalo());
            if (palo.equals("♥") || palo.equals("♦")) {
                cardText.setFill(Color.rgb(255, 100, 100, 0.5));
            } else {
                cardText.setFill(Color.rgb(100, 100, 100, 0.5));
            }
        } else {
            // Mostrar la carta superior
            CartaInglesa topCard = foundationDeck.getUltimaCarta();
            card.setFill(Color.WHITE);
            cardText.setText(topCard.toString());

            // Color rojo para corazones y diamantes, negro para picas y tréboles
            String palo = String.valueOf(topCard.getPalo());
            if (palo.equals("♥") || palo.equals("♦")) {
                cardText.setFill(Color.RED);
            } else {
                cardText.setFill(Color.BLACK);
            }
        }
    }

    /**
     * Obtiene el símbolo del palo del Foundation.
     */
    private String getPaloSymbol() {
        return String.valueOf(foundationDeck.getPalo());
    }

    /**
     * Maneja el click en el FoundationDeck.
     */
    private void handleClick() {
        if (onFoundationClick != null) {
            onFoundationClick.accept(foundationDeck);
        }
    }

    public void setOnFoundationClick(Consumer<FoundationDeck> onFoundationClick) {
        this.onFoundationClick = onFoundationClick;
    }

    public FoundationDeck getFoundationDeck() {
        return foundationDeck;
    }
}
