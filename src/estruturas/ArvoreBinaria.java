package estruturas;

import java.util.Comparator;

public class ArvoreBinaria<T> {

    class No {
        private T valor;
        private No direita;
        private No esquerda;

        public No(T valor) {
            this.valor = valor;
            this.direita = null;
            this.esquerda = null;
        }

        public T getValor() { return valor; }
        public void setValor(T valor) { this.valor = valor; }
        public No getDireita() { return direita; }
        public void setDireita(No direita) { this.direita = direita; }
        public No getEsquerda() { return esquerda; }
        public void setEsquerda(No esquerda) { this.esquerda = esquerda; }
    }

    private No raiz;
    private Comparator<T> comparador;


    public ArvoreBinaria(Comparator<T> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }

    public boolean vazia() {
        return (this.raiz == null);
    }

    public boolean insere(T valor) {
        if (vazia()) {
            No temp = new No(valor);
            this.raiz = temp;
            return true;
        }
        No aux = this.raiz;
        No ant = null;

        while (aux != null) {
            ant = aux;
            int comparacao = comparador.compare(valor, aux.getValor());

            if (comparacao > 0) {
                aux = aux.getDireita();
            } else if (comparacao < 0) {
                aux = aux.getEsquerda();
            } else {
                return false;
            }
        }

        No n = new No(valor);
        if (comparador.compare(valor, ant.getValor()) > 0) {
            ant.setDireita(n);
        } else {
            ant.setEsquerda(n);
        }
        return true;
    }

    public void printPrecurso() {
        System.out.println("Precurso: ");
        precurso(this.raiz);
        System.out.println();
    }

    private void precurso(No aux) {
        if (aux != null) {
            System.out.print(aux.getValor().toString() + " | ");
            precurso(aux.getEsquerda());
            precurso(aux.getDireita());
        }
    }

    public void printIncurso() {
        System.out.println("Incurso (Ordenado): ");
        incurso(this.raiz);
        System.out.println();
    }

    private void incurso(No aux) {
        if (aux != null) {
            incurso(aux.getEsquerda());
            System.out.print(aux.getValor().toString() + " | ");
            incurso(aux.getDireita());
        }
    }

    public void printPosrecurso() {
        System.out.println("Posrecurso: ");
        posrecurso(this.raiz);
        System.out.println();
    }

    private void posrecurso(No aux) {
        if (aux != null) {
            posrecurso(aux.getEsquerda());
            posrecurso(aux.getDireita());
            System.out.print(aux.getValor().toString() + " | ");
        }
    }

    public int grau(No aux) {
        if ((aux.getDireita() == null) && (aux.getEsquerda() == null)) return 0;
        if ((aux.getDireita() == null) || (aux.getEsquerda() == null)) return 1;
        return 2;
    }

    public boolean remove(T valor) {
        No temp = this.raiz;
        No ant = null;

        while (temp != null && comparador.compare(temp.getValor(), valor) != 0) {
            ant = temp;
            if (comparador.compare(valor, temp.getValor()) < 0) {
                temp = temp.getEsquerda();
            } else {
                temp = temp.getDireita();
            }
        }

        if (temp == null) return false;

        if (grau(temp) == 0) {
            if (temp == this.raiz) {
                this.raiz = null;
            } else if (comparador.compare(valor, ant.getValor()) < 0) {
                ant.setEsquerda(null);
            } else {
                ant.setDireita(null);
            }
        } else if (grau(temp) == 1) {
            No filho = (temp.getDireita() != null) ? temp.getDireita() : temp.getEsquerda();

            if (temp == this.raiz) {
                this.raiz = filho;
            } else if (comparador.compare(valor, ant.getValor()) < 0) {
                ant.setEsquerda(filho);
            } else {
                ant.setDireita(filho);
            }
        } else if (grau(temp) == 2) {
            No menorDireita = temp.getDireita();
            No paiSucessor = temp;

            while (menorDireita.getEsquerda() != null) {
                paiSucessor = menorDireita;
                menorDireita = menorDireita.getEsquerda();
            }

            temp.setValor(menorDireita.getValor());

            if (paiSucessor == temp) {
                paiSucessor.setDireita(menorDireita.getDireita());
            } else {
                paiSucessor.setEsquerda(menorDireita.getDireita());
            }
        }
        return true;
    }

    public T busca(T valor) {
        No temp = this.raiz;
        while (temp != null) {
            int comparacao = comparador.compare(valor, temp.getValor());
            if (comparacao == 0) {
                return temp.getValor();
            } else if (comparacao < 0) {
                temp = temp.getEsquerda();
            } else {
                temp = temp.getDireita();
            }
        }
        return null;
    }
}