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
    private Rectangle placeholderCard;
    private Text placeholderText;
    private CartaView cartaView;

    private static final CartaView carta = new CartaView(new CartaInglesa(2, DeckOfCards.Palo.CORAZON, "ROJO")); // Para obtener dimensiones de carta
    private static final double CARD_WIDTH = carta.getAncho();
    private static final double CARD_HEIGHT = carta.getAlto();
    private Consumer<FoundationDeck> onFoundationClick;

    public FundationDeckView(FoundationDeck foundationDeck) {
        this.foundationDeck = foundationDeck;

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
        placeholderText.setFont(Font.font("Arial", FontWeight.BOLD, 18));

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

        if (foundationDeck.estaVacio()) {
            placeholderCard.setFill(Color.rgb(245, 245, 245));
            String paloSymbol = getPaloSymbol();
            placeholderText.setText(paloSymbol);

            String palo = String.valueOf(foundationDeck.getPalo());
            if (palo.equals("♥") || palo.equals("♦")) {
                placeholderText.setFill(Color.rgb(255, 100, 100, 0.5));
            } else {
                placeholderText.setFill(Color.rgb(100, 100, 100, 0.5));
            }

            cartaView = null;
            getChildren().addAll(placeholderCard, placeholderText);
        } else {
            CartaInglesa topCard = foundationDeck.getUltimaCarta();
            cartaView = new CartaView(topCard);
            getChildren().add(cartaView);
        }
    }

    private String getPaloSymbol() {
        return String.valueOf(foundationDeck.getPalo());
    }

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
