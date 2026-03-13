package GUI;
import DeckOfCards.CartaInglesa;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class CartaView extends StackPane {
    private final CartaInglesa carta;
    private final String rutaCartas = "/home/jpasos/Documents/POO Lab/Solitaire/src/CardsImages/cartas_png/";
    // Ejemplo carta: English_pattern_3_of_clubs.svg


    protected static final double CARD_WIDTH = 100;
    protected static final double CARD_HEIGHT = 140;

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

            // Carta boca arriba con imagen

            String nombreArchivo = String.format("%d_of_%s.png",
                    carta.getValor(),
                    carta.getPalo().toString().toLowerCase());
            System.out.println("Intentando cargar imagen: " + rutaCartas + nombreArchivo);
            File file = new File(rutaCartas + nombreArchivo);
            String local = file.toURI().toString();
            Image imagenCarta = new Image(local);
            javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(imagenCarta);
            imageView.setFitWidth(CARD_WIDTH-5);
            imageView.setFitHeight(CARD_HEIGHT-5);
            getChildren().addAll(fondo, imageView);

//            fondo.setFill(Color.WHITE);
//            Label texto = new Label(carta.toString());
//            texto.setFont(Font.font("Arial", FontWeight.BOLD, 16));
//            texto.setTextFill(carta.getColor().equals("rojo") ? Color.RED : Color.BLACK);
//            texto.setAlignment(Pos.CENTER);
//            getChildren().addAll(fondo, texto);
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
