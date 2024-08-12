public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        No novoNo = new No(valor);
        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            No atual = this.raiz;
            No pai = null;
            boolean esquerda = false;
            while (atual != null) {
                if (novoNo.getValor() < atual.getValor()) {
                    pai = atual;
                    atual = atual.getEsq();
                    esquerda = true;
                } else {
                    pai = atual;
                    atual = atual.getDir();
                    esquerda = false;
                }
            }
            if (esquerda) {
                pai.setEsq(novoNo);
            } else {
                pai.setDir(novoNo);
            }
        }
    }

    public void removerRaiz() {
        if (this.raiz == null) {
            System.out.println("A árvore está vazia.");
            return;
        }
            if (this.raiz.getEsq() == null && this.raiz.getDir() == null) {
            this.raiz = null;
        }else if (this.raiz.getDir() == null) {
            this.raiz = this.raiz.getEsq();
        }else if (this.raiz.getEsq() == null) {
            this.raiz = this.raiz.getDir();
        }else {
            No aux = aux(this.raiz);
            aux.setEsq(this.raiz.getEsq());
            if (aux != this.raiz.getDir()) {
                aux.setDir(this.raiz.getDir());
            }
            this.raiz = aux;
        }
    }

    public void remover(int valor) {
        if (this.raiz == null) {
            System.out.println("Árvore vazia.");
            return;
        }if (this.raiz.getValor() == valor) {
            removerRaiz();
            return;
        }
        No atual = this.raiz;
        No pai = null;
        boolean esquerda = true;

        while (atual != null && atual.getValor() != valor) {
            pai = atual;
            if (valor < atual.getValor()) {
                atual = atual.getEsq();
                esquerda = true;
            }else {
                atual = atual.getDir();
                esquerda = false;
            }
        }
        if (atual == null) {
            System.out.println("Valor não encontrado.");
            return;
        }

        // NÓS FOLHAS

        if (atual.getEsq() == null && atual.getDir() == null) {
            if (esquerda) {
                pai.setEsq(null);
            } else {
                pai.setDir(null);
            }
        }

        // APENAS 1 FILHO

        else if (atual.getDir() == null) {
            if (esquerda) {
                pai.setEsq(atual.getEsq());
            } else {
                pai.setDir(atual.getEsq());
            }
        } else if (atual.getEsq() == null) {
            if (esquerda) {
                pai.setEsq(atual.getDir());
            } else {
                pai.setDir(atual.getDir());
            }
        }

        // 2 FILHOS

        else {
            No aux = aux(atual);
            if (esquerda) {
                pai.setEsq(aux);
            } else {
                pai.setDir(aux);
            }
            aux.setEsq(atual.getEsq());
        }
    }

    private No aux(No noAux){
        No auxPai = noAux;
        No aux = noAux;
        No atual = noAux.getDir();

        while(atual != null){
            auxPai = aux;
            aux = atual;
            atual = atual.getEsq();
        }
        if (aux != noAux.getDir()){
            auxPai.setEsq(aux.getDir());
            aux.setDir(noAux.getDir());
        }
        return aux;
    }


    public No getRaiz() {
        return this.raiz;
    }

    public void preOrdem(No no) {
        if(no == null) {
            return;
        }
        System.out.println(no.getValor());
        preOrdem(no.getEsq());
        preOrdem(no.getDir());
    }

    public void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.getEsq());
        System.out.println(no.getValor());
        emOrdem(no.getDir());
    }

    public void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.getEsq());
        posOrdem(no.getDir());
        System.out.println(no.getValor());
    }
}
