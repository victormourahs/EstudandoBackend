package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frota {
    private List<Nave> naves;

    public Frota(){
        //Inicializando a lista de naves
        naves = new ArrayList<>();
    }

    public List<Nave> getNaves() {
        return naves;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }

    public void adicionar(Nave n){
        naves.add(n);
    }

    public List<Nave> listarTodas(){
        return naves;
    }

    public Optional<Nave> buscarPorRegistro(String registro){
        return naves.stream()
                .filter(nave -> registro.equals(nave.registro()))
                .findFirst();
    }

    public boolean remover(String registro){
        for (Nave nave : naves){
            if (registro.equals(nave.registro())){
                naves.remove(nave);
                return true;
            }
        }
        return false;
    }
}
