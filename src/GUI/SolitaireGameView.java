package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import solitaire.FoundationDeck;
import solitaire.SolitaireGame;
import solitaire.TableauDeck;

import java.util.ArrayList;

public class SolitaireGameView extends Application {
    private SolitaireGame game;
    private DrawPileView drawPileView;
    private WastePileView wastePileView;
    private ArrayList<FundationDeckView> foundationViews;
    private ArrayList<TableauView> tableauViews;
    private VBox root;
    private Button newGameButton;
    private Button resetButton;

    @Override
    public void start(Stage primaryStage) {
        // Inicializar el juego
        game = new SolitaireGame();

        // Crear el contenedor principal
        root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #2d5016;");

        // Crear los componentes de la interfaz
        createTopSection();
        createTableauSection();
        createButtons();

        // Configurar la escena
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Solitario");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Crea la sección superior con DrawPile, WastePile y Foundations.
     */
    private void createTopSection() {
        HBox topSection = new HBox(15);
        topSection.setAlignment(Pos.CENTER_LEFT);
        topSection.setPadding(new Insets(10));

        // Crear DrawPile y WastePile
        drawPileView = new DrawPileView(game.getDrawPile());
        wastePileView = new WastePileView(game.getWastePile());

        // Añadir funcionalidad de click al DrawPile
        drawPileView.setOnMouseClicked(event -> handleDrawPileClick());

        // Añadir funcionalidad de click al WastePile
        wastePileView.setOnMouseClicked(event -> handleWastePileClick());

        topSection.getChildren().addAll(drawPileView, wastePileView);

        // Añadir separador
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topSection.getChildren().add(spacer);

        // Crear los 4 Foundation Decks
        foundationViews = new ArrayList<>();
        for (FoundationDeck foundation : game.getFoundation()) {
            FundationDeckView foundationView = new FundationDeckView(foundation);
            foundationViews.add(foundationView);
            topSection.getChildren().add(foundationView);
        }

        root.getChildren().add(topSection);
    }

    /**
     * Crea la sección de los 7 Tableaux.
     */
    private void createTableauSection() {
        HBox tableauSection = new HBox(10);
        tableauSection.setAlignment(Pos.TOP_CENTER);
        tableauSection.setPadding(new Insets(10));

        // Crear los 7 Tableaux
        tableauViews = new ArrayList<>();
        int index = 0;
        for (TableauDeck tableau : game.getTableau()) {
            TableauView tableauView = new TableauView(tableau, index);
            tableauViews.add(tableauView);

            // Crear un contenedor para cada tableau con borde
            VBox tableauContainer = new VBox();
            tableauContainer.setMinWidth(90);
            tableauContainer.setMinHeight(400);
            tableauContainer.setStyle("-fx-border-color: #555; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-color: rgba(0,0,0,0.1); -fx-background-radius: 5;");
            tableauContainer.getChildren().add(tableauView);

            tableauSection.getChildren().add(tableauContainer);
            index++;
        }

        root.getChildren().add(tableauSection);
    }

    /**
     * Crea los botones de control del juego.
     */
    private void createButtons() {
        HBox buttonSection = new HBox(10);
        buttonSection.setAlignment(Pos.CENTER);
        buttonSection.setPadding(new Insets(10));

        newGameButton = new Button("Nuevo Juego");
        newGameButton.setOnAction(event -> handleNewGame());
        newGameButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px;");

        resetButton = new Button("Actualizar");
        resetButton.setOnAction(event -> updateAllViews());
        resetButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px;");

        buttonSection.getChildren().addAll(newGameButton, resetButton);
        root.getChildren().add(buttonSection);
    }

    /**
     * Maneja el click en el DrawPile.
     */
    private void handleDrawPileClick() {
        if (game.getDrawPile().hayCartas()) {
            game.drawCards();
        } else {
            game.reloadDrawPile();
        }
        updateAllViews();
    }

    /**
     * Maneja el click en el WastePile.
     */
    private void handleWastePileClick() {
        // Intentar mover al foundation
        if (game.moveWasteToFoundation()) {
            updateAllViews();
            checkGameOver();
        }
    }

    /**
     * Actualiza todas las vistas del juego.
     */
    private void updateAllViews() {
        drawPileView.update();
        wastePileView.update();

        for (FundationDeckView foundationView : foundationViews) {
            foundationView.update();
        }

        for (TableauView tableauView : tableauViews) {
            tableauView.refrescar();
        }
    }

    /**
     * Maneja la creación de un nuevo juego.
     */
    private void handleNewGame() {
        game = new SolitaireGame();

        // Recrear todas las vistas
        root.getChildren().clear();
        foundationViews.clear();
        tableauViews.clear();

        createTopSection();
        createTableauSection();
        createButtons();
    }

    /**
     * Verifica si el juego ha terminado.
     */
    private void checkGameOver() {
        if (game.isGameOver()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡Felicidades!");
            alert.setHeaderText("¡Has ganado!");
            alert.setContentText("Has completado el juego de Solitario exitosamente.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
