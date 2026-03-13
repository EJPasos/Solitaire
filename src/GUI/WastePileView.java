package GUI;

import DeckOfCards.CartaInglesa;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import DeckOfCards.Palo;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import solitaire.WastePile;

public class WastePileView extends StackPane {
    private WastePile wastePile;
    private final StackPane cardHost = new StackPane();
    private Node currentCardNode;
    private static final CartaView carta = new CartaView(new CartaInglesa(2, Palo.CORAZON, "ROJO")); // Para obtener dimensiones de carta
    private static final double CARD_WIDTH = carta.getAncho();
    private static final double CARD_HEIGHT = carta.getAlto();

    public WastePileView(WastePile wastePile) {
        this.wastePile = wastePile;

        getChildren().add(cardHost);
        setAlignment(Pos.CENTER);

        update();

        setOnMouseClicked(event -> handleClick());
        setOnMouseEntered(e -> {
            if (!isSelectedStyleActive()) setStyle("-fx-cursor: hand;");
        });
        setOnMouseExited(e -> {
            if (!isSelectedStyleActive()) setStyle("");
        });
    }

    /**
     * Actualiza la vista según el estado del WastePile.
     */
    public void update() {
        CartaInglesa topCard = wastePile.verCarta();
        cardHost.getChildren().clear();

        if (topCard == null) {
            cardHost.getChildren().add(createEmptySlot());
            currentCardNode = null;
            return;
        }

        currentCardNode = createCartaViewNode(topCard);
        cardHost.getChildren().add(currentCardNode);
    }

    private Node createEmptySlot() {
        Rectangle slot = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        slot.setArcWidth(10);
        slot.setArcHeight(10);
        slot.setStroke(Color.DARKGRAY);
        slot.setStrokeWidth(2);
        slot.setFill(Color.rgb(220, 220, 220, 0.35));

        Text t = new Text("---");
        t.setFill(Color.DARKGRAY);

        return new StackPane(slot, t);
    }

    /**
     * Crea una vista de carta usando CartaView (sin dibujar la carta manualmente).
     */
    private Node createCartaViewNode(CartaInglesa card) {
        try {
            Class<?> clazz = Class.forName("GUI.CartaView");
            Object instance = null;

            // Intento 1: new CartaView(CartaInglesa)
            try {
                Constructor<?> c = clazz.getConstructor(CartaInglesa.class);
                instance = c.newInstance(card);
            } catch (NoSuchMethodException ignored) { }

            // Intento 2: new CartaView(CartaInglesa, boolean)
            if (instance == null) {
                try {
                    Constructor<?> c = clazz.getConstructor(CartaInglesa.class, boolean.class);
                    instance = c.newInstance(card, true);
                } catch (NoSuchMethodException ignored) { }
            }

            // Intento 3: new CartaView() + setCarta(...)
            if (instance == null) {
                Constructor<?> c = clazz.getConstructor();
                instance = c.newInstance();
                tryInvoke(instance, "setCarta", new Class<?>[]{CartaInglesa.class}, new Object[]{card});
                tryInvoke(instance, "setCard", new Class<?>[]{CartaInglesa.class}, new Object[]{card});
                tryInvoke(instance, "mostrarFrente", new Class<?>[]{}, new Object[]{});
                tryInvoke(instance, "setFaceUp", new Class<?>[]{boolean.class}, new Object[]{true});
            }

            return (Node) instance;
        } catch (Exception e) {
            // Fallback visual en caso de incompatibilidad de API de CartaView.
            return createEmptySlot();
        }
    }

    private void tryInvoke(Object target, String method, Class<?>[] types, Object[] args) {
        try {
            Method m = target.getClass().getMethod(method, types);
            m.invoke(target, args);
        } catch (Exception ignored) { }
    }

    /**
     * Maneja el click en el WastePile.
     */
    private void handleClick() {
        // Este método será implementado cuando se integre con el controlador
        System.out.println("WastePile clicked");
    }

    public WastePile getWastePile() {
        return wastePile;
    }

    public void setSelected(boolean selected) {
        if (selected) {
            setStyle("-fx-border-color: #ffd54f; -fx-border-width: 2; -fx-border-radius: 6; -fx-background-color: rgba(255,213,79,0.15); -fx-background-radius: 6;");
        } else {
            setStyle("");
        }
    }

    private boolean isSelectedStyleActive() {
        String s = getStyle();
        return s != null && s.contains("#ffd54f");
    }
}
