package com.example.vettime2.modelos;

import java.io.Serializable;

public class Cliente_mascota implements Serializable {

    private int id;
    private int clienteId;
    private Cliente cliente;
    private int mascotaId;
    private Mascota mascota;
    private int activo;

    public Cliente_mascota() {

    }

    public Cliente_mascota(int id, int clienteId, Cliente cliente, int mascotaId, Mascota mascota, int activo) {
        this.id = id;
        this.clienteId = clienteId;
        this.cliente = cliente;
        this.mascotaId = mascotaId;
        this.mascota = mascota;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

}
