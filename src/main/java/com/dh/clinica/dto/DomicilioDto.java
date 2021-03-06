package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Domicilio;

public class DomicilioDto {
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public DomicilioDto(){

    }

    public DomicilioDto(Integer id) {
        this.id = id;
    }

    public DomicilioDto(String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public DomicilioDto(Integer id, String calle, String numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public DomicilioDto(Domicilio d) {
        id = d.getId();
        calle = d.getCalle();
        numero = d.getNumero();
        localidad = d.getLocalidad();
        provincia = d.getProvincia();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Domicilio toEntity() {
        Domicilio entity = new Domicilio();

        entity.setId(id);
        entity.setCalle(calle);
        entity.setLocalidad(localidad);
        entity.setProvincia(provincia);
        entity.setNumero(numero);

        return entity;
    }
}
