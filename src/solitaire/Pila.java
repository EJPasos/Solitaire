package solitaire;

public class Pila<T> {
    private int tope = -1;
    private T[] pila;

    public Pila(int capacidad) {
        pila = (T[]) new Object[capacidad];
    }

    public void push(T valor){
        if (!estaLlena()) {
            tope++;
            pila[tope] = valor;
        } else {
            System.out.println("La pila está llena");
        }
    }

    public T pop(){
        if (!estaVacia()) {
            T valor = pila[tope];
            pila[tope] = null;
            tope--;
            return valor;
        } else {
            System.out.println("La pila está vacía");
            return null;
        }
    }

    public T peek() {
        if (!estaVacia()) {
            return pila[tope];
        }
        return null;
    }

    public int size() {
        return tope + 1;
    }

    public T get(int index) {
        if (index >= 0 && index <= tope) {
            return pila[index];
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i <= tope; i++) {
            pila[i] = null;
        }
        tope = -1;
    }

    public boolean estaVacia(){
        return tope == -1;
    }

    public boolean estaLlena(){
        return tope == pila.length - 1;
    }
}
