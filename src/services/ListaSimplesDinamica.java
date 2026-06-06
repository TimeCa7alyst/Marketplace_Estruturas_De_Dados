package services;

public class ListaSimplesDinamica<T> {

    class No<T> {
        private T dado;
        private No<T> proximo;

        public No(T dado, No<T> proximo) {
            this.dado = dado;
            this.proximo = proximo;
        }

        public T getDado() { return dado; }
        public void setDado(T dado) { this.dado = dado; }
        public No<T> getProximo() { return proximo; }
        public void setProximo(No<T> proximo) { this.proximo = proximo; }
    }

    private No<T> inicio;

    public ListaSimplesDinamica() {
        this.inicio = null;
    }

    public boolean listaVazia() {
        return (this.inicio == null);
    }

    public int tamanhoLista() {
        if (listaVazia()) return 0;

        No<T> temp = this.inicio;
        int tamanho = 0;
        while (temp != null) {
            tamanho++;
            temp = temp.getProximo();
        }
        return tamanho;
    }

    public boolean insere(T dado) {
        No<T> novoNo = new No<>(dado, null);

        if (listaVazia()) {
            this.inicio = novoNo;
        } else {
            No<T> temp = this.inicio;
            while (temp.getProximo() != null) {
                temp = temp.getProximo();
            }
            temp.setProximo(novoNo);
        }
        return true;
    }

    public boolean insere(T dado, int pos) {
        if (pos < 0 || pos > tamanhoLista()) {
            return false;
        }

        if (pos == 0) {
            No<T> novoNo = new No<>(dado, this.inicio);
            this.inicio = novoNo;
            return true;
        }

        No<T> temp = this.inicio;
        int i = 0;

        while (i < pos - 1) {
            temp = temp.getProximo();
            i++;
        }

        No<T> novoNo = new No<>(dado, temp.getProximo());
        temp.setProximo(novoNo);
        return true;
    }


    public void removeInicio() {
        if (listaVazia()) return;
        this.inicio = this.inicio.getProximo();
    }

    public void removeFim() {
        if (listaVazia()) return;

        if (this.inicio.getProximo() == null) {
            this.inicio = null;
            return;
        }

        No<T> temp = this.inicio;
        while (temp.getProximo().getProximo() != null) {
            temp = temp.getProximo();
        }
        temp.setProximo(null);
    }

    public void removeMeio(int pos) {
        if (pos < 0 || pos >= tamanhoLista() || listaVazia()) return;

        if (pos == 0) {
            removeInicio();
            return;
        }

        No<T> temp = this.inicio;
        int i = 0;

        while (i < pos - 1) {
            temp = temp.getProximo();
            i++;
        }
        temp.setProximo(temp.getProximo().getProximo());
    }

    public T getDado(int id) {
        if (id < 0 || id >= tamanhoLista() || listaVazia()) {
            return null;
        }

        No<T> temp = this.inicio;
        for (int i = 0; i < id; i++) {
            temp = temp.getProximo();
        }
        return temp.getDado();
    }


    public void printLista() {
        No<T> temp = this.inicio;
        while (temp != null) {
            System.out.println(temp.getDado().toString());
            temp = temp.getProximo();
        }
    }
}